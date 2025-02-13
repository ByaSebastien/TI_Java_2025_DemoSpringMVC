package be.bstorm.demospringmvc.models.product.forms;

import be.bstorm.demospringmvc.entities.Product;
import be.bstorm.demospringmvc.validators.annotations.CustomSize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ProductForm {

    @NotBlank(message = "Designation should not be empty")
    @CustomSize(min = 2, max = 50)
    private String designation;

    @CustomSize
    private String description;

    @Min(0)
    @NotNull
    private double price;

    // Mappeur mal aimé
//    public ProductForm(Product product) {
//        this.designation = product.getDesignation();
//        this.description = product.getDescription();
//        this.price = product.getPrice();
//    }

    public Product toProduct() {
        return new Product(
                designation,
                description.isEmpty() ? null : description,
                price,
                0
        );
    }

    public static ProductForm fromProduct(Product product) {
        return new ProductForm(product.getDesignation(), product.getDescription(), product.getPrice());
    }

    // Possibilité de validation
//    public List<String> validate() {
//        List<String> errors = new ArrayList<>();
//        if (designation == null || designation.isEmpty()) {
//            errors.add("Designation is required");
//        }
//        if (description == null || description.isEmpty()) {
//            errors.add("Description is required");
//        }
//        if (price < 0) {
//            errors.add("Price is required");
//        }
//        return errors;
//    }
}
