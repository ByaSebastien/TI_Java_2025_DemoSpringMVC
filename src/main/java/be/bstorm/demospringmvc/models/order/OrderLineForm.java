package be.bstorm.demospringmvc.models.order;

import lombok.Data;

@Data
public class OrderLineForm {

    private Long productId;
    private int quantity;
}
