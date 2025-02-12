package be.bstorm.demospringmvc.repositories;

import be.bstorm.demospringmvc.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select count(p) > 0 from Product p where p.designation ilike :designation")
    boolean existsByDesignation(String designation);

    @Query("select count(p) > 0 from Product p where p.id != :id and p.designation ilike :designation")
    boolean existsOtherByDesignation(Long id, String designation);
}
