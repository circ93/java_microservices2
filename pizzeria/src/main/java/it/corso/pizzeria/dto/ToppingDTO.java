package it.corso.pizzeria.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ToppingDTO {

    private Long id;

    private String name;

    //non importo pizze perche non mi occorre portarmi dietro le pizze

}
