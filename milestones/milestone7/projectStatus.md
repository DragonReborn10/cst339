# Milestone 7 Project Status

- Author: John Dearing
- Date: 10/31/2026

## Tasks Completed

1. Retained all functionality from Milestone 6, including Home, Registration, Login, Dashboard, and Soldier Create, Display, Search, Update, and Delete operations.
2. Preserved the Spring Security framework implemented in Milestone 6, including database-backed authentication, BCrypt password hashing, protected application routes, CSRF protection, secure Logout, and session management.
3. Implemented REST API functionality in the existing Soldier Management System using Spring Boot and Spring MVC.
4. Treated Soldier records as the products required by the Milestone 7 assignment, allowing the existing Soldier model, service, DAO, and MySQL database table to be reused without creating separate Product classes or tables.
5. Created `SoldierRestController.java` using Spring MVC's `@RestController` annotation to handle REST API requests and return JSON responses.
6. Implemented REST API 1, `GET /api/soldiers`, to retrieve all Soldier records from the MySQL database and return them as a JSON array.
7. Implemented REST API 2, `GET /api/soldiers/{soldierId}`, to retrieve a specific Soldier record using the requested Soldier ID and return the result as a JSON object.
8. Configured the Soldier-by-ID REST API to return HTTP `404 Not Found` when the requested Soldier does not exist.
9. Used `ResponseEntity` to return appropriate HTTP response status codes and JSON response bodies from the REST controller.
10. Preserved Soldier IDs as String values to match the existing `SoldierModel`, `SoldierService`, and database implementation.
11. Updated `SecurityConfig.java` to introduce an additional Spring Security filter chain specifically for `/api/**` requests.
12. Configured HTTP Basic authentication for REST API requests using Spring Security to meet the Milestone 7 security requirement.
13. Configured the REST API security filter chain with `@Order(1)` so that API security rules are evaluated before the existing web application security configuration.
14. Preserved the existing web application security filter chain with `@Order(2)`, maintaining form-based Login, Logout, session management, and protected web pages.
15. Configured REST API authentication using `SessionCreationPolicy.STATELESS`, requiring REST clients to provide valid credentials with each request.
16. Configured the REST API security filter chain to disable CSRF protection for stateless API requests while preserving CSRF protection for the existing browser-based application.
17. Reused `DatabaseUserDetailsService`, which implements Spring Security's `UserDetailsService` interface, to authenticate REST API users against the existing MySQL database.
18. Reused `UserDataService.findByUsername()` to retrieve registered user credentials from the MySQL `users` table.
19. Preserved BCrypt password encoding and verification through Spring Security's `PasswordEncoder` interface and `BCryptPasswordEncoder`.
20. Configured REST API requests without valid authentication credentials to receive HTTP `401 Unauthorized` responses.
21. Preserved the existing N-Layer architecture: Controller → Business Service → DAO → JdbcTemplate → MySQL Database.
22. Reused the existing `SoldierService` and `SoldierDataService` classes to retrieve Soldier records without duplicating business logic or database queries.
23. Preserved Spring JDBC/`JdbcTemplate` database access and parameterized SQL queries for user authentication and Soldier Management operations.
24. Preserved Soldier Search functionality for exact and partial Soldier IDs, first names, last names, and full names.
25. Preserved the Soldier ID as the immutable primary key during Update operations.
26. Preserved the existing MySQL database schema, including the `formations`, `users`, and `soldiers` tables, without requiring a new Product table.
27. Preserved the direct `soldiers.unit` mapping used by the existing Soldier Create and Edit workflows.
28. Added `SoldierRestControllerTest.java` with automated Spring Security test cases for unauthenticated REST API requests and invalid HTTP Basic credentials.
29. Updated the Milestone 7 README, Analysis Planning, and Design documentation to describe REST API functionality, security configuration, implementation decisions, and testing requirements.

## Known Issues

- The Milestone 7 REST APIs currently support retrieving Soldier records only. REST API endpoints for creating, updating, and deleting Soldiers have not been implemented. These operations remain available through the existing Spring MVC web application.
- The REST APIs use HTTP Basic authentication. HTTP Basic credentials are Base64-encoded rather than encrypted by the authentication scheme itself, so HTTPS is required when using these APIs outside the local development environment.
- Existing user accounts created before Milestone 6 may contain plaintext passwords. These accounts must be migrated to BCrypt or have their passwords reset before they can authenticate successfully through Spring Security.
- The current security configuration assigns the default `USER` role to authenticated accounts but does not implement administrator roles or role-based permissions for REST API or Soldier Management operations.
- Any authenticated user can currently retrieve Soldier records through the REST APIs. Formation-level authorization and more restrictive access controls have not been implemented.
- Any authenticated user can currently view, create, update, and delete available Soldier records through the web application. Role-based authorization remains a future enhancement.
- The REST APIs currently return JSON representations of the existing `SoldierModel`. Dedicated Data Transfer Objects (DTOs) have not been implemented to restrict which Soldier properties are exposed.
- The REST API does not currently support pagination, sorting, or advanced filtering. The `GET /api/soldiers` endpoint retrieves the available Soldier records as a list.
- The REST API uses the existing String-based Soldier ID. API clients must provide a valid Soldier ID that matches a record in the MySQL database.
- The `formation_id` field is not assigned through the current Create/Edit Soldier workflow. The entered Unit continues to be stored directly in `soldiers.unit`.
- Formation Level Access remains a future feature.
- Forgot Username and Forgot Password functionality have not been implemented.
- Account lockout, password reset, email verification, and multifactor authentication are not currently implemented.
- Soldier Search uses database `LIKE` patterns and is intended for a course-scale fictional dataset. Pagination may be necessary for larger datasets.
- The application depends on local MySQL availability for Registration, Login, Soldier CRUD operations, and REST API data retrieval.
- Automated REST API tests have been added for unauthorized access and invalid credentials, but successful authentication, JSON response content, and missing-record behavior require additional test coverage.
- The Milestone 7 Maven build, automated tests, and complete Milestone 6 regression test plan have not yet been verified as successfully executed.
- The final screencast demonstrating the application and REST APIs has not yet been recorded or linked in the Design Report.
- The current implementation is intended for course demonstration and has not been evaluated for production deployment.

## Risks

- HTTP Basic authentication transmits credentials in an encoded format that can be decoded. Without HTTPS, credentials may be intercepted during transmission. HTTPS must be enabled before production deployment.
- REST API endpoints expose Soldier information to any successfully authenticated user. Additional role-based and formation-level authorization should be implemented before handling sensitive or production Soldier information.
- Returning the complete `SoldierModel` as JSON may expose more Soldier information than an API client requires. Dedicated DTOs and field-level access controls should be considered before production deployment.
- Existing plaintext-password accounts cannot authenticate through BCrypt password verification until their passwords are migrated or reset.
- Incorrect Spring Security filter chain ordering may cause REST API requests to use form-based Login instead of HTTP Basic authentication. The `/api/**` security filter chain must be evaluated before the general web application filter chain.
- Incorrect REST API security configuration could allow unauthenticated access to Soldier information. Both REST endpoints must be tested with valid credentials, invalid credentials, and no credentials.
- Disabling CSRF protection globally would weaken security for the existing browser-based application. CSRF protection must remain enabled for web forms and be disabled only for the dedicated stateless REST API security chain.
- MySQL downtime or incorrect database credentials can interrupt Registration, Login, Soldier CRUD operations, and REST API requests.
- REST API requests depend on the existing `SoldierService` and `SoldierDataService` classes. Changes to these shared components could affect both the web application and the REST APIs.
- Returning all Soldier records without pagination may cause performance issues as the database grows
- Invalid or nonexistent Soldier IDs must be handled correctly to prevent unexpected server errors. The Soldier-by-ID API should return HTTP `404 Not Found` when no matching record exists.
- Deleting a Soldier through the existing web application permanently removes the record unless the database has been backed up. Fictional test data should be used, and the selected Soldier ID should be verified before deletion.
- Concurrent updates or deletions may affect the same Soldier record. Existing row-count checks can detect missing records but do not prevent lost updates.
- Database constraints and Jakarta Bean Validation rules must remain aligned to avoid data-integrity errors.
- Changes to the existing Spring Security configuration may unintentionally affect Registration, Login, Logout, or protected Soldier Management pages. The complete Milestone 6 regression test plan must be executed to verify backward compatibility.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone7 README.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/README.md)
- [Milestone7 analysisPlanning.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/analysisPlanning.md)
- [Milestone7 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/design.md)
- [Milestone7 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/test.md)