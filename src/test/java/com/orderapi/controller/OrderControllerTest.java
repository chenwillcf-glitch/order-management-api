package com.orderapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderapi.model.Order;
import com.orderapi.model.Customer;
import com.orderapi.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for OrderController
 * 
 * CRITICAL GAPS: Missing error handling tests
 * These tests don't verify what happens when exceptions are thrown
 */
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private Order testOrder;
    private Customer testCustomer;

    @BeforeEach
    public void setUp() {
        testCustomer = new Customer();
        testCustomer.setId(1L);
        testCustomer.setFirstName("John");
        testCustomer.setLastName("Doe");

        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setCustomer(testCustomer);
        testOrder.setProducts(new ArrayList<>());
        testOrder.setTotalAmount(BigDecimal.valueOf(100.00));
        testOrder.setStatus("PENDING");
    }

    @Test
    public void testGetAllOrders() throws Exception {
        when(orderService.getAllOrders()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOrderById() throws Exception {
        when(orderService.getOrderById(1L)).thenReturn(testOrder);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testCreateOrder() throws Exception {
        when(orderService.createOrder(any(Order.class))).thenReturn(testOrder);

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testOrder)))
                .andExpect(status().isCreated());
    }

    // MISSING CRITICAL TESTS:
    // - testGetOrderByIdNotFound (will throw NoSuchElementException - 500 error!)
    // - testCreateOrderWithNegativeAmount
    // - testAddProductToOrderWithInvalidOrder (will throw exception)
    // - testUpdateOrderStatusWithInvalidStatus
}