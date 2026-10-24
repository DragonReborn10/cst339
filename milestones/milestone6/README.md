# Milestone 6

- Author: John Dearing
- Date: 10/24/2026

## Introduction

This is **Milestone 6**

- In Milestone 6 of the Soldier Management System (SMS) for CST-339. After developing the Soldier Display, Search, Update and Delete operations in Milestone 5, Milestone 6 aims to implement **Spring Security framework** in the existing Soldier Management System. For this milestone, the previous approach of authentication via `HttpSession` has been substituted by Spring Security authentication and authorization. This implementation includes database-based authentication, BCrypt encoding of passwords, secured routes of the application, CSRF protection and secure session handling.

- All registered users will be authenticated based on their credentials that will be saved in the MySQL database. Before being saved to the database, all passwords will be encoded by `BCryptPasswordEncoder`. User details will be obtained by `DatabaseUserDetailsService` class from the database. All routes related to the Dashboard and Soldier Management will be restricted to only authenticated users. All unauthenticated users that try to access these pages will be automatically redirected to the Login page. Login and Registration will remain publicly available.

- In Spring Security, there is also logout through which the session gets invalidated and users get redirected to Login page. All functionalities provided in Soldier Management in Milestone 5 have been kept intact, i.e., Soldier creation, display, search, update, and delete functions. Java 17, Spring Boot, Spring Security, Spring MVC, Thymeleaf, Bootstrap, Jakarta Bean Validation, Spring Beans, constructor injection, Spring JDBC/`JdbcTemplate`, MySQL, Maven, and the Controller → Service → DAO → Database structure have all been kept as is. The application still has its N-Layer architecture but now also has the Spring Security as another security layer before processing requests to Spring MVC controllers.

**Pages in Milestone 6:**
- `/` — Home page
- `/login` — Login page, with authentication handled by Spring Security
- `/register` — User Registration page
- `/dashboard` — Protected Dashboard page
- `/soldiers/create` — Protected Create Soldier page
- `/soldiers` — Protected Soldier Display and Search page
- `/soldiers/edit/{soldierId}` — Protected Edit Soldier page

## Code Review and Documentation

Overall project structure, naming convention, input validation, Spring MVC implementation, Spring Security configuration, dependency injection, separation of concerns, database interaction, and secure authentication process were analyzed for the Milestone 6 source code. The primary goal of Milestone 6 is the introduction of the Spring Security into the application in place of current temporary session-based authentication solution without losing any functionality of Soldier Management System. The `SecurityConfig` class contains all of the application's security configurations including public and secured pages, custom authentication process, logout mechanism, and BCrypt password encoding. The `DatabaseUserDetailsService` class extends Spring Security `UserDetailsService` interface and gets the credentials of registered users from the MySQL database via `UserDataService` class. The `RegistrationService` class makes use of Spring Security `PasswordEncoder` interface to encode user passwords before storing them in the database. In such way, the password storage mechanism was changed from storing passwords in plain text to password encoding. As the development process is being done on an individual basis, the code review is conducted by me. JavaDoc and inline comments were added or modified for the involved classes and methods.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone6 analysisPlanning.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/analysisPlanning.md)
- [Milestone6 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/design.md)
- [Milestone6 projectStatus.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/projectStatus.md)
- [Milestone6 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/test.md)
