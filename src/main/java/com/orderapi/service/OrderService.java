package com.orderapi.service;

import com.orderapi.model.Order;
import com.orderapi.model.Product;
import com.orderapi.model.Customer;
import com.orderapi.repository.OrderRepository;
import com.orderapi.repository.ProductRepository;
import com.orderapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing orders
 * 
 * CRITICAL GAPS:
 * - No error handling for missing customers or products
 * - No validation for negative amounts
 * - No transaction management
 * - Inconsistent with other service patterns
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Get order by ID
     * No error handling for missing order
     */
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get(); // Dangerous: throws NoSuchElementException
    }

    /**
     * Create order
     * MISSING: 
     * - Validation for customer existence
     * - Validation for product existence
     * - Validation for negative amounts
     * - Proper error handling
     */
    public Order createOrder(Order order) {
        // No validation whatsoever
        return orderRepository.save(order);
    }

    /**
     * Update order status
     * Incomplete implementation
     * 
     * TODO: Add validation for valid status transitions
     */
    public Order updateOrderStatus(Long id, String status) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order o = order.get();
            // No validation of status values
            o.setStatus(status);
            return orderRepository.save(o);
        }
        return null; // Inconsistent return pattern
    }

    /**
     * Add product to order
     * No validation
     */
    public Order addProductToOrder(Long orderId, Long productId, int quantity) {
        Order order = orderRepository.findById(orderId).get(); // Throws exception if not found
        Product product = productRepository.findById(productId).get(); // Same issue
        
        // No quantity validation
        // No stock check
        order.getProducts().add(product);
        
        // Total is not recalculated properly
        order.setTotalAmount(order.getTotalAmount().add(product.getPrice()));
        
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        // No validation that customer exists
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    /**
     * Calculate order total
     * MISSING: Error handling for null or empty product list
     */
    public BigDecimal calculateOrderTotal(Order order) {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : order.getProducts()) {
            total = total.add(product.getPrice());
        }
        return total;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}