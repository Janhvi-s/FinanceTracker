# Finance Tracker

A personal finance tracking REST API application built with Spring Boot, Spring Data JPA, and Hibernate ORM.

## Project Overview

Finance Tracker is a backend service that enables users to manage their financial transactions efficiently. It provides REST API endpoints to create, read, update, and delete transaction records, with persistent storage using an H2 in-memory database.

## Technology Stack

### Core Framework
- **Spring Boot 4.0.0** - Framework for building production-grade Spring applications
- **Java 17** - Programming language
- **Maven** - Build automation and dependency management tool

### Data Access & Persistence
- **Spring Data JPA** - Data access layer abstraction
- **Hibernate** - Object-Relational Mapping (ORM) framework
- **H2 Database** - In-memory relational database (ideal for development and testing)
- **H2 Console** - Web-based database viewer accessible at `/h2-console`

### Web & API
- **Spring Web MVC** - Web framework for building RESTful APIs
- **Spring REST Controllers** - For handling HTTP requests and responses

### Testing
- **Spring Data JPA Test** - Testing utilities for data access layers
- **Spring Web MVC Test** - Testing utilities for web components

## Project Structure

```
finance-tracker/
├── src/
│   ├── main/
│   │   ├── java/com/financetracker/finance_tracker/
│   │   │   ├── FinanceTrackerApplication.java      # Main Spring Boot application
│   │   │   ├── controller/
│   │   │   │   └── TransactionController.java      # REST API endpoints
│   │   │   ├── model/
│   │   │   │   └── Transaction.java                # JPA Entity class
│   │   │   └── repository/
│   │   │       └── TransactionRepository.java      # Spring Data JPA Repository
│   │   └── resources/
│   │       ├── application.properties              # Application configuration
│   │       ├── static/                             # Static resources
│   │       └── templates/                          # HTML templates
│   └── test/
│       └── java/com/financetracker/finance_tracker/
│           └── FinanceTrackerApplicationTests.java # Unit tests
├── pom.xml                                         # Maven configuration
└── README.md                                       # This file
```

## Core Components

### 1. Transaction Entity (`Transaction.java`)
JPA Entity representing a financial transaction with the following attributes:
- `id` (Long) - Unique identifier (auto-generated primary key)
- `amount` (double) - Transaction amount
- `date` (LocalDate) - Transaction date
- `description` (String) - Transaction description
- `type` (String) - Transaction type (e.g., income, expense)

### 2. TransactionRepository (`TransactionRepository.java`)
Spring Data JPA Repository interface extending `JpaRepository<Transaction, Long>` providing:
- Automatic CRUD operations
- Database interaction abstraction
- Standard operations: save, findAll, findById, delete

### 3. TransactionController (`TransactionController.java`)
REST Controller handling API endpoints with dependency injection:

#### API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/transactions` | Create a new transaction |
| GET | `/api/transactions` | Retrieve all transactions |
| GET | `/api/transactions/{id}` | Retrieve transaction by ID |
| PUT | `/api/transactions/{id}` | Update existing transaction |
| DELETE | `/api/transactions/{id}` | Delete transaction by ID |

### 4. FinanceTrackerApplication (`FinanceTrackerApplication.java`)
Main Spring Boot application entry point with `@SpringBootApplication` annotation.

## Configuration

### Database Configuration (`application.properties`)
```properties
# H2 Console Configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Database Connection (In-memory)
spring.datasource.url=jdbc:h2:mem:financetrackerdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update    # Auto-create/update tables
spring.jpa.show-sql=true                # Log SQL statements
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Installation & Running

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd finance-tracker
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   The application will start on `http://localhost:8080`

4. **Access H2 Console**
   Navigate to `http://localhost:8080/h2-console` to view and interact with the database
   - JDBC URL: `jdbc:h2:mem:financetrackerdb`
   - Username: `sa`
   - Password: `password`

## API Usage Examples

### Create a Transaction
```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 500.00,
    "date": "2026-01-07",
    "description": "Monthly salary",
    "type": "income"
  }'
```

### Get All Transactions
```bash
curl http://localhost:8080/api/transactions
```

### Get Transaction by ID
```bash
curl http://localhost:8080/api/transactions/1
```

### Update a Transaction
```bash
curl -X PUT http://localhost:8080/api/transactions/1 \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 550.00,
    "date": "2026-01-07",
    "description": "Updated salary",
    "type": "income"
  }'
```

### Delete a Transaction
```bash
curl -X DELETE http://localhost:8080/api/transactions/1
```

## Architecture Highlights

- **Dependency Injection**: Constructor-based DI for loose coupling
- **Repository Pattern**: Abstraction layer for database operations
- **ORM with Hibernate**: Automatic SQL generation and object mapping
- **RESTful Design**: Standard HTTP methods for CRUD operations
- **Error Handling**: Exception handling with meaningful error messages

## Testing

Run unit tests with:
```bash
mvn test
```

## Future Enhancements

- User authentication and authorization
- Transaction filtering and search capabilities
- Monthly/yearly financial summaries and reports
- Multiple user support with separate transaction histories
- Data validation and constraints
- API documentation with Swagger/OpenAPI
- PostgreSQL support for production deployments

## License

This project is provided as-is for educational and personal use.

---

**Created**: December 2025
**Last Updated**: January 2026
