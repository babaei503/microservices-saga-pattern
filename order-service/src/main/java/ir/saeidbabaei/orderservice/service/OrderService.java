package ir.saeidbabaei.orderservice.service;

import ir.saeidbabaei.orderservice.event.OrderCreatedEvent;
import ir.saeidbabaei.orderservice.model.Order;
import ir.saeidbabaei.orderservice.model.Order.OrderStatus;
import ir.saeidbabaei.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@AllArgsConstructor
@Service
public class OrderService {

	
    private final OrderRepository repository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Order createOrder(Order order) {
    	
        order.setStatus(OrderStatus.NEW);

        log.debug("Saving an order {}", order);

        Order returnOrder = repository.save(order);
        
        publish(order);

        return returnOrder;
        
    }

    private void publish(Order order) {
    	
        OrderCreatedEvent event = new OrderCreatedEvent(UUID.randomUUID().toString(), order);
        
        log.debug("Publishing an order created event {}", event);
        
        publisher.publishEvent(event);
        
    }

    public List<Order> findAll() {
    	
        return repository.findAll();
    }

    @Transactional
    public void updateOrderAsDone(Long orderId) {
    	
        log.debug("Updating Order {} to {}", orderId, OrderStatus.DONE);
        
        Optional<Order> orderOptional = repository.findById(orderId);
        
        if (orderOptional.isPresent()) {
        	
            Order order = orderOptional.get();
            order.setStatus(OrderStatus.DONE);
            repository.save(order);
            
            log.debug("Order {} done", order.getId());
            
        } else {
        	
            log.error("Cannot update Order to status {}, Order {} not found", OrderStatus.DONE, orderId);
            
        }
    }

    @Transactional
    public void cancelOrder(Long orderId) {
    	
        log.debug("Canceling Order {}", orderId);
        
        Optional<Order> optionalOrder = repository.findById(orderId);
        
        if (optionalOrder.isPresent()) {
        	
            Order order = optionalOrder.get();
            order.setStatus(OrderStatus.CANCELED);
            repository.save(order);
            
            log.debug("Order {} was canceled", order.getId());
            
        } else {
        	
            log.error("Cannot find an Order {}", orderId);
            
        }
    }
}