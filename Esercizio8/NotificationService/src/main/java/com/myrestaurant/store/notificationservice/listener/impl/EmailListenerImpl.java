package com.myrestaurant.store.notificationservice.listener.impl;

import com.myrestaurant.store.notificationservice.listener.NotificationListener;
import com.myrestaurant.store.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
//mi occorre per definire il nome del profilo che poi richiamerò dai microservizi
@Profile("email")
public class EmailListenerImpl implements NotificationListener {

    private final EmailService emailService;

    @Override
    //l'annotation mi occorre per dirgli su quale coda stare in ascolto
    @RabbitListener(queues = {"${app.rabbitmq.pizzas-added-notification}"})
    public void onNotifyPizzaToRestaurant(String event) {

        log.info("Into onNotifyPizzaToRestaurant email method.");

        emailService.sendMessage(event);

    }
}
