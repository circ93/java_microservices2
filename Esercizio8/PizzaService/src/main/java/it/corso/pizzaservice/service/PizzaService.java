package it.corso.pizzaservice.service;

import it.corso.pizzaservice.model.Pizza;
import it.corso.pizzaservice.model.RestaurantIds;

import java.util.List;

public interface PizzaService extends GenericService<Pizza, Long> {

    List<Pizza> findByRestaurantId(Long restaurant_id);

    List<Pizza> addPizzasToRestaurant(List<RestaurantIds> restaurantIdsList);

}