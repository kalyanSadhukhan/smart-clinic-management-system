# Smart Clinic Management System

## Overview
A comprehensive clinic management system built with Spring Boot, MySQL, and Vanilla web technologies.

## Requirements
- Java 17
- Maven
- MySQL 8.0
- Docker & Docker Compose

## How to run locally
1. Clone the repository.
2. Ensure MySQL is running on port 3306 with root/root credentials.
3. Open terminal in `backend/` and run `mvn spring-boot:run`.
4. Open the `frontend/*.html` files in your browser.

## How to run with Docker
1. Open terminal in the root directory.
2. Run `docker-compose up --build`.

## Features
- JWT Authentication and Role Based Access Control
- Doctor Management (Admin)
- Appointment Booking (Patient)
- Prescription Management (Doctor)
- Search Doctors by Specialization
- Stored Procedures for Reporting

## License
MIT
