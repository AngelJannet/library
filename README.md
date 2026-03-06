# Library API

Spring Boot REST API for managing books, authentication, and user favorites with JWT-based security.

## Tech Stack
- Java 17
- Spring Boot 3.3
- Spring Web, Spring Security, Spring Data JPA
- PostgreSQL
- Flyway migrations
- Maven

## Security Model
- Public:
  - `POST /api/auth/register`
  - `POST /api/auth/login`
  - `GET /api/books`
  - `GET /api/books/{id}`
- Admin only (`ROLE_ADMIN`):
  - `POST /api/books`
  - `DELETE /api/books/{id}`
  - `GET /api/books/export`
- Logged-in user (`ROLE_USER`) only:
  - `POST /api/favorites?bookId={id}`
  - `DELETE /api/favorites/{bookId}`
  - `GET /api/favorites` (returns only current user favorites)

JWT must be sent as:

```http
Authorization: Bearer <token>
```

## Prerequisites
- JDK 17+
- Maven 3.9+
- PostgreSQL running locally

## Database Configuration
Current config is in [src/main/resources/application.yml](/C:/Users/janne/IdeaProjects/library/src/main/resources/application.yml):

- URL: `jdbc:postgresql://localhost:5432/library`
- Username: `library_admin`
- Password: `admin`

Create DB and user (example):

```sql
CREATE DATABASE library;
CREATE USER library_admin WITH PASSWORD 'admin';
GRANT ALL PRIVILEGES ON DATABASE library TO library_admin;
```

Flyway migrations run on startup from `src/main/resources/db/migration`.

## Run

```bash
mvn spring-boot:run
```

App runs on `http://localhost:8080`.

## Quick API Examples

Register:

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jane","lastName":"Doe","email":"jane@example.com","password":"secret123"}'
```

Login:

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"jane@example.com","password":"secret123"}'
```

Create book (admin token required):

```bash
curl -X POST http://localhost:8080/api/books \
  -H "Authorization: Bearer <ADMIN_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{"title":"Dune","description":"Sci-fi classic","publishedYear":1965,"authorIds":[1],"genreIds":[1]}'
```

Get books (public):

```bash
curl http://localhost:8080/api/books
```

Add favorite (user token required):

```bash
curl -X POST "http://localhost:8080/api/favorites?bookId=1" \
  -H "Authorization: Bearer <USER_TOKEN>"
```

Get my favorites (user token required):

```bash
curl http://localhost:8080/api/favorites \
  -H "Authorization: Bearer <USER_TOKEN>"
```

Export CSV (admin token required):

```bash
curl -X GET http://localhost:8080/api/books/export \
  -H "Authorization: Bearer <ADMIN_TOKEN>" -o books.csv
```

## Important Notes
- New users are registered with `USER` role by default.
- `ADMIN` role exists in migrations, but assigning admin to a user currently requires DB/manual service-level operation.
- JWT signing key is generated at runtime in [JwtService.java](/C:/Users/janne/IdeaProjects/library/src/main/java/com/example/library/security/JwtService.java), so tokens become invalid after app restart.
