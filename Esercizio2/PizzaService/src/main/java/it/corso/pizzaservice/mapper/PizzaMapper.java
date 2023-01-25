package it.corso.pizzaservice.mapper;

import it.corso.pizzaservice.dto.PizzaDTO;
import it.corso.pizzaservice.model.Pizza;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PizzaMapper extends GenericMapper<Pizza, PizzaDTO> {

}