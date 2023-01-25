package it.corso.pizzaservice.service.impl;

import it.corso.pizzaservice.dao.PizzaRepository;
import it.corso.pizzaservice.dao.RestaurantIdsRepository;
import it.corso.pizzaservice.model.Pizza;
import it.corso.pizzaservice.model.RestaurantIds;
import it.corso.pizzaservice.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository repository;

    private final RestaurantIdsRepository restaurantIdsRepository;

    @Override
    public Pizza save(Pizza entity) {
        return repository.save(entity);
    }

    @Override
    public List<Pizza> save(List<Pizza> entities) {
        return (List<Pizza>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

    @Override
    public Optional<Pizza> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Pizza> findAll() {
        return repository.findAll();
    }

    @Override
    public Pizza update(Pizza entity, Long id) {
        Optional<Pizza> _pizza = repository.findById(id);
        if (_pizza.isPresent()) {
            return save(entity);
        } else {
            return null;
        }
    }

    @Override
    public List<Pizza> findByRestaurantId(Long restaurant_id) {
        List<RestaurantIds> _restaurantIds = restaurantIdsRepository.findByRestaurantId(restaurant_id);
        List<Pizza> _pizzas = new ArrayList<>(_restaurantIds.size());

        for (RestaurantIds el : _restaurantIds) {
            _pizzas.add(repository.findById(el.getPizzaId()).orElse(null));
        }
        return _pizzas;
    }
}