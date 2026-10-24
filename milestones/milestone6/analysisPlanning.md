# Milestone 6 Analysis Planning

- Author: John Dearing
- Date: 10/24/2026

## Planning Documentation

- Milestone 6 extends the Milestone 5 Soldier Management System (SMS) by integrating the Spring Security framework into the existing Java 17 Spring Boot application.
- Milestone 6 focuses on replacing the previous manual `HttpSession` authentication process with Spring Security authentication and authorization.
- The new implementation introduces database-backed authentication, BCrypt password hashing, protected application routes, CSRF protection, secure Logout, and Spring Security session management.
- The existing Controller → Business Service → DAO → JdbcTemplate → MySQL architecture is preserved. Spring Security adds a security layer that processes HTTP requests before they reach the Spring MVC controllers.
- The existing `formations`, `users`, and `soldiers` tables remain in use. No new database tables are required for the Spring Security implementation.
- The `users.password` column must support the complete BCrypt password hash. Existing accounts containing plaintext passwords must be migrated or have their passwords reset.

| Task | Planned Sequence | Deliverable | Status |
| --- | --- | --- | --- |
| Add Spring Security dependencies | 1 | Spring Security and Spring Security Test dependencies in `pom.xml` | Completed |
| Configure Spring Security | 2 | `SecurityConfig.java` with authentication, authorization, Login, Logout, and CSRF protection | Completed |
| Implement database-backed authentication | 3 | `DatabaseUserDetailsService` implementing `UserDetailsService` | Completed |
| Extend User DAO | 4 | `UserDataService.findByUsername()` for retrieving registered users | Completed |
| Implement BCrypt password hashing | 5 | `RegistrationService` uses `PasswordEncoder` before storing passwords | Completed |
| Update Login workflow | 6 | Spring Security processes `POST /login` instead of the legacy authentication service | Completed |
| Protect application routes | 7 | Dashboard and Soldier CRUD routes require authentication | Completed |
| Remove manual session authentication | 8 | Remove temporary `HttpSession` login checks from controllers | Completed |
| Implement secure Logout | 9 | `POST /logout` with session invalidation and Login redirection | Completed |
| Integrate CSRF protection | 10 | CSRF tokens included in state-changing forms | Completed |
| Preserve Soldier functionality | 11 | Existing Soldier CRUD, Search, Validation, and MySQL persistence retained | Completed |
| Add automated security tests | 12 | JUnit, Spring Security Test, and MockMvc test cases | Prepared; execution pending |
| Execute regression testing | 13 | Verify existing Milestone 5 functionality with Spring Security enabled | Pending verification |
| Package application | 14 | Compile, test, package, and launch executable Spring Boot JAR | Pending verification |
| Update documentation | 15 | README, Analysis Planning, Design, Project Status, Testing, and security screenshots | In progress |

The updated application flow is:

```text
User / Web Browser
        |
        v
Spring Security Filter Chain
        |
        +-- Authentication
        |
        +-- Authorization
        |
        +-- CSRF Protection
        |
        v
Spring MVC Controller
        |
        v
Business Service
        |
        v
Data Service / DAO
        |
        v
Spring JdbcTemplate
        |
        v
MySQL Database
```

## General Technical Approach

- The Soldier Management System is a Java 17 Spring Boot MVC project.
- The application uses Thymeleaf and Bootstrap for the user interface, Jakarta Bean Validation for form validation, Spring-managed beans with constructor-based dependency injection, Spring JDBC for database access, and MySQL for persistent storage.
- Milestone 6 introduces Spring Security to manage user authentication, authorization, session security, CSRF protection, and Logout.
- The previous Login workflow used `LoginController`, `AuthenticationService`, `UserDataService`, and manual `HttpSession` variables.
- The new Login workflow uses Spring Security and a database-backed implementation of `UserDetailsService`.

## Key Technical Decisions

- Use the Spring Security framework instead of continuing manual `HttpSession` authentication checks.
- Create `SecurityConfig.java` to centrally configure authentication, authorization, Login, Logout, password encoding, and CSRF protection.
- Use `SecurityFilterChain` to define public and protected application routes.
- Allow public access to Home, Login, Registration, and static resources.
- Require authentication for Dashboard and all Soldier Management routes.
- Implement `DatabaseUserDetailsService` using Spring Security's `UserDetailsService` interface.
- Reuse `UserDataService` for database-backed user authentication instead of creating a separate user persistence layer.
- Add `findByUsername()` to retrieve registered users from the MySQL database.
- Use `BCryptPasswordEncoder` through the `PasswordEncoder` interface for secure password storage and verification.
- Update `RegistrationService` to encode passwords before inserting user records.
- Allow Spring Security to process `POST /login` instead of performing authentication manually inside `LoginController`.
- Remove the legacy `AuthenticationService` and plaintext-password comparison logic.
- Remove temporary `HttpSession` authentication checks from `DashboardController` and `SoldierController`.
- Use Spring Security to manage authenticated sessions and Logout.
- Use POST-based Logout rather than GET-based Logout.
- Preserve CSRF protection and include valid tokens in state-changing forms.
- Continue using `SoldierModel`, `SoldierController`, `SoldierService`, and `SoldierDataService` for Soldier CRUD functionality.
- Keep all SQL in the `@Repository` data access layer while preserving business logic in the `@Service` layer.
- Continue using parameterized `JdbcTemplate` queries to prevent SQL injection.
- Preserve case-insensitive `LIKE` searches for partial Soldier IDs and names.
- Keep `soldier_id` immutable during editing and use the URL path ID as the authoritative Update target.
- Continue using POST for Delete with a browser confirmation prompt.
- Preserve `@Valid` and `BindingResult` validation for Soldier Create and Update operations.
- Preserve `soldiers.unit` as the user-entered Unit. The `formation_id` field remains unassigned by the current Create/Edit workflow.
- Retain the existing MySQL `formations`, `users`, and `soldiers` tables.
- Ensure the `users.password` column supports BCrypt hashes.
- Assign the default `USER` role to authenticated accounts. Administrative roles and formation-level permissions remain future enhancements.
- Use JUnit, Spring Security Test, and MockMvc to test authentication requirements, route protection, CSRF behavior, and Logout.
- Use Maven Wrapper for compilation, automated testing, and executable JAR packaging.

## Future Enhancements

The following features are planned for future updates:

- Administrator and user role-based authorization.
- Formation-level access restrictions.
- Assignment of Soldiers to formations through `formation_id`.
- Forgot Username functionality.
- Forgot Password and password reset functionality.
- Account lockout after repeated failed Login attempts.
- Multifactor authentication.
- Pagination for large Soldier datasets.
- Additional production security configuration and deployment hardening.
- auto logout after inactivity

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone6 README.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/README.md)
- [Milestone6 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/design.md)
- [Milestone6 projectStatus.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/projectStatus.md)
- [Milestone6 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/test.md)