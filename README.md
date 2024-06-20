# Shop API
This application is a REST API to manage shop products.

# Technologies used
- Java 21
- Spring Boot 3.2
- Postgres 16
- Liquibase 4.24
- JUnit 5
- Mockito 5.7

# Launch project

## Required
- Docker
- Docker compose

At the project root, run :
```bash
docker compose -f ./docker-compose.yml up -d
```
This command will start a postgres container and api container.

## Database

The database is accessible via the port 5442.
The root user is **postgres** and its password is **postgres**

## API
The API is accessible via the port 8086

# Use the API

You can request the local API with Postman or an equivalent tool via url :
http://localhost:8096/products/

A swagger is also avaible at URL : http://localhost:8096/swagger-ui/index.html

## Available operation
| Resource           | POST                  | GET                            | PATCH                                    | PUT | DELETE           |
| ------------------ | --------------------- | ------------------------------ | ---------------------------------------- | --- | ---------------- |
| **/products**      | Create a new products | Retrieve all products          | X                                        | X   |     X            |
| **/products/1**    | X                     | Retrieve details for product 1 | Update details of product 1 if it exists | X   | Remove product 1 |


