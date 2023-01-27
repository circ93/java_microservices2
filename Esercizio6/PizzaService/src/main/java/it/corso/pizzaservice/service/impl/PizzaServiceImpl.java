package it.corso.pizzaservice.service.impl;

import it.corso.pizzaservice.dao.PizzaRepository;
import it.corso.pizzaservice.dao.RestaurantIdsRepository;
import it.corso.pizzaservice.model.Pizza;
import it.corso.pizzaservice.model.RestaurantIds;
import it.corso.pizzaservice.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;

    private final RestaurantIdsRepository restaurantIdsRepository;

    @Value("${app.rabbitmq.pizzas-added-routingkey}")
    private String queuePizzaAdded;

    @Value("${app.rabbitmq.pizzas-added-notification}")
    private String queuePizzasAddedNotify;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public Pizza save(Pizza entity) {
        return pizzaRepository.save(entity);
    }

    @Override
    public List<Pizza> save(List<Pizza> entities) {
        return (List<Pizza>) pizzaRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        pizzaRepository.deleteById(id);

    }

    @Override
    public Optional<Pizza> findById(Long id) {
        return pizzaRepository.findById(id);
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza update(Pizza entity, Long id) {
        Optional<Pizza> _pizza = pizzaRepository.findById(id);
        if (_pizza.isPresent()) {
            return save(entity);
        } else {
            return null;
        }
    }

    //metodo che restituisce tutte le pizze del ristornate con l'id passato
    @Override
    public List<Pizza> findByRestaurantId(Long restaurant_id) {
        List<RestaurantIds> _restaurantIds = restaurantIdsRepository.findByRestaurantId(restaurant_id);
        List<Pizza> _pizzas = new ArrayList<>(_restaurantIds.size());

        for (RestaurantIds el : _restaurantIds) {
            _pizzas.add(pizzaRepository.findById(el.getPizzaId()).get());
        }
        return _pizzas;
    }

    // metodo che mi aggiorna la tabella RestaurantIds quindi associa le pizze passate al ristorante
    @Override
    public List<Pizza> addPizzasToRestaurant(List<RestaurantIds> restaurantIdsList) {
        List<Pizza> _pizzas = new ArrayList<>(restaurantIdsList.size());
        for (RestaurantIds el : restaurantIdsList){
            _pizzas.add(pizzaRepository.findById(el.getPizzaId()).get());
        }
        restaurantIdsRepository.saveAll(restaurantIdsList);
        // dopo aver inserito le pizze creo il messaggio di risposta da inviare alla coda di rabbitmq per restaurant
        rabbitTemplate.convertAndSend("", queuePizzaAdded, _pizzas);
        String event = "Sono state aggiunte " + _pizzas.size() + " pizze al ristorante!";
        rabbitTemplate.convertAndSend("", queuePizzasAddedNotify, event);
        return _pizzas;
    }

}