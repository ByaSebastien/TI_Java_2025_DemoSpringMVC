package be.bstorm.demospringmvc.utils;

import be.bstorm.demospringmvc.entities.Product;
import be.bstorm.demospringmvc.repositories.ProductRepository;
import be.bstorm.demospringmvc.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @Override
    public void run(String... args) throws Exception {

        if(productRepository.count() == 0) {
            List<Product> products = List.of(
                    new Product("Ordinateur Portable", "PC ultra performant avec SSD 1To", 1299.99),
                    new Product("Smartphone", "Écran OLED 6.5 pouces, 128Go de stockage", 899.99),
                    new Product("Casque Bluetooth", "Réduction de bruit active, autonomie 30h", 199.99),
                    new Product("Clavier Mécanique", "Switches Cherry MX Red, RGB", 149.99),
                    new Product("Souris Gamer", "Capteur optique 16 000 DPI", 79.99),
                    new Product("Écran 27 pouces", "Résolution 4K UHD, 144Hz", 349.99),
                    new Product("Imprimante Laser", "Monochrome, Wi-Fi intégré", 249.99),
                    new Product("Disque Dur Externe", "2To, USB-C 3.1", 129.99),
                    new Product("Enceinte Connectée", "Assistant vocal intégré, son 360°", 99.99),
                    new Product("Tablette Graphique", "Compatible Windows & Mac, stylet inclus", 299.99)
            );

            products.forEach(productService::save);
        }
    }
}
