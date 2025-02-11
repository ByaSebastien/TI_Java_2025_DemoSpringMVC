package be.bstorm.demospringmvc.repositories;

import be.bstorm.demospringmvc.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

//    Optional<Product> findProductByDesignation(String designation);
}
