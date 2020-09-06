package ir.saeidbabaei.orderservice.event;

import ir.saeidbabaei.orderservice.model.Order;
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
public class OrderDoneEvent {
	
    private String transactionId;
    
    private Order order;
    
}