package it.corso.pizzaservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ToppingDTO {

    private Long id;

    private String name;

}
