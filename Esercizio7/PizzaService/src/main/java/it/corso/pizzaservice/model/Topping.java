package it.corso.pizzaservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Table(name = "topping")
public class Topping implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topping_id", nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @ManyToMany(mappedBy = "toppings")
    @JsonIgnore
    private Set<Pizza> pizze = new HashSet<>();

}