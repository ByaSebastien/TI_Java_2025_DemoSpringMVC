package be.bstorm.demospringmvc.services.impls;

import be.bstorm.demospringmvc.entities.Product;
import be.bstorm.demospringmvc.repositories.ProductRepository;
import be.bstorm.demospringmvc.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // Ceci est le RequiredArgsConstructor
//    public ProductServiceImpl(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    @Override
    public Page<Product> findAll(Pageable pageable) {

        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {

        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
    }

    @Override
    public void save(Product product) {

        if(productRepository.existsByDesignation(product.getDesignation())){
            throw new RuntimeException("Product with designation " + product.getDesignation() + " already exists");
        }
        productRepository.save(product);
    }

    @Override
    public void update(Long id, Product product) {

//        Product product = this.findById(id);
        Product existingProduct = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cannot update product with id " + id + ". Product not found")
        );
        if(productRepository.existsOtherByDesignation(id, product.getDesignation())){
           throw new RuntimeException("Other product with designation " + product.getDesignation() + " already exists");
        }
        existingProduct.setDesignation(product.getDesignation());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        productRepository.save(existingProduct);
    }

    @Override
    public void deleteById(Long id) {

        if(!productRepository.existsById(id)){
            throw new RuntimeException("Cannot delete product with id " + id + " does not exist");
        }
        productRepository.deleteById(id);
    }
}
