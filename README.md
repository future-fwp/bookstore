# Bookstore Spring Boot Application

Welcome to the **Bookstore Spring Boot Application**! This is a simple project demonstrating a CRUD (Create, Read, Update, Delete) application for managing a bookstore using Spring Boot, H2 in-memory database, and RESTful API endpoints.

## Table of Contents

-  [Overview](#overview)
-  [Features](#features)
-  [Prerequisites](#prerequisites)
-  [Setup and Installation](#setup-and-installation)
-  [Running the Application](#running-the-application)
-  [Interacting with the H2 Database](#interacting-with-the-h2-database)
-  [API Endpoints](#api-endpoints)
-  [Troubleshooting](#troubleshooting)
-  [Contribution](#contribution)

## Overview

This Bookstore app allows users to manage a collection of books. It provides REST API endpoints for performing CRUD operations like adding, viewing, updating, and deleting books.

## Features

-  **Create** a new book record.
-  **Read** (view) all book records or a specific book by ID.
-  **Update** an existing book record.
-  **Delete** a book record.
-  **H2 Console** for in-memory database access.

## Prerequisites

Before you begin, ensure you have met the following requirements:

-  **Java Development Kit (JDK)** version 17 or above.
-  **Maven** for dependency management.
-  **Git** for version control (optional).

## Setup and Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/your-username/bookstore-spring-boot.git
   cd bookstore-spring-boot
   ```

2. **Build the project** using Maven:

   ```bash
   mvn clean install
   ```

## Running the Application

1. **Start the application** using the following Maven command:

   ```bash
   mvn spring-boot:run
   ```

2. Alternatively, you can **run the JAR file** if it is already built:

   ```bash
   java -jar target/bookstore-0.0.1-SNAPSHOT.jar
   ```

3. The application will start at:

   ```
   http://localhost:8080
   ```

## Interacting with the H2 Database

1. Access the **H2 Console** by navigating to:

   ```
   http://localhost:8080/h2-console
   ```

2. Use the following details to log in:

   -  **JDBC URL**: `jdbc:h2:mem:testdb`
   -  **Username**: `sa`
   -  **Password**: (leave it blank)

3. You can now interact with the `BOOK` table using SQL queries, like:

   ```sql
   SELECT * FROM BOOK;
   ```

4. To **add a new book** directly in the H2 console, use:

   ```sql
   INSERT INTO BOOK (id, title, author, price) VALUES (1, 'Spring Boot in Action', 'Craig Walls', 29.99);
   ```

## API Endpoints

Here are the REST API endpoints you can use to interact with the application:

### 1. **Get All Books**

-  **GET** `books`
-  Example: `curl -X GET http://localhost:8080/books`

### 2. **Get a Book by ID**

-  **GET** `books/{id}`
-  Example: `curl -X GET http://localhost:8080/books/1`

### 3. **Create a New Book**

-  **POST** `/books`
-  **Body** (JSON):

   ```json
   {
   	"title": "Spring Boot in Action",
   	"author": "Craig Walls",
   	"price": 29.99
   }
   ```

-  Example:

   ```bash
   curl -X POST -H "Content-Type: application/json" -d '{"title":"Spring Boot in Action","author":"Craig Walls","price":29.99}' http://localhost:8080/books
   ```

### 4. **Update an Existing Book**

-  **PUT** `/books/{id}`
-  **Body** (JSON):

   ```json
   {
   	"title": "Spring Boot in Action - Updated",
   	"author": "Craig Walls",
   	"price": 32.99
   }
   ```

-  Example:

   ```bash
   curl -X PUT -H "Content-Type: application/json" -d '{"title":"Spring Boot in Action - Updated","author":"Craig Walls","price":32.99}' http://localhost:8080/api/books/1
   ```

### 5. **Delete a Book**

-  **DELETE** `/api/books/{id}`
-  Example:

   ```bash
   curl -X DELETE http://localhost:8080/api/books/1
   ```

## Troubleshooting

If you encounter any issues, here are a few tips:

-  **Error: Port 8080 is already in use**  
   Solution: Stop the service running on port 8080 or change the port in `application.properties`.

-  **H2 Console not accessible**  
   Ensure that the app is running, and navigate to `http://localhost:8080/h2-console`. Verify the JDBC URL and credentials.

-  **Database issues**  
   If you encounter a primary key violation, use unique `id` values or reset the database by restarting the application.

## Contribution

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch for your feature/bug fix.
3. Commit your changes.
4. Push to the branch.
5. Submit a pull request.
