package it.corso.pizzeria;

import it.corso.pizzeria.DAO.PizzaRepository;
import it.corso.pizzeria.model.Pizza;
import it.corso.pizzeria.model.Topping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class PizzeriaApplicationTests {

    @Autowired
    PizzaRepository pizzaRepository;


    @Test
    void populateDB() {

        Topping mozzarella = Topping.builder()
                .name("mozzarella locale")
                .build();

        Topping pomodoro = Topping.builder()
                .name("Pomodoro san Marzano")
                .build();

        Topping basilico = Topping.builder()
                .name("Basilico")
                .build();

        Pizza margherita = Pizza.builder()
                .name("Margherita")
                .favorite(true)
                .toppings(Set.of(mozzarella, pomodoro, basilico))
                .build();

        //non mi occorre salvare anche i toppings perche Hibernate lo fa in automatico come conseguenza del @ManyToMany
        //siccome viene istanziata la pizza e i toppings non hanno persistenza, allora li salva in automatico
        pizzaRepository.saveAll(
                List.of(margherita)
        );
    }

}
