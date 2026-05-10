# Architecture Documentation

## System Architecture
The Smart Clinic Management System is a typical 3-tier architecture:
1. **Frontend**: HTML/CSS/JS communicating via REST APIs.
2. **Backend**: Spring Boot providing RESTful services, secured with JWT.
3. **Database**: MySQL for structured relational data (users, appointments) and MongoDB for document-based logs (optional).

## Technologies
- Java 17
- Spring Boot 3.2.x
- Spring Security + JWT
- Spring Data JPA
- MySQL 8.0
- MongoDB
- Docker & Docker Compose
- GitHub Actions (CI)

## Security
- BCrypt is used for password hashing.
- Role-based access control (RBAC) with `ROLE_ADMIN`, `ROLE_DOCTOR`, and `ROLE_PATIENT`.
- All secured API endpoints require a valid JWT passed in the `Authorization` header.
