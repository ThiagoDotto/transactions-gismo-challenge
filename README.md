
This REST application was created to manage Accounts and Transactions.

## Technologies
- Java 11
- Maven
- Spring Boot
- Docker
- Docker-Compose
- Postgres
- Flyway
- Swagger


## Getting Started
```SHELL
> git clone https://github.com/ThiagoDotto/transactions-gismo-challenge
```

### Running with docker
___

Para execultar o projeto localmente é preciso execultar via docker. 
Ele irá subir 2 containers contendo,
Postrgress execultando na porta 5432 e a aplicação na porta 8081.
    
Para subir os containers vamos acessar via console a pasta onde o projeto está localizado e execultar:
```
docker-compose build
```
```
docker-compose up
```

## Running Tests
Apois o docker subir. Podemos exeultar os testes com o comando:

```SHELL
> ./mvnw test
```

- When the project is up you can see the endpoints documented here: http://localhost:8080/swagger-ui/index.html

### Account Endpoints:
- To create an account: POST `/accounts`
- To find an account by its ID: GET `/accounts/{id}`

### Transaction Endpoint:
- To create a transaction: POST `/transactions`
