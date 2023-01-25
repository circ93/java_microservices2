package it.corso.pizzeria.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.corso.pizzeria.dto.ToppingDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Topping API")
public interface ToppingController {

    //definizamo il metodo in swagger
    @ApiOperation("Add new topping")
    public ToppingDTO save(@RequestBody ToppingDTO toppingDTO);

    @ApiOperation("Find topping by ID")
    public ToppingDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete by ID")
    public void deleteById(@PathVariable("id") Long id);

    @ApiOperation("Find all toppings")
    public List<ToppingDTO> findAll();

    @ApiOperation("Update topping")
    public ToppingDTO update(@RequestBody ToppingDTO toppingDTO, @PathVariable("id") Long id);

}
