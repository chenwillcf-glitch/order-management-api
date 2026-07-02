package com.orderapi.service;

import com.orderapi.model.Product;
import com.orderapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ProductService
 * 
 * INCOMPLETE: Missing many test cases
 */
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;

    @BeforeEach
    public void setUp() {
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setPrice(BigDecimal.valueOf(99.99));
        testProduct.setDescription("A test product");
        testProduct.setQuantity(10);
    }

    @Test
    public void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        
        Product result = productService.getProductById(1L);
        
        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productRepository.findById(999L)).thenReturn(Optional.empty());
        
        Product result = productService.getProductById(999L);
        
        assertNull(result);
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.save(testProduct)).thenReturn(testProduct);
        
        Product result = productService.createProduct(testProduct);
        
        assertNotNull(result);
        assertEquals("Test Product", result.getName());
    }

    // TODO: Add tests for:
    // - createProduct with negative price (currently missing validation)
    // - updateProduct
    // - deleteProduct
    // - getProductBySku
    // - Error handling scenarios
}