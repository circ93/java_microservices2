package it.corso.pizzeria.mapper;

import it.corso.pizzeria.dto.RestourantDTO;
import it.corso.pizzeria.model.Restourant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends GenericMapper<Restourant, RestourantDTO> {
}
