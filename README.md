# Internet Banking Application Demo

A comprehensive Java-based Internet Banking Application Demo showcasing Spring Boot, JPA, and HATEOAS. 
The project encompasses a secure and scalable architecture, featuring controllers for user, account and user details, along with services for user management, account operations and user details. 
The robust validation mechanism ensures data integrity, while the use of Spring HATEOAS facilitates RESTful interactions.

## Key Components

### Controllers

**UserController:** Manages user-related operations such as retrieval, creation, update and password modification.

**AccountController:** Handles account-related functionalities including retrieval, creation and deletion.

**UserDetailsController:** Manages detailed user information, providing operations for retrieval, creation and deletion.

### Services

**UserService:** Implements user-related business logic, supporting operations like user retrieval, creation, update and password modification.

**AccountService:** Manages account-related functionalities, including retrieval, creation and deletion.

**UserDetailsService:** Handles detailed user information, supporting operations for retrieval, creation, and deletion.

## Security and Validation

- **Spring Data JPA:** Ensures seamless database interactions.
- **Spring HATEOAS:** Facilitates the creation of RESTful services.
- **Spring Validation:** Implements robust input validation for enhanced security.

## Features

The project covers essential banking functionalities such as user and account management. 
It integrates Spring Data JPA for efficient database operations, Spring HATEOAS for RESTful services and Spring Validation to ensure secure and validated data handling. 
Explore the well-structured codebase to understand the implementation of DTO verification, entity mapping and more.
