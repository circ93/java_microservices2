package it.corso.pizzeria.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.corso.pizzeria.dto.PizzaDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//definiamo questa classe in swagger
@Api(tags = "Pizza API")
public interface PizzaController {

    //definizamo il metodo in swagger
    @ApiOperation("Add new pizza")
    public PizzaDTO save(@RequestBody PizzaDTO pizzaDTO);

    @ApiOperation("Find pizza by ID")
    public PizzaDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete by ID")
    public void deleteById(@PathVariable("id") Long id);

    @ApiOperation("Find all pizzas")
    public List<PizzaDTO> findAll();

    @ApiOperation("Update pizza")
    public PizzaDTO update(@RequestBody PizzaDTO pizzaDTO, @PathVariable("id") Long id);

    @ApiOperation("Find pizzas by restaurants id")
    public List<PizzaDTO> findByRestaurantId(@PathVariable("id") Long id);

}
