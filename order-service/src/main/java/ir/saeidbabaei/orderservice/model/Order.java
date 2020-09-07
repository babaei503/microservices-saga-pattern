package ir.saeidbabaei.orderservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Long productId;

    private Long quantity;

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public enum OrderStatus {
        NEW, DONE, CANCELED
    }

}