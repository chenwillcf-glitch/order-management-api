package com.orderapi.service;

import com.orderapi.model.Customer;
import com.orderapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing customers
 * 
 * TODO: Add email uniqueness validation
 * TODO: Add phone number format validation
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    /**
     * Create new customer
     * Missing: Validation for duplicate emails
     * Missing: Phone number format validation
     */
    public Customer createCustomer(Customer customer) {
        // No email uniqueness check
        return customerRepository.save(customer);
    }

    /**
     * Update customer
     * Different error handling pattern than ProductService
     */
    public Optional<Customer> updateCustomer(Long id, Customer customerDetails) {
        return customerRepository.findById(id).map(customer -> {
            customer.setFirstName(customerDetails.getFirstName());
            customer.setLastName(customerDetails.getLastName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setAddress(customerDetails.getAddress());
            customer.setCity(customerDetails.getCity());
            customer.setState(customerDetails.getState());
            customer.setZipCode(customerDetails.getZipCode());
            return customerRepository.save(customer);
        });
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}