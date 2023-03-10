package it.corso.pizzeria;

import it.corso.pizzeria.dao.PizzaRepository;
import it.corso.pizzeria.dao.RestaurantRepository;
import it.corso.pizzeria.model.Driver;
import it.corso.pizzeria.model.Pizza;
import it.corso.pizzeria.model.Restaurant;
import it.corso.pizzeria.model.Topping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class PizzeriaApplicationTests {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

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

    @Test
    void populateDB2() {
        Topping mozzarella = Topping.builder().name("Mozzarella locale").build();
        Topping pomodoroLocale = Topping.builder().name("Pomodoro locale").build();
        Topping basilico = Topping.builder().name("Basilico").build();
        Topping gorgonzola = Topping.builder().name("Gorgonzola locale").build();
        Topping provolone = Topping.builder().name("Provolone locale").build();
        Topping granaPadano = Topping.builder().name("Grana Padano").build();
        Topping fiorDiLatte = Topping.builder().name("Fior di Latte").build();
        Topping funghiPorciniDellaSila = Topping.builder().name("Funghi porcini della Sila").build();
        Topping provoloneDelMonaco = Topping.builder().name("Provolone del Monaco").build();
        Topping salsaDiPeperoniGialli = Topping.builder().name("Salsa di Peperoni gialli").build();
        Topping salsicciaDiFegatoSbriciolata = Topping.builder().name("Salsiccia di Fegato sbriciolata").build();
        Topping patateSilane = Topping.builder().name("Patate silane").build();
        Topping lardoDiMaialeNeroSilano = Topping.builder().name("Lardo di maiale nero silano").build();
        Topping riduzionePeperoniGialliEPrezzemolo = Topping.builder().name("Riduzione di Peperoni gialli e prezzemolo").build();
        Topping rucola = Topping.builder().name("Rucola").build();
        Topping ricottaDiPecoraAlloZafferano = Topping.builder().name("Ricotta di pecora allo zafferano").build();
        Topping pomodoriSecchi = Topping.builder().name("Pomodori secchi").build();
        Topping gherigliDiNoce = Topping.builder().name("Gherigli di noce").build();
        Topping pomodoriniRossi = Topping.builder().name("Pomodorini rossi").build();
        Topping speckMaialeNeroCalabrese = Topping.builder().name("Speck maiale nero calabrese").build();
        Topping granellaDiPistacchio = Topping.builder().name("Granella di pistacchio").build();
        Topping battutoDiPistacchioDiBronte = Topping.builder().name("Battuto di pistacchio di Bronte").build();
        Topping figliata = Topping.builder().name("Figliata").build();
        Topping mortadella = Topping.builder().name("Mortadella").build();
        Topping cremaDiGorgonzolaEPistacchio = Topping.builder().name("Crema di gorgonzola e pistacchio").build();
        Topping pacchetelleGialle = Topping.builder().name("Pacchetelle gialle").build();
        Topping bottargaDiMuggine = Topping.builder().name("Bottarga di muggine").build();
        Topping aliciDiCetara = Topping.builder().name("Alici di Cetara").build();
        Topping zesteDiLimoneDiCetraro = Topping.builder().name("Zeste di Limone di Cetraro").build();
        Topping stracciatella = Topping.builder().name("Stracciatella").build();
        Pizza margherita = Pizza.builder().name("Margherita").favorite(true).toppings(Set.of(mozzarella, pomodoroLocale, basilico)).build();
        Pizza quattroFormaggi = Pizza.builder().name("4 Formaggi").toppings(Set.of(mozzarella, gorgonzola, provolone, granaPadano)).build();
        Pizza pizzaDAsila = Pizza.builder().name("Pizzad'Asila").toppings(Set.of(fiorDiLatte, patateSilane, funghiPorciniDellaSila, lardoDiMaialeNeroSilano)).build();
        Pizza pizzaSacroEProfano = Pizza.builder().name("Sacro e profano").toppings(Set.of(fiorDiLatte, salsaDiPeperoniGialli, salsicciaDiFegatoSbriciolata, funghiPorciniDellaSila, riduzionePeperoniGialliEPrezzemolo, provoloneDelMonaco)).build();
        Pizza pizzaDAccarezzare = Pizza.builder().name("Pizzad'Accarezzare").toppings(Set.of(fiorDiLatte, rucola, ricottaDiPecoraAlloZafferano, pomodoriSecchi, gherigliDiNoce)).build();
        Pizza pizzaDAlessandro = Pizza.builder().name("Pizzad'Alessandro").toppings(Set.of(fiorDiLatte, pomodoriniRossi, speckMaialeNeroCalabrese, granellaDiPistacchio, battutoDiPistacchioDiBronte)).build();
        Pizza pizzaEFigliata = Pizza.builder().name("Pizza e Figliata").toppings(Set.of(figliata, mortadella, cremaDiGorgonzolaEPistacchio)).build();
        Pizza pizzaBufalaDiMare = Pizza.builder().name("Pizza bufala di mare").toppings(Set.of(fiorDiLatte, pacchetelleGialle, bottargaDiMuggine, aliciDiCetara, zesteDiLimoneDiCetraro, stracciatella, basilico)).build();
        pizzaRepository.saveAll(List.of(margherita, quattroFormaggi, pizzaDAsila, pizzaSacroEProfano, pizzaDAccarezzare, pizzaDAlessandro, pizzaEFigliata, pizzaBufalaDiMare));
    }

    @Test
    void populateDB3() {
        Driver driver1 = Driver.builder().name("Driver1").build();
        Driver driver2 = Driver.builder().name("Driver2").build();
        Driver driver3 = Driver.builder().name("Driver3").build();
        Driver driver4 = Driver.builder().name("Driver4").build();
        Driver driver5 = Driver.builder().name("Driver5").build();
        Driver driver6 = Driver.builder().name("Driver6").build();
        Restaurant vogliaDiPizza = Restaurant.builder().name("Voglia di pizza").address("Via Roma").city("Roma").drivers(Set.of(driver1, driver2, driver3)).build();
        Restaurant pizzaAGoGo = Restaurant.builder().name("Pizza a GoGo").address("Via Milano").city("Milano").drivers(Set.of(driver4, driver5, driver6)).build();
        restaurantRepository.saveAll(List.of(vogliaDiPizza, pizzaAGoGo));
    }

}
