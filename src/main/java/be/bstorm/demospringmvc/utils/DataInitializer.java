package be.bstorm.demospringmvc.utils;

import be.bstorm.demospringmvc.entities.Product;
import be.bstorm.demospringmvc.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Autowired
    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(productRepository.count() == 0) {
            productRepository.save(new Product("truc","...",42D));
        }
    }
}
