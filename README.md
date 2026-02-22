# Issue Tracker API

A simple RESTful API for managing issues, built with **Spring Boot** and **Java**.

---

## Tech Stack

- Java 25
- Spring Boot 4.0.3
- Spring Data JPA
- Spring Validation
- Maven
- MySQL

---

## Project Structure

```
src/main/java/com/main/
├── controller/       # REST controllers
├── service/          # Business logic
├── repository/       # Data access layer
├── entity/           # JPA entities
└── enums/            # Enums (IssueStatus)
```

---

## Getting Started

### Prerequisites

- Java 25
- Maven
- A running MySQL instance (MySQL Workbench)

### Database Setup

Run the following SQL in MySQL Workbench to create the table or just run the backend it will auto create it:

```sql
CREATE TABLE IF NOT EXISTS issues (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status ENUM('OPEN', 'IN_PROGRESS', 'CLOSED') NOT NULL DEFAULT 'OPEN',
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
```

### Configuration

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/issues
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Run the App

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## API Endpoints

| Method   | Endpoint                       | Description              |
|----------|--------------------------------|--------------------------|
| `GET`    | `/api/issues`                  | Get all issues           |
| `GET`    | `/api/issues/{id}`             | Get issue by ID          |
| `GET`    | `/api/issues/status/{status}`  | Get issues by status     |
| `POST`   | `/api/issues`                  | Create a new issue       |
| `PATCH`  | `/api/issues/{id}/status`      | Update issue status      |
| `DELETE` | `/api/issues/{id}`             | Delete an issue          |

---

## Request & Response Examples

### Create an Issue - `POST /api/issues`

**Request Body:**
```json
{
  "title": "Fix login bug",
  "description": "Users can't log in with correct credentials",
  "status": "IN_PROGRESS"
}
```

### Update Issue Status - `PATCH /api/issues/{id}/status`

```
PATCH /api/issues/1/status?status=IN_PROGRESS
```

**Valid status values:** `OPEN`, `IN_PROGRESS`, `CLOSED`

---

## Validation Rules

- `title` - required, max 255 characters
- `description` - optional
- `status` - must be one of `OPEN`, `IN_PROGRESS`, `CLOSED`

---

## License

This project is for the backend Take-Home Assignment.
