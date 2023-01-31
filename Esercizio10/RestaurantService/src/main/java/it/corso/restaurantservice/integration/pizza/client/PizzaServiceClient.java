package it.corso.restaurantservice.integration.pizza.client;

import it.corso.restaurantservice.dto.RestaurantIdsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "defaultPizzaClient", url = "${app.pizza-service-url}")
public interface PizzaServiceClient {

    @PostMapping("/")
    List<Object> aggiungiPizzaToRestaurant(List<RestaurantIdsDTO> restaurantIdsDTOS);

}
