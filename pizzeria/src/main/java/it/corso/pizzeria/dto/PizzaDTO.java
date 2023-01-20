package it.corso.pizzeria.dto;

import it.corso.pizzeria.model.Topping;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PizzaDTO {

    private Long id;

    private String name;

    private Boolean favorite;

    private Set<Topping> toppings = new HashSet<>();

}
