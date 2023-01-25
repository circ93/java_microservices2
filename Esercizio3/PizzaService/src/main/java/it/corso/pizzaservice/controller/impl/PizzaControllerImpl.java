package it.corso.pizzaservice.controller.impl;

import it.corso.pizzaservice.controller.PizzaController;
import it.corso.pizzaservice.dto.PizzaDTO;
import it.corso.pizzaservice.dto.RestaurantIdsDTO;
import it.corso.pizzaservice.mapper.PizzaMapper;
import it.corso.pizzaservice.mapper.RestaurantIdsMapper;
import it.corso.pizzaservice.model.Pizza;
import it.corso.pizzaservice.model.RestaurantIds;
import it.corso.pizzaservice.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaControllerImpl implements PizzaController {

    private final PizzaService pizzaService;
    private final PizzaMapper pizzaMapper;
    private final RestaurantIdsMapper restaurantIdsMapper;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PizzaDTO save(@RequestBody PizzaDTO pizzaDTO) {
        Pizza _pizza = pizzaMapper.asEntity(pizzaDTO);
        return pizzaMapper.asDTO(pizzaService.save(_pizza));
    }

    @Override
    @GetMapping("/{id}")
    public PizzaDTO findById(@PathVariable Long id) {
        Pizza _pizza = pizzaService.findById(id).orElse(null);
        return pizzaMapper.asDTO(_pizza);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        pizzaService.deleteById(id);

    }

    @Override
    @GetMapping
    public List<PizzaDTO> findAll() {
        return pizzaMapper.asDTOList(pizzaService.findAll());
    }

    @Override
    @PutMapping("/{id}")
    public PizzaDTO update(@RequestBody PizzaDTO pizzaDTO,@PathVariable Long id) {
        Pizza _pizza = pizzaMapper.asEntity(pizzaDTO);
        return pizzaMapper.asDTO(pizzaService.update(_pizza, id));
    }

    @Override
    @GetMapping("/restaurant/{restaurantId}")
    public List<PizzaDTO> findByRestaurantId(@PathVariable Long restaurantId) {
        List<Pizza> _pizzas = pizzaService.findByRestaurantId(restaurantId);
        return pizzaMapper.asDTOList(_pizzas);
    }

    @Override
    @PostMapping("/restaurant")
    public List<PizzaDTO> addPizzasToRestaurant(List<RestaurantIdsDTO> restaurantIdsDTOS) {
        List<RestaurantIds> _restaurantIds = restaurantIdsMapper.asEntityList(restaurantIdsDTOS);
        return pizzaMapper.asDTOList(pizzaService.addPizzasToRestaurant(_restaurantIds));
    }

}
