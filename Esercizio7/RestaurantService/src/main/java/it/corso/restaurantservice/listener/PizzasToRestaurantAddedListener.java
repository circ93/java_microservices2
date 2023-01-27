package it.corso.restaurantservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PizzasToRestaurantAddedListener {

    @RabbitListener(queues = {"${app.rabbitmq.pizzas-added-routingkey}"})
    public void onPizzaToRestaurantAdded(List<Object> pizzas) {

        log.info("Sono state inserite " + pizzas.size() + " pizze!");

        for (Object el : pizzas) {
            log.info("Pizza -> {} ", el.toString());
        }

    }


}
