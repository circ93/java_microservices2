package it.corso.restaurantservice.mapper;

import it.corso.restaurantservice.dto.DriverDTO;
import it.corso.restaurantservice.model.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper extends GenericMapper<Driver, DriverDTO> {
}
