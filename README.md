# Microservice Saga Pattern

Microservice Saga Patten using RabbitMQ. There are two types of architecture for this 
pattern: Choreography and Orchestration. For this example, I used the Choreography one.

I did not create the product microservice. Products will be saveed in the stock-service. 

The management plugin is included in the RabbitMQ distribution. It must be enabled 
before it can be used:

rabbitmq-plugins enable rabbitmq_management

http://localhost:15672/

To import definitions using rabbitmqctl, use:

rabbitmqctl import_definitions /path/rabbitmq-definitions.json

## Order Service

This service responsible for handling information regarding orders.

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

Create order - Post method:

http://localhost:8080/api/orders

```
{
    "productId": 3,
    "quantity": 4,
    "value": 70
}
```

Get all orders - Get method:

http://localhost:8080/api/orders

## Payment Service

This service responsible for handling information regarding payments.

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

## Stock Service

This service responsible for handling information regarding stocks.

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").