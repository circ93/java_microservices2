package it.corso.pizzeria.service;

import it.corso.pizzeria.model.Pizza;

import java.util.List;

public interface PizzaService extends GenericService<Pizza, Long> {

    List<Pizza> findByRestaurantId(Long id);

}
