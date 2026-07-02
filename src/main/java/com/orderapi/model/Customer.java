package com.orderapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * Customer entity representing a customer.
 */
@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Email
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    private String city;

    private String state;

    private String zipCode;

    // TODO: Add validation for phone number format
    // Missing error handling for duplicate emails
}