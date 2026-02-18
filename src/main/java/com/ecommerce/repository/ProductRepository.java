package com.ecommerce.repository;

import com.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Find products by category
    List<Product> findByCategoryIgnoreCase(String category);
    
    // Find products by brand
    List<Product> findByBrandIgnoreCase(String brand);
    
    // Find products by name containing keyword
    List<Product> findByNameContainingIgnoreCase(String keyword);
    
    // Find products by description containing keyword
    List<Product> findByDescriptionContainingIgnoreCase(String keyword);
    
    // Find products by price range
    List<Product> findByPriceBetween(Double min, Double max);
    
    // Find products in stock
    List<Product> findByStockQuantityGreaterThan(int quantity);
}
