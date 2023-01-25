package it.corso.pizzaservice.dao;

import it.corso.pizzaservice.model.RestaurantIds;
import it.corso.pizzaservice.model.RestaurantIdsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantIdsRepository extends JpaRepository<RestaurantIds, RestaurantIdsPK> {

    List<RestaurantIds> findByRestaurantId(Long restaurant_id);

}
