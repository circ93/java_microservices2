package it.corso.pizzeria.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ToppingDTO {

    private Long id;

    private String name;

    //non importo pizze perche non mi occorre portarmi dietro le pizze

}
