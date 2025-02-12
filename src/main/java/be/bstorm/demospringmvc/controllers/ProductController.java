package be.bstorm.demospringmvc.controllers;

import be.bstorm.demospringmvc.entities.Product;
import be.bstorm.demospringmvc.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getProducts(
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "5") int size,
            Model model
    ) {
        Page<Product> products = productService.findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.ASC,"id")));
        int pageNumber = products.getPageable().getPageNumber();
        int pageSize = products.getPageable().getPageSize();
        int totalPages = products.getTotalPages();
        model.addAttribute("products", products.getContent());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);
        return "pages/product/index.html";
    }

    @GetMapping("/{id}")
    public String getProduct(
            @PathVariable Long id,
            Model model
    ) {

        Product product = productService.findById(id);
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
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(
            @PathVariable Long id,
            Model model
    ){
        Product product = productService.findById(id);

        model.addAttribute("product", product);
        return "pages/product/update.html";
    }

    @PostMapping("/update/{id}")
    public String postUpdateProduct(
            @PathVariable Long id,
            @ModelAttribute Product product
    ){

        productService.update(id,product);

        return "redirect:/product";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable Long id
    ) {
        productService.deleteById(id);
        return "redirect:/product";
    }
}