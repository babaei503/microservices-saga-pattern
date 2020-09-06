# Order Service

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