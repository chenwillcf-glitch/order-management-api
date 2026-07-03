# Order Management API

A Spring Boot REST API for managing orders, products, and customers.

## Overview

This application provides endpoints for:
- Creating and retrieving orders
- Managing products and inventory
- Customer management

## Project Structure

```
src/
  main/
    java/com/orderapi/
      - Application.java
      - controller/
      - service/
      - repository/
      - model/
  test/
    java/com/orderapi/
      - controller/
      - service/
```

## Getting Started

### Prerequisites
- Java 11+
- Maven 3.6+

### Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

The API will start on `http://localhost:8080`

## API Endpoints

### Orders
- `GET /api/orders` - Retrieve all orders
- `GET /api/orders/{id}` - Retrieve order by ID
- `POST /api/orders` - Create new order
- `PUT /api/orders/{id}/status` - Update order status
- `POST /api/orders/{orderId}/products/{productId}` - Add product to order
- `GET /api/orders/customer/{customerId}` - Get orders by customer
- `GET /api/orders/status/{status}` - Get orders by status
- `DELETE /api/orders/{id}` - Delete order

### Products
- `GET /api/products` - Retrieve all products
- `GET /api/products/{id}` - Retrieve product by ID
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Customers
- `GET /api/customers` - Retrieve all customers
- `GET /api/customers/{id}` - Retrieve customer by ID
- `POST /api/customers` - Create new customer
- `PUT /api/customers/{id}` - Update customer
- `DELETE /api/customers/{id}` - Delete customer
- `GET /api/customers/email/{email}` - Get customer by email

## Testing

Run tests with:
```bash
mvn test
```
