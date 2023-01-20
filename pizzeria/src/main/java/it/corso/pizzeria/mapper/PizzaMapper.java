package it.corso.pizzeria.mapper;

import it.corso.pizzeria.dto.PizzaDTO;
import it.corso.pizzeria.model.Pizza;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PizzaMapper extends GenericMapper<Pizza, PizzaDTO> {



}
