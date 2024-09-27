# Invoice Manager Server


## Building the project

### Requirements
    1. Java 17
    2. Maven
    3. [invoice-manager-api](https://github.com/TTomczek/invoice-manager-api)
    4. Docker

### Docker
Used for the database and the authorization server (Keycloak).

    docker compose up -d

### Starting the server
You need to set the environment variable `POSTGRES_USER` and `POSTGRES_PASSWORD` to the username and password of the database user.

    1. Run `mvn clean install` in the project directory
    2. Run `mvn spring-boot:run` to start the server

