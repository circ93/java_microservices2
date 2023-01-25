package it.corso.pizzeria.mapper;

import it.corso.pizzeria.dto.ToppingDTO;
import it.corso.pizzeria.model.Topping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToppingMapper extends GenericMapper<Topping, ToppingDTO> {



}
