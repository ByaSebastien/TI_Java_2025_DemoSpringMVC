package be.bstorm.demospringmvc.entities;

import be.bstorm.demospringmvc.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "order_")
@EqualsAndHashCode(of = {"id"}) @ToString(of = {"id","orderDate","deliveryDate","status"})
@Getter
public class Order {

    @Id
    @Setter
    private UUID id;

    @Setter
    private LocalDate orderDate;

    @Setter
    private LocalDate deliveryDate;

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private final Set<OrderLine> orderLines;

    public Order() {
        orderLines = new HashSet<>();
    }

    public Order(UUID id, LocalDate orderDate, LocalDate deliveryDate, OrderStatus status) {
        this();
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public void addOrderLine(OrderLine orderLine) {
        orderLines.add(orderLine);
    }

    public void removeOrderLine(OrderLine orderLine) {
        orderLines.remove(orderLine);
    }
}
