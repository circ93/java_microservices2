package it.corso.restaurantservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.corso.restaurantservice.dto.RestaurantDTO;
import it.corso.restaurantservice.dto.RestaurantIdsDTO;
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

    //metodo che mi restituisce tutte le pizze del ristorante con l'id passato
    @ApiOperation("Find all pizzas by restaurant id")
    public List<Object> getPizzasByRestaurantId(@PathVariable("id") Long restaurant_id);

    //metodo  che mi aggiunge le pizze a un ristornate, nel restourantIds ci sono i riferimenti agli id delle pizze
    @ApiOperation("Add pizzas to restaurant")
    public List<Object> addPizzasToRestaurant(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS);
}