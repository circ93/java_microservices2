package it.corso.pizzeria.controller.Impl;

import it.corso.pizzeria.controller.RestaurantController;
import it.corso.pizzeria.dto.RestaurantDTO;
import it.corso.pizzeria.mapper.RestaurantMapper;
import it.corso.pizzeria.model.Restaurant;
import it.corso.pizzeria.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantControllerImpl implements RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;


    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantDTO save(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant _restaurant = restaurantMapper.asEntity(restaurantDTO);
        return restaurantMapper.asDTO(restaurantService.save(_restaurant));
    }

    @Override
    @GetMapping("/{id}")
    public RestaurantDTO findById(@PathVariable Long id) {
        Restaurant _restaurant = restaurantService.findById(id).orElse(null);
        return restaurantMapper.asDTO(_restaurant);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        restaurantService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<RestaurantDTO> findAll() {
        return restaurantMapper.asDTOList(restaurantService.findAll());
    }

    @Override
    public RestaurantDTO update(@RequestBody RestaurantDTO restaurantDTO, @PathVariable Long id) {
        Restaurant _restaurant = restaurantMapper.asEntity(restaurantDTO);
        return restaurantMapper.asDTO(restaurantService.update(_restaurant, id));
    }

    @Override
    @PostMapping("/addPizzas")
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantDTO addPizzaToRestaurantMenu(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant _restaurant = restaurantMapper.asEntity(restaurantDTO);
        return restaurantMapper.asDTO(restaurantService.addPizzasToRestaurant(_restaurant));
    }

}
