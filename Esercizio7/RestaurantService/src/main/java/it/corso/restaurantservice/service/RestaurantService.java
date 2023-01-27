package it.corso.restaurantservice.service;

import it.corso.restaurantservice.dto.RestaurantIdsDTO;
import it.corso.restaurantservice.model.Restaurant;

import java.util.List;

public interface RestaurantService extends GenericService<Restaurant, Long> {

    public void addPizzasToRestaurant(List<RestaurantIdsDTO> restaurantIdsDTOS);

}
