
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

Il Servizio di Spring Cloud Eureka serve per poter sapere dove posso contattare i microservizi, infatti tutti si devono registrare all'eureka server. Quindi è buona pratica implementare questo componente come un microservizio a parte, lo chiameremo DiscoveryService. <br  />
La dependency da aggiungere in tutti i microservizi è:
```sh
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```
<br  />
Nello yaml di DiscoveryService bisogna inserire la seguente configurazione:

```sh
spring:
  application:
    name: DISCOVERY-SERVICE

server:
  port: 8761

eureka:
  client:
    #essendo eureka server non ho bisogno di registrarmi come client
    register-with-eureka: false
    fetch-registry: false
```
<br  />
Dopo di che bisogna aggiungere l'annotazione @EnableEurekaServer nella main class annotata come SpringBootApplication!
<br  />
Mentre la configurazione dei microservizi è la seguente:
```sh
eureka:
  client:
    #permette la registrazione al server
    register-with-eureka: true
    fetch-registry: true
    service-url:
      #bisogna dirgli dove poter contattare il servizio di Eureka server
      defaultZone: http://localhost:8761/eureka/
```
Anche nei microservices occorre l'annotazione @EnableEurekaClient nella main class annotata come SpringBootApplication!


## 27/01
- Esercizio 6 -> spring cloud implementation (Spring Cloud Gateway)

Il Gateway è l'unico punto d'ingresso per le chiamate, così chi chiama il gateway non sa dell'esistenza dei miscroservizi che ci sono dietro, quindi si occupa di smistare le chiamate al giusto microservizio, infatti nella sua configurazione devo definire le chiamate che ogni microservizio può ricevere. (come una sorta di centralino).
<br  />
In poche parole: arriva una richiesta al gateway, legge la sua configurazione in base alla quale decide quale microservizio gli occorre; a questo punto il gateway chiede ad EurekaServer quante e quali istanze del servizio che gli occorre sono attive. Eureka risponde con la lista dei servizi attivi, a quel punto il gateway può inoltrare la richiesta al giusto micorservizio.
<br  />

Per prima cosa creiamo il servizio Gateway con le seguenti dipendenze:
```sh
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
Poi bisogna aggiungere la configurazione nel file yaml sia per poter contattare EurekaServer che le API dei servizi che può contattare.
```sh
spring:
  application:
    name: GATEWAY-SERVICE
  cloud:
    gateway:
      routes:
        - uri: lb://PIZZA-SERVICE
          predicates:
            - Path=/api/pizzas/**, /api/toppings/**
        - uri: lb://RESTAURANT-SERVICE
          predicates:
            - Path=/api/restaurants/**, /api/drivers/**


server:
  port: 8080

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/
```
Nelle uri vengono specificati i nomi specifici con cui i microservizi si registrano ad Eureka, in questo caso PIZZA-SERVICE e RESTAURANT-SERVICE e le relative Api che espongono.
<br  />

- Esercizio 7 -> spring cloud implementation (Spring Cloud Config Server)

Il ConfigServer disaccoppia la configurazione dal servizio, infatti i file yaml dei tre microservizi si trovano su una repo git, il ConfigServer si occupa di fornire la configurazione all'avvio dell'istanza del microservizio, così facendo in caso di cambiamenti la modifica va effettuata solo sulla repository e non nei singolo microservzi.<br  />
Inoltre introduce anche il concetto di profili, tramite i profili posso scegliere una configurazione diversa, rinominado il profilo con -{nome profilo} in automatico il ConfigServer carica quella configurazione. Per attivare un profilo devo settarlo dallo yaml con il tag profile active, molto importante l'ordine con cui setto piu di un profilo, da destra verso sinistra si sovrascrivono. Ad esempio nel caso del notification Service sono attivi due profili, ma a svuotare la coda è solo il profilo letter che si trova alla fine.<br  />


- Esercizio 8 -> spring cloud implementation (Spring Cloud Zipkin)

Zipkin mi permette di capire quali microservizi vengono contattati quando viene fatta una richiesta. Il controllo avviene tramite Spring Cloud Dynamic Tracing, ad ogni richiesta viene assegnato un identificativo (Trace) che identifica quella specifica chiamata in ingresso. Per ogni servizio che viene invocato per rispondere alla chiamata viene genrato uno SPAN, questo mi aiuterà a capire che quella specifica chiamata ha attraversato tot servizi. Sapere che quello Span fa parte di quella traccia (trace) mi fa capire che quel servizio è stato invocato da quella specifica chiamata.

Sleut ha le API di Tracing e zipkin si occupa dell'implementazione dell'Api; motivo per cui ogni servizio deve avere le dipendenze di zipkin. Per ogni richiesta ogni microservizio deve comunicare a zipkin i dati di traccia per cui è stato contattato, di per se il microservizio di queste info non ci fa nulla, ma è zipkin che ricostruisce la trace grazie all'identificativo span di ogni microservizio. Ma non solo, queste dependency occorrono al microservizio affinchè decorino la richiesta in uscita con l'identificativo di trace, così che il microservizio successivo sa che quella richiesta arriva da quella trace.


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

- Esercizio 9 -> spring cloud implementation (Spring Cloud OpenFeign)

<img src="https://github.com/circ93/java_microservices2/blob/c3de6faca77743e0f48b9e2fa6a99ecef839dcbc/img/openFeign.png" alt="drawing" width="500"/>
<br  />
OpenFeign definisce la truttura delle Api, a runtime la libreria di OpenFeign crea il client Rest per chiamate sincrone. Quindi creando l'interfaccia con il metodo che ci occorre lui lo genera in automatico. L'esempio pratico è implementato nel controller di restaurant-service.
L'interfaccia PizzaServiceClient nel package integration.pizza.client è annotata con @FeignClient, nell'url inietto l'api del servizio da contattare, recuperandolo dalla configurazione.yaml. L'unico metodo presente risponde ad una Post che si aspetta una lista di RestaurantIdsDTO e restituisce una lista di object, solo con queste informazioni è in grado di implementare il metodo in autonomia e rispondere alla richiesta. Infatti questo metodo viene invocato nel RestaurantControllerImpl se viene fatta una Post ad /aggiungiPizzas.
<br  />
<br  />
Per funzionare ha bisogno delle seguenti configurazioni:

<img src="https://github.com/circ93/java_microservices2/blob/b84b729d2bf478f87d52ead35f50255513109618/img/openFeign_conf.png" alt="drawing" width="500"/>
<br  />

Bisogna aggiungere le seguenti dipendenze nel Pom:

```sh

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

```
<br  />

- Esercizio 10 -> spring cloud implementation (Spring Cloud Circuit Breaker)

<img src="https://github.com/circ93/java_microservices2/blob/9cd223559f4b83d30ebc5439c6ff792fae8e0999/img/spring_cloud_circuit_braker.png" alt="drawing" width="500"/>


> Questo service ci viene in aiuto quando contatto un servizio e non ricevo risposta, o la risposta impiega troppo tempo. <br  />
> Dall'immagine sopra possiamo immaginare le chiamate API come un circuito elettrico, per cui quando il circuito è aperto la chiamata si interrompe e non potrò ricevere risposta. Questo componente introduce anche il concetto di mezzo-aperto, ovvero il caso in cui la risposta impiega troppo tempo ad arrivare. <br  />
> Quindi in qualche modo questo componente gestisce la resilienza delle comunicazioni! <br  />
<br  />
Come funziona:
<br  />
Resilience4j è un componente che a seconda della API annotata con @Resilience4j lui fa il polling del servizio chiamato e sa se il servizio è disponibile (circuito chiuso), o non disponibile (circuito apert), tempi di attesa per la risposta (mezzo aperto). Se il circuito presenta problemi, a seconda dei mille settaggi nel file yaml, chiama una funzione di fallBack che può generare eccezioni o fare cose. <br  />
Questo componente va implementato in ogni microservizio che deve contattare altri microservizi. <br  />


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
      addPizzasToRestaurant:
        wait-duration-in-open-state: 1m
        permitted-number-of-calls-in-half-open-state: 3
        sliding-window-type: count-based
        sliding-window-size: 5
        minimum-number-of-calls: 5
        slow-call-duration-threshold: 10s
        slow-call-rate-threshold: 60
        failure-rate-threshold: 60
```
<br  />
Per utilizzare CircuitBraker bisogna annotare le specifiche Api con la seguente annotation:

```sh

@CircuitBreaker(name = "aggiungiPizzasToRestaurant", fallbackMethod = "aggiungiPizzaToRestaurantFailed")

```
<br  />
il nome deve coincidere con l'instances all'interno dello yaml, mentre la funzione di fallback deve essere implementata e deve ricevere come parametro una Exeption. Ad esempio:

```sh
public List<Object> aggiungiPizzaToRestaurantFailed(Exception e){
    log.error("La chiamata al PizzaService non è andata a buon fine!");
    return Collections.emptyList();
}
```
<br  />

Per funzionare bisogna aggiungere una classe di configurazione che genera un particolare bean che gli occorre (fix stackoverflow):
```sh
@Configuration
public class CircuitBrakerConf {

    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }


    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }

}
```
<br  />


## CREAZIONE DOCKER IMAGE DA PROGETTO MAVEN

- Esercizio 10 Docker -> docker image generation, deploy with docker-compose

Per generare le immagini docker bisogna inserire la seguente configuration nel Pom:

```sh
<configuration>
    <image>
        #verrà creata un'immagine docker con il nome inserito in questo tag
        <name>com.myrestaurant.store/gateway-service</name>
    </image>
</configuration>
```

In questo progetto sono presenti 6 microservices, ognuno con una configurazione diversa.<br  />

Per una corretta procedura di avvio dei servizi ho creato un dockercompose-restaurant-project.yaml file con tutte le specifiche dipendenze, solo il ConfigService viene avviato in locale sulla porta 8888 e il quale recupera le configurazioni da una Repo git.

Dopo aver generato tutte le immagini lanciare docker-compose:
```sh
docker-compose -f dockercompose-restaurant-project.yaml up -d
```
il risultato sarà il seguente:


<img src="https://github.com/circ93/java_microservices2/blob/36a2222a1919805a3a815672baa5778c385e5592/img/docker_desktop.png" alt="drawing" width="500"/>

<br  />

Aprendo la console di Eureka è possibile vedere che tutti i servizi si sono registrati al server e quindi saranno disponibili:
```sh
localhost:8080/api/...
```
<br  />
<img src="https://github.com/circ93/java_microservices2/blob/36a2222a1919805a3a815672baa5778c385e5592/img/eureka_registration.png" alt="drawing" width="500"/>