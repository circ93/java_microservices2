package it.corso.pizzaservice.mapper;

import it.corso.pizzaservice.dto.RestaurantIdsDTO;
import it.corso.pizzaservice.model.RestaurantIds;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantIdsMapper extends GenericMapper<RestaurantIds, RestaurantIdsDTO> {
}
