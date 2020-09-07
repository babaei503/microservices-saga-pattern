package ir.saeidbabaei.stockservice.eventhandler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import ir.saeidbabaei.stockservice.event.BilledOrderEvent;
import ir.saeidbabaei.stockservice.exception.StockException;
import ir.saeidbabaei.stockservice.service.StockService;
import ir.saeidbabaei.stockservice.util.Converter;
import ir.saeidbabaei.stockservice.util.TransactionIdHolder;

@AllArgsConstructor
@Log4j2
@Component
public class BilledOrderHandler {

    private final Converter converter;
    private final StockService stockService;
    private final TransactionIdHolder transactionIdHolder;

    @RabbitListener(queues = {"${queue.billed-order}"})
    public void handle(@Payload String payload) {
    	
        log.debug("Handling a billed order event {}", payload);
        
        BilledOrderEvent event = converter.toObject(payload, BilledOrderEvent.class);
        
        transactionIdHolder.setCurrentTransactionId(event.getTransactionId());
        
        try {
        	
            stockService.updateQuantity(event.getOrder());
            
        } catch (StockException e) {
        	
            log.error("Cannot update stock, reason: {}", e.getMessage());
            
        }
    }
}