package ir.saeidbabaei.stockservice.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {

    private Long id;

    private Long productId;

    private Long quantity;
    
    private BigDecimal value;

}