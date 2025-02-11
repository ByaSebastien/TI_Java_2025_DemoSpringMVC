package be.bstorm.demospringmvc.datas;

import be.bstorm.demospringmvc.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class FakeDb {

    public List<Product> products;

    public FakeDb() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Laptop", "High-end gaming laptop", 1500.99));
        products.add(new Product(2L, "Smartphone", "Latest model smartphone", 899.99));
        products.add(new Product(3L, "Tablet", "10-inch screen tablet", 499.49));
        products.add(new Product(4L, "Smartwatch", "Fitness tracking smartwatch", 199.99));
        products.add(new Product(5L, "Headphones", "Noise-canceling headphones", 129.99));
        products.add(new Product(6L, "Keyboard", "Mechanical gaming keyboard", 89.99));
        products.add(new Product(7L, "Mouse", "Wireless ergonomic mouse", 59.99));
        products.add(new Product(8L, "Monitor", "4K Ultra HD monitor", 349.99));
        products.add(new Product(9L, "Speaker", "Bluetooth portable speaker", 79.99));
        products.add(new Product(10L, "External SSD", "1TB external SSD", 129.49));
    }
}
