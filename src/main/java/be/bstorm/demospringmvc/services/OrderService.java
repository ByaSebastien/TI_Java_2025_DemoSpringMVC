package be.bstorm.demospringmvc.services;

import be.bstorm.demospringmvc.entities.Order;
import be.bstorm.demospringmvc.entities.OrderLine;

import java.util.List;

public interface OrderService {

    void createOrder(List<OrderLine> orderLines);
}
