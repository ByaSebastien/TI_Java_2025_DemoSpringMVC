package be.bstorm.demospringmvc.models.order;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {

    private List<OrderLineForm> orderLines;

    public OrderForm() {
        this.orderLines = new ArrayList<>();
    }

    public void add(OrderLineForm orderLine) {
        orderLines.add(orderLine);
    }
}
