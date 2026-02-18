package com.ecommerce.controller;

import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET all products with optional pagination
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Product> products = productService.getAllProducts(page, size);
        return ResponseEntity.ok(products);
    }

    // GET product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    // GET products by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        return ResponseEntity.ok(products);
    }

    // GET products by search keyword, brand and category
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category) {

        List<Product> products;
        if (brand != null && category != null) {
            List<Product> byBrand = productService.getProductsByBrand(brand);
            products = byBrand.stream()
                    .filter(p -> p.getCategory().equalsIgnoreCase(category))
                    .collect(java.util.stream.Collectors.toList());
        } else if (brand != null) {
            products = productService.getProductsByBrand(brand);
        } else if (category != null) {
            products = productService.getProductsByCategory(category);
        } else if (keyword != null) {
            products = productService.searchProducts(keyword);
        } else {
            products = productService.getAllProducts(0, 100).getContent();
        }
        return ResponseEntity.ok(products);
    }

    // GET products within price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        List<Product> products = productService.getProductsByPriceRange(min, max);
        return ResponseEntity.ok(products);
    }

    // GET products in stock
    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        List<Product> products = productService.getInStockProducts();
        return ResponseEntity.ok(products);
    }

    // POST - Add new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // PUT - Update product
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        try {
            Product savedProduct = productService.updateProduct(productId, updatedProduct);
            return ResponseEntity.ok(savedProduct);
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT - Update stock quantity
    @PutMapping("/{productId}/stock-quantity")
    public ResponseEntity<Product> updateStockQuantity(@PathVariable Long productId, @RequestParam int quantity) {
        try {
            Product savedProduct = productService.updateStockQuantity(productId, quantity);
            return ResponseEntity.ok(savedProduct);
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Delete product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.noContent().build();
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
