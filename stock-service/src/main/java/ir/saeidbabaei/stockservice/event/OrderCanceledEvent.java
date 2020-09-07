package ir.saeidbabaei.stockservice.event;

import ir.saeidbabaei.stockservice.model.Order;
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
public class OrderCanceledEvent {
	
	private String transactionId;
    
    private Order order;
    
}