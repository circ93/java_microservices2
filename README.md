
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
<br  />
Come funziona:
> Resilience4j è un componente che a seconda della API annotata con @Resilience4j lui fa il polling del servizio chiamato e sa se il servizio è disponibile (circuito chiuso), o non disponibile (circuito apert), tempi di attesa per la risposta (mezzo aperto). Se il circuito presenta problemi, a seconda dei mille settaggi nel file yaml, chiama una funzione di fallBack che può generare eccezioni o fare cose. <br  />
> Questo componente va implementato in ogni microservizio che deve contattare altri microservizi.

- Esercizio 9 -> spring cloud implementation (Spring Cloud Zipkin) ...coming soon

<img src="https://github.com/circ93/java_microservices2/blob/9cd223559f4b83d30ebc5439c6ff792fae8e0999/img/zipkin.png" alt="drawing" width="500"/>
