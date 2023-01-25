package it.corso.pizzaservice.mapper;

import it.corso.pizzaservice.dto.ToppingDTO;
import it.corso.pizzaservice.model.Topping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToppingMapper extends GenericMapper<Topping, ToppingDTO> {



}