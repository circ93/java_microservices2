package it.corso.pizzeria.dto;

import it.corso.pizzeria.model.Driver;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RestourantDTO {

    private Long id;

    private String name;

    private String address;

    private String city;

    private Set<Driver> drivers = new HashSet<>();

}