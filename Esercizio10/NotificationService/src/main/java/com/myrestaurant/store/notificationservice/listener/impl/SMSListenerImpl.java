package com.myrestaurant.store.notificationservice.listener.impl;

import com.myrestaurant.store.notificationservice.listener.NotificationListener;
import com.myrestaurant.store.notificationservice.service.SMSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
//mi occorre per definire il nome del profilo che poi posso richiamare, deve essere definito nello yaml il profilo attivo
@Profile("sms")
public class SMSListenerImpl implements NotificationListener {

    private final SMSService smsService;

    @Override
    //l'annotation mi occorre per dirgli su quale coda stare in ascolto
    @RabbitListener(queues = {"${app.rabbitmq.pizzas-added-notification}"})
    public void onNotifyPizzaToRestaurant(String event) {

        log.info("Into onNotifyPizzaToRestaurant sms method.");

        smsService.sendMessage(event);

    }
}
