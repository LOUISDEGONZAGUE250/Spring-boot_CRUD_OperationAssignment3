package com.ecommerce.config;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return args -> {
            // Check if data already exists
            if (productRepository.count() == 0) {
                // Initialize with 10 sample products
                productRepository.save(new Product(null, "Laptop Pro", "High-performance laptop with Intel i7 processor", 1299.99,
                        "Electronics", 15, "Dell"));
                productRepository.save(new Product(null, "Wireless Mouse", "Ergonomic wireless mouse with USB receiver", 29.99,
                        "Electronics", 50, "Logitech"));
                productRepository.save(new Product(null, "USB-C Cable", "Fast charging USB-C cable 2 meters long", 12.99, "Electronics",
                        100, "Anker"));
                productRepository.save(new Product(null, "Gaming Headset", "Surround sound gaming headset with noise cancellation", 149.99,
                        "Electronics", 25, "SteelSeries"));
                productRepository.save(new Product(null, "Running Shoes", "Professional running shoes with cushioning technology", 89.99,
                        "Sports", 30, "Nike"));
                productRepository.save(new Product(null, "Yoga Mat", "Non-slip yoga mat 6mm thickness", 25.99, "Sports", 40, "Liforme"));
                productRepository.save(new Product(null, "Cotton T-Shirt", "100% organic cotton comfortable t-shirt", 19.99, "Clothing", 60,
                        "H&M"));
                productRepository.save(new Product(null, "Denim Jeans", "Classic blue denim jeans with stretch fabric", 49.99, "Clothing",
                        35, "Levi's"));
                productRepository.save(new Product(null, "Stainless Steel Water Bottle",
                        "Insulated water bottle keeps drinks hot for 12 hours", 34.99, "Sports", 45, "Hydro Flask"));
                productRepository.save(new Product(null, "4K Webcam", "Ultra HD 4K webcam for streaming and video calls", 179.99,
                        "Electronics", 20, "Razer"));

                System.out.println("Sample products initialized successfully!");
            }
        };
    }
}
