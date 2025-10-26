# Contact Directory API

A RESTful API for managing contacts, users, and groups built with Spring Boot and MySQL.

## Features

- **User Management**: Create, read, update, and delete users
- **Contact Management**: Manage contacts with name, email, and phone information
- **Group Management**: Organize contacts into groups
- **Contact-Group Associations**: Assign contacts to multiple groups
- **Search Functionality**: Search contacts by name or email
- **User-specific Operations**: Filter contacts and groups by user

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **MySQL 8.0**
- **Lombok**
- **Maven**

## Prerequisites

- JDK 21 or higher
- MySQL 8.0 or higher
- Maven 3.9.11 or higher (or use included Maven Wrapper)

## Database Setup

1. Create a MySQL database:
```sql
CREATE DATABASE contact_directory;
```

2. Update database credentials in [`application.properties`](contact_directory/contact_directory/src/main/resources/application.properties):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/contact_directory
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Installation & Running

### Using Maven Wrapper (Recommended)

**Windows:**
```cmd
mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

### Using Maven

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### User Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users` | Create a new user |
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |

### Contact Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/contacts/user/{userId}` | Add contact for user |
| GET | `/api/contacts` | Get all contacts |
| GET | `/api/contacts/{id}` | Get contact by ID |
| GET | `/api/contacts/user/{userId}` | Get contacts by user |
| GET | `/api/contacts/search?keyword={keyword}` | Search contacts |
| PUT | `/api/contacts/{id}` | Update contact |
| DELETE | `/api/contacts/{id}` | Delete contact |

### Group Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/groups/user/{userId}` | Create group for user |
| GET | `/api/groups` | Get all groups |
| GET | `/api/groups/{id}` | Get group by ID |
| GET | `/api/groups/user/{userId}` | Get groups by user |
| PUT | `/api/groups/{id}` | Update group |
| DELETE | `/api/groups/{id}` | Delete group |

### Contact-Group Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/contact-group/contact/{contactId}/groups/{groupId}` | Assign contact to group |
| GET | `/api/contact-group/groups/{groupId}` | Get contacts in group |
| GET | `/api/contact-group/contacts/{contactId}` | Get groups for contact |

## Request/Response Examples

### Create User
```json
POST /api/users
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

### Create Contact
```json
POST /api/contacts/user/1
{
  "name": "Jane Smith",
  "email": "jane.smith@example.com",
  "phone": "+1234567890"
}
```

### Create Group
```json
POST /api/groups/user/1
{
  "name": "Family",
  "description": "Family contacts"
}
```

## Project Structure

```
src/main/java/com/example/contact_directory/
├── controller/       # REST controllers
├── service/          # Business logic
├── repository/       # Data access layer
├── entity/          # JPA entities
├── dto/             # Data Transfer Objects
├── mapper/          # Entity-DTO mappers
└── exception/       # Custom exceptions
```

## Key Classes

- [`ContactController`](contact_directory/contact_directory/src/main/java/com/example/contact_directory/controller/ContactController.java) - Handles contact-related HTTP requests
- [`UserService`](contact_directory/contact_directory/src/main/java/com/example/contact_directory/service/UserService.java) - User business logic
- [`ContactService`](contact_directory/contact_directory/src/main/java/com/example/contact_directory/service/ContactService.java) - Contact business logic
- [`GroupService`](contact_directory/contact_directory/src/main/java/com/example/contact_directory/service/GroupService.java) - Group business logic
- [`EntityMapper`](contact_directory/contact_directory/src/main/java/com/example/contact_directory/mapper/EntityMapper.java) - Entity-DTO conversion

## Error Handling

The API uses [`GlobalExceptionHandler`](contact_directory/contact_directory/src/main/java/com/example/contact_directory/exception/GlobalExceptionHandler.java) for centralized exception handling:

- `404 Not Found` - Resource not found
- `400 Bad Request` - Invalid input (e.g., duplicate email)
- `500 Internal Server Error` - Unexpected errors

## Development

### Build Project
```bash
mvn clean package
```

### Run Tests
```bash
mvn test
```

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

Created as a demo Spring Boot application for contact management.