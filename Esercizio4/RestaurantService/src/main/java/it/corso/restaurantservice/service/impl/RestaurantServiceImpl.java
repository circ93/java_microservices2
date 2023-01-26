package it.corso.restaurantservice.service.impl;

import it.corso.restaurantservice.dao.RestaurantRepository;
import it.corso.restaurantservice.dto.RestaurantIdsDTO;
import it.corso.restaurantservice.model.Restaurant;
import it.corso.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    //richiamo la chiave del topic/queue di rabbit
    @Value("${app.rabbitmq.add-pizzas-routingkey}")
    private String addPizzasToRestaurantRoutinkey;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public Restaurant save(Restaurant entity) {
        return repository.save(entity);
    }

    @Override
    public List<Restaurant> save(List<Restaurant> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    @Override
    public Restaurant update(Restaurant entity, Long id) {
        Optional<Restaurant> _restaurant = repository.findById(id);
        if (_restaurant.isPresent()) {
            return save(entity);
        } else {
            return null;
        }
    }

    @Override
    public void addPizzasToRestaurant(List<RestaurantIdsDTO> restaurantIdsDTOS) {

        rabbitTemplate.convertAndSend("", addPizzasToRestaurantRoutinkey, restaurantIdsDTOS);

    }
}
