package be.bstorm.demospringmvc.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode @ToString
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    @Setter
    @Column(nullable = false,unique = true,length = 50)
    private String designation;

    @Setter
    private String description;

    @Setter
    @Range(min = 0)
    private double price;

    @Setter
    private int stock;

    public Product(String designation, String description, double price, int stock) {
        this.designation = designation;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
