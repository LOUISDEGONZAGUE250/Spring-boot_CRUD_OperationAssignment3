package com.ecommerce.service;

import com.ecommerce.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<Product> getAllProducts(int page, int size);

    Optional<Product> getProductById(Long productId);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> searchProducts(String keyword);

    List<Product> getProductsByPriceRange(Double min, Double max);

    List<Product> getInStockProducts();

    Product addProduct(Product product);

    Product updateProduct(Long productId, Product updatedProduct);

    Product updateStockQuantity(Long productId, int quantity);

    void deleteProduct(Long productId);
}
