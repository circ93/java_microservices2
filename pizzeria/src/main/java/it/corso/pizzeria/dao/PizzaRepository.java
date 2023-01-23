package it.corso.pizzeria.dao;

import it.corso.pizzeria.model.Pizza;
import it.corso.pizzeria.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    List<Pizza> findByRestaurantsIn(List<Restaurant> restaurants);

}
