package ir.saeidbabaei.orderservice.eventhandler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import ir.saeidbabaei.orderservice.event.OrderCanceledEvent;
import ir.saeidbabaei.orderservice.service.OrderService;
import ir.saeidbabaei.orderservice.util.Converter;

@Log4j2
@Component
@AllArgsConstructor
public class OrderCanceledEventHandler {

    private final Converter converter;
    
    private final OrderService orderService;

    @RabbitListener(queues = {"${queue.order-canceled}"})
    public void onOrderCanceled(@Payload String payload) {
    	
        log.debug("Handling a refund order event {}", payload);
        
        OrderCanceledEvent event = converter.toObject(payload, OrderCanceledEvent.class);
        
        orderService.cancelOrder(event.getOrder().getId());
    }
}