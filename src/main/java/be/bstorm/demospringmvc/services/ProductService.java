package be.bstorm.demospringmvc.services;

import be.bstorm.demospringmvc.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);
    Product findById(Long id);
    void save(Product product);
    void update(Long id, Product product);
    void deleteById(Long id);
}
