package ir.saeidbabaei.stockservice.eventlistener;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import ir.saeidbabaei.stockservice.event.OrderCanceledEvent;
import ir.saeidbabaei.stockservice.event.OrderDoneEvent;
import ir.saeidbabaei.stockservice.util.Converter;

@Log4j2
@Component
public class StockEventListener {

    private static final String ROUTING_KEY = "";

    private final RabbitTemplate rabbitTemplate;
    private final Converter converter;
    private final String queueOrderDoneName;
    private final String topicOrderCanceledName;

    public StockEventListener(RabbitTemplate rabbitTemplate,
                              Converter converter,
                              @Value("${queue.order-done}") String queueOrderDoneName,
                              @Value("${topic.order-canceled}") String topicOrderCanceledName) {
    	
        this.rabbitTemplate = rabbitTemplate;
        this.converter = converter;
        this.queueOrderDoneName = queueOrderDoneName;
        this.topicOrderCanceledName = topicOrderCanceledName;
        
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onOrderDoneEvent(OrderDoneEvent event) {
    	
        log.debug("Sending order done event to {}, event: {}", queueOrderDoneName, event);
        
        rabbitTemplate.convertAndSend(queueOrderDoneName, converter.toJSON(event));
        
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onOrderCancelledEvent(OrderCanceledEvent event) {
    	
        log.debug("Sending order canceled event to {}, event: {}", topicOrderCanceledName, event);
        
        rabbitTemplate.convertAndSend(topicOrderCanceledName, ROUTING_KEY, converter.toJSON(event));
        
    }
}
