package it.corso.pizzaservice.dao;


import it.corso.pizzaservice.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}