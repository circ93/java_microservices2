package com.myrestaurant.store.notificationservice.listener.impl;

import com.myrestaurant.store.notificationservice.listener.NotificationListener;
import com.myrestaurant.store.notificationservice.service.EmailService;
import com.myrestaurant.store.notificationservice.service.LetterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
//mi occorre per definire il nome del profilo che poi richiamerò dai microservizi
@Profile("letter")
public class LetterListenerImpl implements NotificationListener {

    private final LetterService letterService;

    @Override
    @RabbitListener(queues = {"${app.rabbitmq.pizzas-added-notification}"})
    public void onNotifyPizzaToRestaurant(String event) {

        log.info("Into onNotifyPizzaToRestaurant letter method.");

        letterService.sendMessage(event);

    }
}
