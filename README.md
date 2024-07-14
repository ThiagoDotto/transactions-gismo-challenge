# REST Application for Managing Accounts and Transactions

This REST application was created to manage Accounts and Transactions.

## Technologies
- Java 17
- Maven
- Spring Boot 3.3.1
- Docker
- Docker-Compose
- PostgreSQL
- Flyway
- Swagger
- RestAssured (for API testing)

## Getting Started

To get started with the project, clone the repository:

```shell
git clone https://github.com/ThiagoDotto/transactions-pismo-challenge
```

## Running with Docker
To run the project locally using Docker, execute:

```shell 
./start.sh 
```
This will build and start the application with all necessary services.

Running Tests
After the Docker containers are up and running, you can execute the tests with the following command:

```shell
./mvnw test
```

This will run both unit and integration tests.

## API Documentation

Once the project is up and running, you can access the API documentation at:

http://localhost:8080/swagger-ui/index.html

### Account Endpoints:
- To create an account: POST `/accounts`
- To find an account by its ID: GET `/accounts/{id}`

### Transaction Endpoint:
- To create a transaction: POST `/transactions`