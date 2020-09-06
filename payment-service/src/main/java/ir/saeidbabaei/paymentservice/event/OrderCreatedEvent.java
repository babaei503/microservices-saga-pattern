package ir.saeidbabaei.paymentservice.event;

import ir.saeidbabaei.paymentservice.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderCreatedEvent {

	private String transactionId;
    
    private Order order;
    
}