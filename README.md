# Library Management Project

This repository contains a simple library domain model that is used in two different runtimes:

- A Spring Boot REST API backed by MySQL for storing and retrieving books.
- An interactive console program that simulates borrowing rules in a small library.

Both runtimes share the same Maven project and are launched from the `Main` class with different command line arguments.

## Prerequisites
- Java 17 (matching the version declared in `pom.xml`).
- Maven 3.9+ (the bundled `mvnw` wrapper works out-of-the-box).
- Docker & Docker Compose (only required if you want to run the REST API locally with MySQL).

## Building the project
```bash
./mvnw clean package
```
The build produces `target/groupassignment-1.0-snapshot.jar`, which can be used to launch either runtime described below.

## Running the REST API
1. Start the database and (optionally) Adminer for inspecting data:
   ```bash
   docker compose up -d
   ```
   This spins up MySQL on `localhost:3306` using the credentials in `src/main/resources/application.properties` (`jensen` / `devops25`). Adminer becomes available at http://localhost:8081.

2. Launch the application in API mode:
   ```bash
   java -jar target/groupassignment-1.0-snapshot.jar 1
   ```
   The API listens on `http://localhost:8080` and exposes the following endpoints:
   - `GET /books` – list all persisted books.
   - `POST /books` – add a new book. Example request:
     ```bash
     curl -X POST http://localhost:8080/books \
       -H "Content-Type: application/json" \
       -d '{"name": "The Hobbit", "genre": "Fantasy"}'
     ```

   A helper script, `books_api.sh`, demonstrates how to create a couple of records and fetch them back:
   ```bash
   ./books_api.sh
   ```

## Running the console program
The console option showcases the same domain logic in a text UI where you can borrow, return, and extend loans interactively.

```bash
java -jar target/groupassignment-1.0-snapshot.jar 2
```
You will be prompted with single-letter commands:
- `B` – borrow a book from the in-memory stock list.
- `R` – return a borrowed book and see any late fees owed.
- `L` – list the books you currently have on loan.
- `A` / `G` – list borrowed books filtered by author or genre.
- `E` – extend a loan (counts as your borrow action for the day).
- `Q` – quit the program.

The program advances the simulated day after each action, which is important for enforcing the borrowing limits that the accompanying tests describe.

## Running the tests
Execute the JUnit 5 suite with:
```bash
./mvnw test
```
The tests document the intended borrowing rules (daily borrowing limit, overdue fees, etc.) even where implementations are still evolving.

## Project structure
- `src/main/java/dev/jensendev25/groupassignment/springboot` – Spring Boot REST API components (entity, repository, service, controller).
- `src/main/java/dev/jensendev25/groupassignment/junit` – Domain classes used by the console program and unit tests.
- `src/main/resources/application.properties` – Database configuration for the API runtime.
- `books_api.sh` – Sample cURL script for interacting with the REST endpoints.
- `docker-compose.yml` – Local MySQL + Adminer stack compatible with the default application properties.

## Troubleshooting
- Ensure MySQL is running before starting the REST API; connection failures will appear on startup otherwise.
- If ports 8080, 8081, or 3306 are already in use, adjust them in `docker-compose.yml` and `application.properties` accordingly.
- The console application keeps all state in memory; restart it to reset inventory and borrowing history.
