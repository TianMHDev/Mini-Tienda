# MiniTiendaJDBC - Inventory Management System

This project is a simple, command-line-based inventory management system for a small store. It allows users to add, list, search, update, and delete products from the inventory. The application is built with Java and uses a MySQL database for data persistence.

## Prerequisites

Before you begin, ensure you have the following installed:
- **Java 17** or higher
- **Maven 3.x** or higher
- **MySQL 8.x** or higher

## Setup Instructions

### 1. Database Configuration

1. **Create the database:**
   - Open your MySQL client and create a new database named `mini_tienda`.
     ```sql
     CREATE DATABASE mini_tienda;
     ```

2. **Create the `productos` table:**
   - Use the following SQL script to create the required table.
     ```sql
     USE mini_tienda;

     CREATE TABLE productos (
         id INT AUTO_INCREMENT PRIMARY KEY,
         nombre VARCHAR(255) NOT NULL,
         precio DECIMAL(10, 2) NOT NULL,
         stock INT NOT NULL
     );
     ```

3. **Configure database credentials:**
   - Navigate to `src/main/resources/db.properties` and update the `user` and `password` fields with your MySQL credentials.
     ```properties
     url=jdbc:mysql://localhost:3306/mini_tienda
     user=your_mysql_username
     password=your_mysql_password
     driver=com.mysql.cj.jdbc.Driver
     ```

### 2. Build the Project

- Open a terminal in the project's root directory and run the following Maven command to build the project and download the required dependencies.
  ```bash
  mvn clean install
  ```

## How to Run the Application

- After building the project, you can run the application using the following command in the terminal:
  ```bash
  mvn exec:java -Dexec.mainClass="app.MiniTiendaApp"
  ```
- This will launch the application, and you can interact with the inventory management system through the command-line interface.

## Core Components

- **`MiniTiendaApp.java`**: The main entry point of the application, which handles user interactions and menu navigation.
- **`Producto.java`**: The data model for a product, representing the structure of the `productos` table.
- **`ServicioInventarioImpl.java`**: The service layer that implements the business logic for inventory management.
- **`ProductoRepositorioImpl.java`**: The repository layer responsible for database interactions.
- **`pom.xml`**: The Maven configuration file that defines project dependencies and build settings.

### Function to Import

To run this application, you need to execute the `main` method in the `app.MiniTiendaApp` class. No specific functions need to be imported if you are running the application as a whole.