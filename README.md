# Shop API
This application is a REST API to manage shop products.

# Technologies used
- Java 21
- Spring Boot 3.2
- ModelMapper
- JUnit 5
- Mockito 5.7
- Postgres 16
- Liquibase 4.24
- Docker compose
- SwaggerUI

# Launch project

## With Docker
- Install Java 21
- Install Docker
- Install Docker compose v2
- Clone the project into your local directory
- At the project root, run :
```bash
docker compose -f ./docker-compose.yml up -d
```
This command will start a postgres container and api container.

The API is accessible via the port **8096**.

The database is accessible via the port **5442**. You can access database with following credentials

```
User : postgres Password : postgres
```

# Use the API

You can request the local API with Postman or an equivalent tool via url :
http://localhost:8096/

A swagger is also avaible at URL : http://localhost:8096/swagger-ui/index.html

## Available operation
| Resource           | POST                  | GET                            | PATCH                                    | PUT | DELETE           |
| ------------------ | --------------------- | ------------------------------ | ---------------------------------------- | --- | ---------------- |
| **/products**      | Create a new products | Retrieve all products          | X                                        | X   |     X            |
| **/products/1**    | X                     | Retrieve details for product 1 | Update details of product 1 if it exists | X   | Remove product 1 |


# Architecture

The Architecture is inspired from Vertical Slice Architecture (VSA). 
It is a feature based architecture, that group files related to the same feature together.