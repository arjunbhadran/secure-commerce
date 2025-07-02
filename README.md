# secure-commerce

A robust and secure e-commerce web application built with **Spring MVC** and **Spring Security** using **JWT (JSON Web Token)** for stateless authentication.

## Features

- **User Registration & Login**
- **JWT-based Authentication & Authorization**
- **Role-based Access Control (User/Admin)**
- **Product Catalog Management (CRUD)**
- **Order Management**
- **Secure RESTful APIs**
- **Password Encryption with BCrypt**
- **MVC Architecture**

## Technology Stack

- **Java 17+**
- **Spring MVC**
- **Spring Security**
- **JWT (io.jsonwebtoken)**
- **Hibernate/JPA**
- **PostgreSQL**
- **Maven**

## Architecture Overview


graph TD
A[Client (React/Angular/JS)] -->|HTTP| B[Spring MVC Controller]
B --> C[Service Layer]
C --> D[Repository Layer]
D --> E[(Database)]
B --> F[Spring Security JWT Filter]
F --> G[AuthenticationManager]


## Security

- **Spring Security** secures all endpoints except for registration and login.
- **JWT** tokens are issued upon successful authentication and must be included in the `Authorization` header (`Bearer <token>`) for protected endpoints.
- **PasswordEncoder** (`BCryptPasswordEncoder`) is used for secure password hashing.

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL

### Installation

1. **Clone the repository**
```

git clone https://github.com/arjunbhadran/secure-commerce.git
cd secure-commerce

```

2. **Configure Database**
- Update `src/main/resources/application.properties` with your DB credentials.

3. **Build the Project**
```

mvn clean install

```

4. **Run the Application**
```

mvn spring-boot:run

```

### API Endpoints

| Endpoint                | Method | Description                   | Access         |
|-------------------------|--------|-------------------------------|---------------|
| `/api/auth/register`    | POST   | Register a new user           | Public        |
| `/api/auth/login`       | POST   | Authenticate user, get JWT    | Public        |
| `/api/products`         | GET    | View all products             | User/Admin    |
| `/api/products`         | POST   | Add new product               | Admin         |
| `/api/cart`             | GET    | View user's cart              | User          |
| `/api/orders`           | POST   | Place an order                | User          |

> **Note:** All protected endpoints require a valid JWT in the `Authorization` header.

### Example JWT Authentication Flow

1. **User logs in** with username and password.
2. **Server returns JWT** on successful authentication.
3. **Client stores JWT** (e.g., in local storage).
4. **Client sends JWT** in `Authorization: Bearer <token>` header for all subsequent requests.

### Password Security

- User passwords are hashed using **BCrypt** before storage for enhanced security.

## Project Structure

```

src/
main/
java/
com.example.ecommerce/
controller/
service/
repository/
model/
security/
resources/
application.properties

```
