# E-Commerce Product CRUD API

A Spring Boot RESTful web service for managing products with PostgreSQL database.

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- PostgreSQL 12+

## PostgreSQL Setup

### 1. Install PostgreSQL
Download and install from: https://www.postgresql.org/download/

### 2. Create Database
Open pgAdmin or run in SQL shell:
```sql
CREATE DATABASE ecommerce_db;
```

### 3. Configure Database Connection
Edit `src/main/resources/application.properties`:

```properties
# Update these values according to your PostgreSQL setup
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=postgres
spring.datasource.password=your_password
```

## Running the Application

### Option 1: Using Maven
```bash
mvn spring-boot:run
```

### Option 2: Build JAR and Run
```bash
mvn clean package
java -jar target/ecommerce-product-api-1.0.0.jar
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | Get all products (paginated) |
| GET | `/api/products/{id}` | Get product by ID |
| POST | `/api/products` | Create new product |
| PUT | `/api/products/{id}` | Update product |
| PUT | `/api/products/{id}/stock-quantity?quantity=X` | Update stock |
| DELETE | `/api/products/{id}` | Delete product |
| GET | `/api/products/category/{category}` | Filter by category |
| GET | `/api/products/brand/{brand}` | Filter by brand |
| GET | `/api/products/search?keyword=X` | Search products |
| GET | `/api/products/price-range?min=X&max=Y` | Filter by price |
| GET | `/api/products/in-stock` | Get in-stock products |

## Testing the API

### Create Product
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Laptop\",\"price\":999.99,\"category\":\"Electronics\",\"stockQuantity\":10,\"brand\":\"Dell\"}"
```

### Get All Products
```bash
curl http://localhost:8080/api/products
```

### Get Product by ID
```bash
curl http://localhost:8080/api/products/1
```

### Update Product
```bash
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Laptop Pro\",\"price\":1099.99,\"category\":\"Electronics\",\"stockQuantity\":15,\"brand\":\"Dell\"}"
```

### Delete Product
```bash
curl -X DELETE http://localhost:8080/api/products/1
```

## Project Structure

```
src/main/java/com/ecommerce/
├── Application.java              # Main Spring Boot application
├── config/
│   └── DataInitializer.java      # Sample data on startup
├── controller/
│   └── ProductController.java    # REST API endpoints
├── exception/
│   ├── GlobalExceptionHandler.java
│   └── ProductNotFoundException.java
├── model/
│   └── Product.java              # JPA Entity
├── repository/
│   └── ProductRepository.java    # Data access layer
└── service/
    ├── ProductService.java       # Service interface
    └── ProductServiceImpl.java   # Service implementation
```

## Technology Stack

- Spring Boot 2.7.14
- Spring Data JPA
- PostgreSQL Driver
- Maven
- Java 11

## Notes

- The application automatically creates the `products` table on first run
- Sample data (10 products) is loaded on startup if database is empty
- Set `spring.jpa.hibernate.ddl-auto=update` for auto schema updates
- For production, consider using `spring.jpa.hibernate.ddl-auto=validate`
