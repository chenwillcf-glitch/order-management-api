package com.orderapi.service;

import com.orderapi.model.Order;
import com.orderapi.model.Customer;
import com.orderapi.model.Product;
import com.orderapi.repository.OrderRepository;
import com.orderapi.repository.CustomerRepository;
import com.orderapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for OrderService
 * 
 * CRITICAL: Missing tests for error conditions and validations
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    private Order testOrder;
    private Customer testCustomer;
    private Product testProduct;

    @BeforeEach
    public void setUp() {
        testCustomer = new Customer();
        testCustomer.setId(1L);
        testCustomer.setFirstName("John");
        testCustomer.setLastName("Doe");
        testCustomer.setEmail("john@example.com");

        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setPrice(BigDecimal.valueOf(50.00));

        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setCustomer(testCustomer);
        testOrder.setProducts(new ArrayList<>());
        testOrder.setTotalAmount(BigDecimal.valueOf(50.00));
        testOrder.setStatus("PENDING");
    }

    @Test
    public void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        
        var result = orderService.getAllOrders();
        
        assertNotNull(result);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        
        Order result = orderService.getOrderById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testCreateOrder() {
        when(orderRepository.save(testOrder)).thenReturn(testOrder);
        
        Order result = orderService.createOrder(testOrder);
        
        assertNotNull(result);
        verify(orderRepository, times(1)).save(testOrder);
    }

    @Test
    public void testCalculateOrderTotal() {
        testOrder.getProducts().add(testProduct);
        
        BigDecimal total = orderService.calculateOrderTotal(testOrder);
        
        assertEquals(BigDecimal.valueOf(50.00), total);
    }

    // MISSING CRITICAL TESTS:
    // - testCreateOrderWithNegativeAmount (should fail but doesn't)
    // - testAddProductToOrderWithInvalidOrder (should throw but test doesn't expect it)
    // - testAddProductToOrderWithInvalidProduct
    // - testCreateOrderWithNullCustomer
    // - testUpdateOrderStatusWithInvalidStatus
    // - testGetOrderByIdNotFound (will throw NoSuchElementException)
}