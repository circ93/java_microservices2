package it.corso.restaurantservice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class RestaurantIdsDTO {

    private Long restaurantId;

    private Long pizzaId;

}
