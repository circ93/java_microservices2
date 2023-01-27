
# _Spring Boot Microservices_

## 20/01
- Esercizio 1 -> monolithic Pizzeria project

## 23/01
- Esercizio 2 -> split project Pizzeria in two Services

## 24/01
- Esercizio 3 -> implemented the communication between the two services

## 25/01
- Esercizio 4 -> created connection whit Rabbitmq broker

## 26/01
- Esercizio 5 -> start spring cloud implementation (Spring Cloud Eureka)

## 27/01
- Esercizio 6 -> spring cloud implementation (Spring Cloud Gateway)
- Esercizio 7 -> spring cloud implementation (Spring Cloud Config Server)
- Esercizio 8 -> spring cloud implementation (Spring Cloud Circuit Breaker) ...coming soon

<img src="https://github.com/circ93/java_microservices2/blob/9cd223559f4b83d30ebc5439c6ff792fae8e0999/img/spring_cloud_circuit_braker.png" alt="drawing" width="500"/>

> Questo service ci viene in aiuto quando contatto un servizio e non ricevo risposta, o la risposta impiega troppo tempo. <br  />
> Dall'immagine sopra possiamo immaginare le chiamate API come un circuito elettrico, per cui quando il circuito è aperto la chiamata si interrompe e non potrò ricevere risposta. Questo componente introduce anche il concetto di mezzo-aperto, ovvero il caso in cui la risposta impiega troppo tempo ad arrivare. <br  />
> Quindi in qualche modo questo componente gestisce la resilienza delle comunicazioni! <br  />
