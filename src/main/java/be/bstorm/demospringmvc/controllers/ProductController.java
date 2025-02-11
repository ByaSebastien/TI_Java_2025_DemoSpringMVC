package be.bstorm.demospringmvc.controllers;

import be.bstorm.demospringmvc.datas.FakeDb;
import be.bstorm.demospringmvc.entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final FakeDb fakeDb = new FakeDb();

    @GetMapping
    public String getProducts(
            Model model
    ) {
        List<Product> products = fakeDb.products;
        model.addAttribute("products", products);
        return "pages/product/index.html";
    }

    @GetMapping("/{id}")
    public String getProduct(
            @PathVariable Long id,
            Model model
    ) {

        Product product = fakeDb.products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow();
        model.addAttribute("product", product);
        return "pages/product/details.html";
    }

    @GetMapping("/create")
    public String createProduct(
            Model model
    ) {
        model.addAttribute("product", new Product());
        return "pages/product/create.html";
    }

    @PostMapping("/create")
    public String createProduct(
            @ModelAttribute Product product
    ) {
        fakeDb.products.add(product);
        return "redirect:/product";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(
            @PathVariable Long id,
            Model model
    ){
        Product product = fakeDb.products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow();

        model.addAttribute("product", product);
        return "pages/product/update.html";
    }

    @PostMapping("/update/{id}")
    public String postUpdateProduct(
            @PathVariable Long id,
            @ModelAttribute Product product
    ){

        Product existingProduct = fakeDb.products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow();

        existingProduct.setDesignation(product.getDesignation());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());

        return "redirect:/product";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable Long id
    ) {
        fakeDb.products.removeIf(p -> p.getId().equals(id));
        return "redirect:/product";
    }
}