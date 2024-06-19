# Shop API
This application is an API to manage shop products.

# Technologies used
- Java 21
- Spring Boot 3.2
- Postgres 16
- Liquibase 4.24

# Database
Go to **db/** folder then run :
```bash
docker compose -f ./docker-compose.yml up -d
```
This command will start a postgres container accessible via the port 5442.
The root user is **postgres** and its password is **postgres**