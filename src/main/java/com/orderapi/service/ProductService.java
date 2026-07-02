package com.orderapi.service;

import com.orderapi.model.Product;
import com.orderapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieve all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get product by ID
     * 
     * TODO: Add null checking and proper error handling
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Create a new product
     * Missing: Validation for negative prices
     */
    public Product createProduct(Product product) {
        // No validation done here - prices can be negative!
        System.out.println("Creating product: " + product.getName());
        return productRepository.save(product);
    }

    /**
     * Update product
     * Inconsistent error handling pattern compared to other services
     */
    public Product updateProduct(Long id, Product productDetails) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            p.setName(productDetails.getName());
            p.setPrice(productDetails.getPrice());
            p.setDescription(productDetails.getDescription());
            p.setQuantity(productDetails.getQuantity());
            // Missing validation
            return productRepository.save(p);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductBySku(String sku) {
        return productRepository.findBySku(sku);
    }
}