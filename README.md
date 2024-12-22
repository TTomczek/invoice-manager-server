# Invoice Manager Server

This is the server for the Invoice Manager project. It is a Spring Boot application that provides a reactive REST API for managing invoices. The server uses the [invoice-manager-api](https://github.com/TTomczek/invoice-manager-api) library to communicate with the client. This is a project for the course "Java Enterprise Edition" at the [South Westphalia University of Applied Sciences](https://www.fh-swf.de/en/international_3/index.php)

## Quickstart
    1. Run invoice-manager-server-0.0.1-SNAPSHOT.jar with the following command: `java -jar invoice-manager-server-0.0.1-SNAPSHOT.jar`
    2. The server will be available at `http://localhost:8080`

## Building the project

### Requirements
    1. Java 21
    2. Maven 3.8.1
    3. [invoice-manager-api](https://github.com/TTomczek/invoice-manager-api)

Windows:
    1. Run `mvn clean install` in the project directory
    2. Run `mvnw spring-boot:run` to start the server

### Credentials
    Keycloak admin
        Username: admin
        Password: admin

    Manager User
        Username: manager
        Password: manager

    Employee User
        Username: employee
        Password: employee

## Server profiles
    develop: Deactivates the security features of the server
    localfilestorage: Stores files on the local file system
    h2: Uses a file based H2 database
    mariadb: Uses a MariaDB database
    standard: use the standard invoice/pdf generator

## API Documentation
The API documentation is available at `http://localhost:8080/swagger-ui.html`
