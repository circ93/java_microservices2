package it.corso.pizzeria.dto;

import it.corso.pizzeria.model.Driver;
import it.corso.pizzeria.model.Pizza;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RestaurantDTO {

    private Long id;

    private String name;

    private String address;

    private String city;

    private Set<Driver> drivers;

    private Set<Pizza> pizzas;

}
