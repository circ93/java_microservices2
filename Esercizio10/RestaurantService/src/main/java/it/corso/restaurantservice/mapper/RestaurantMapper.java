package it.corso.restaurantservice.mapper;

import it.corso.restaurantservice.dto.RestaurantDTO;
import it.corso.restaurantservice.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends GenericMapper<Restaurant, RestaurantDTO> {
}
