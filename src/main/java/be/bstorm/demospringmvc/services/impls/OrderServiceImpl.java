package be.bstorm.demospringmvc.services.impls;

import be.bstorm.demospringmvc.entities.Order;
import be.bstorm.demospringmvc.entities.OrderLine;
import be.bstorm.demospringmvc.enums.OrderStatus;
import be.bstorm.demospringmvc.repositories.OrderLineRepository;
import be.bstorm.demospringmvc.repositories.OrderRepository;
import be.bstorm.demospringmvc.services.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    @Transactional
    @Override
    public void createOrder(List<OrderLine> orderLines) {
        LocalDate now = LocalDate.now();
        Order order = new Order(
                UUID.randomUUID(),
                now,
                now.plusDays(7),
                OrderStatus.IN_PROGRESS
        );
        orderRepository.save(order);
        for(OrderLine orderLine : orderLines) {
            if(orderLine.getQuantity() > orderLine.getProduct().getStock()){
                throw new RuntimeException("Limit of quantity exceeded for product " + orderLine.getProduct().getDesignation());
            }
            orderLine.setOrder(order);
        }
        orderLineRepository.saveAll(orderLines);
        orderRepository.save(order);
    }
}
