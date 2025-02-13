package be.bstorm.demospringmvc.controllers;

import be.bstorm.demospringmvc.entities.OrderLine;
import be.bstorm.demospringmvc.entities.Product;
import be.bstorm.demospringmvc.models.order.OrderForm;
import be.bstorm.demospringmvc.models.order.OrderLineForm;
import be.bstorm.demospringmvc.services.OrderService;
import be.bstorm.demospringmvc.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("/create")
    public String create(
            Model model
    ) {

        OrderForm order = new OrderForm();
        order.add(new OrderLineForm());
        order.add(new OrderLineForm());
        order.add(new OrderLineForm());

        model.addAttribute("order", order);

        return "pages/order/create.html";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute OrderForm order
    ){

        List<OrderLine> orderLines = new ArrayList<>();
        for(OrderLineForm lines : order.getOrderLines()){
            Product product = productService.findById(lines.getProductId());
            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantity(lines.getQuantity());
            orderLines.add(orderLine);
        }
        orderService.createOrder(orderLines);
        return "redirect:/product";
    }
}
