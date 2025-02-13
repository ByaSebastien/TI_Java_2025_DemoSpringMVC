package be.bstorm.demospringmvc.entities;

import be.bstorm.demospringmvc.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "order_")
@EqualsAndHashCode @ToString
@NoArgsConstructor @AllArgsConstructor
@Getter
public class Order {

    @Id
    @Setter
    private UUID id;

    @Setter
    @Column(nullable = false)
    private LocalDate orderDate;

    @Setter
    @Column(nullable = false)
    private LocalDate deliveryDate;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
