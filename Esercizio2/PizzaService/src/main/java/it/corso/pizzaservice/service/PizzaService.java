package it.corso.pizzaservice.service;

import it.corso.pizzaservice.model.Pizza;

import java.util.List;

public interface PizzaService extends GenericService<Pizza, Long> {

    List<Pizza> findByRestaurantId(Long restaurant_id);

}