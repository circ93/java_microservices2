
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
> Questo componente va implementato in ogni microservizio che deve contattare altri microservizi. <br  />

Dipendenze da inserire:
```sh
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Parametri da inserire nello yaml:

```sh
resilience4j:
  circuitbreaker:
    circuit-breaker-aspect-order: 1
    instances:
      addPizzasToRestaurantCircuitBreaker:
        wait-duration-in-open-state: 1m
        permitted-number-of-calls-in-half-open-state: 3
        sliding-window-type: count-based
        sliding-window-size: 5
        minimum-number-of-calls: 5
        slow-call-duration-threshold: 10s
        slow-call-rate-threshold: 60
        failure-rate-threshold: 60
  retry:
    retry-aspect-order: 2
    instances:
      retryAddPizzasToRestaurant:
        max-attempts: 3
        wait-duration: 5s
        enable-exponential-backoff: true
        exponential-backoff-multiplier: 2
        retry-exceptions:
          - org.springframework.web.client.RestClientException
```


- Esercizio 9 -> spring cloud implementation (Spring Cloud Zipkin) ...coming soon

<img src="https://github.com/circ93/java_microservices2/blob/4b65f85a1ecab23b554e3f829c100a8c5f4a3163/img/zipkin.png" alt="drawing" width="500"/>

Prima cosa da fare è avviare il container doker con zipkin sulla porta di default 9411.

Poi tocca inserire le dipendenze nei microservizi:

```sh
<!-- ZipKin -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>
```
<br  />

- Esercizio 10 -> spring cloud implementation (Spring Cloud OpenFeign) ...coming soon

<img src="https://github.com/circ93/java_microservices2/blob/c3de6faca77743e0f48b9e2fa6a99ecef839dcbc/img/openFeign.png" alt="drawing" width="500"/>

Inserire le seguenti configurazioni sui vari servizi

<img src="https://github.com/circ93/java_microservices2/blob/b84b729d2bf478f87d52ead35f50255513109618/img/openFeign_conf.png" alt="drawing" width="500"/>

Dipendenze del Pom

```sh
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
