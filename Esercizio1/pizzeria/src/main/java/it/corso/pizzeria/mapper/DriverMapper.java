package it.corso.pizzeria.mapper;

import it.corso.pizzeria.dto.DriverDTO;
import it.corso.pizzeria.model.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper extends GenericMapper<Driver, DriverDTO> {
}
