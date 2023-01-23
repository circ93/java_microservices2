package it.corso.pizzeria.dao;

import it.corso.pizzeria.model.Restourant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestourantRepository extends JpaRepository<Restourant, Long> {
}
