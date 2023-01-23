package it.corso.pizzeria.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class DriverDTO {

    private Long id;

    private String name;

}
