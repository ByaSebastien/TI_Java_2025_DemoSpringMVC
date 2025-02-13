package be.bstorm.demospringmvc.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderLine {

    @EmbeddedId
    @Setter
    private OrderLineId id;

    @Range(min = 1)
    @Setter
    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @MapsId("orderId")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @MapsId("productId")
    @Setter
    private Product product;

    public void setOrder(Order order) {
        this.order = order;
        if (this.id == null) {
            this.id = new OrderLineId(order.getId(), this.product.getId());
        }
        order.addOrderLine(this);
    }


    @Data
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderLineId {
        private UUID orderId;
        private Long productId;
    }
}
