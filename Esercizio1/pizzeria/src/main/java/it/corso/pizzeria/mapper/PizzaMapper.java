package it.corso.pizzeria.mapper;

import it.corso.pizzeria.dto.PizzaDTO;
import it.corso.pizzeria.model.Pizza;
import it.corso.pizzeria.model.Restaurant;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PizzaMapper extends GenericMapper<Pizza, PizzaDTO> {

}
