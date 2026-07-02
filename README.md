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
- `PUT /api/orders/{id}` - Update order (TODO: incomplete)
- `DELETE /api/orders/{id}` - Delete order

### Products
- `GET /api/products` - Retrieve all products
- `POST /api/products` - Create new product

### Customers
- `GET /api/customers` - Retrieve all customers
- `POST /api/customers` - Create new customer

## Known Issues

- [ ] Missing error handling in OrderService
- [ ] Incomplete validation for Order amounts
- [ ] No authorization/authentication
- [ ] Limited test coverage
- [ ] Inconsistent exception handling across controllers

## TODO

- Add pagination to list endpoints
- Implement transaction management
- Add logging throughout
- Complete unit test coverage
- Add API documentation (Swagger)

## Testing

Run tests with:
```bash
mvn test
```

**Note:** Test coverage is incomplete and intentionally has gaps.