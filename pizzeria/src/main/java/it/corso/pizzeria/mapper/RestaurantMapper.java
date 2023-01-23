package it.corso.pizzeria.mapper;

import it.corso.pizzeria.dto.RestaurantDTO;
import it.corso.pizzeria.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends GenericMapper<Restaurant, RestaurantDTO> {
}
