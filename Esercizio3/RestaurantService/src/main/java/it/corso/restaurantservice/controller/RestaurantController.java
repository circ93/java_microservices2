package it.corso.restaurantservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.corso.restaurantservice.dto.RestaurantDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Restaurant API")
public interface RestaurantController {

    @ApiOperation("Add new restaurant")
    public RestaurantDTO save(@RequestBody RestaurantDTO restaurantDTO);

    @ApiOperation("Find restaurant by ID")
    public RestaurantDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete by ID")
    public void deleteById(@PathVariable("id") Long id);

    @ApiOperation("Find all restaurants")
    public List<RestaurantDTO> findAll();

    @ApiOperation("Update restourant")
    public RestaurantDTO update(@RequestBody RestaurantDTO restaurantDTO, @PathVariable("id") Long id);

    @ApiOperation("Find all pizzas by restaurant id")
    public List<Object> getPizzasByRestaurantId(@PathVariable("id") Long restaurant_id);

}