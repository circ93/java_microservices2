package com.myrestaurant.store.notificationservice.listener;

import com.myrestaurant.store.notificationservice.service.EmailService;
import com.myrestaurant.store.notificationservice.service.SMSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//log
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    private final EmailService emailService;

    private final SMSService smsService;

    //l'annotation mi occorre per dirgli su quale coda stare in ascolto
    @RabbitListener(queues = {"${app.rabbitmq.pizzas-added-notification}"})
    public void onAddPizzaToRestaurant(String event) {

        log.info("Into onAddPizzaToRestaurant method.");

        emailService.sendMessage(event);
        smsService.sendMessage(event);

    }




}
