# Invoice Manager Server

This is the server for the Invoice Manager project. It is a Spring Boot application that provides a reactive REST API for managing invoices. The server uses the [invoice-manager-api](https://github.com/TTomczek/invoice-manager-api) library to communicate with the client. This is a project for the course "Java Enterprise Edition" at the [South Westphalia University of Applied Sciences](https://www.fh-swf.de/en/international_3/index.php)

## Building the project

### Requirements
    1. Java 17
    3. [invoice-manager-api](https://github.com/TTomczek/invoice-manager-api)
    4. Docker

### Docker
Used for the database and the authorization server (Keycloak).

    docker compose up -d

### Starting the server
You need to set the environment variable `MARIADB_ROOT_PASSWORD`to the password of the database root user.

Windows:
    1. Run `./mvnw.cmd clean install` in the project directory
    2. Run `./mvnw.cmd spring-boot:run` to start the server

Linux:
    1. Run `./mvnw clean install` in the project directory
    2. Run `./mvnw spring-boot:run` to start the server
