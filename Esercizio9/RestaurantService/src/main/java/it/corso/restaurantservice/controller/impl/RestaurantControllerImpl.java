package it.corso.restaurantservice.controller.impl;

import it.corso.restaurantservice.controller.RestaurantController;
import it.corso.restaurantservice.dto.RestaurantDTO;
import it.corso.restaurantservice.dto.RestaurantIdsDTO;
import it.corso.restaurantservice.integration.pizza.client.PizzaServiceClient;
import it.corso.restaurantservice.mapper.RestaurantMapper;
import it.corso.restaurantservice.model.Restaurant;
import it.corso.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantControllerImpl implements RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    private final PizzaServiceClient pizzaServiceClient;

    //mi occorre per richiamare l'url del servizio pizza salvato nel file properties
    @Value("${app.pizza-service-url}")
    private String pizzaServiceUrl;


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
    @GetMapping("/pizzas/{restaurant_id}")
    public List<Object> getPizzasByRestaurantId(@PathVariable Long restaurant_id) {
        RestTemplate restTemplate = new RestTemplate();
        return List.of(Objects.requireNonNull(restTemplate.getForObject(pizzaServiceUrl + "/" + restaurant_id, Object[].class)));
    }


    //CHIAMATA SINCRONA tramite OpenFeign
    @Override
    @PostMapping("/aggiungiPizzas")
    public List<Object> aggiungiPizzasToRestaurant(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS) {
        return pizzaServiceClient.aggiungiPizzaToRestaurant(restaurantIdsDTOS);
    }


    // CHIAMA ASINCRONA
    @Override
    @PostMapping("/addPizzas")
    public List<Object> addPizzasToRestaurant(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS) {

        restaurantService.addPizzasToRestaurant(restaurantIdsDTOS);

        return null;
    }

}