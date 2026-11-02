# Milestone 8 Analysis Planning

- Author: John Dearing
- Date: 11/01/2026

## Planning Documentation

- Milestone 8 extends the Milestone 7 Soldier Management System (SMS) by implementing role-based access control, formation-level Soldier management, individual Soldier data restrictions, and an administrator-reviewed Soldier deletion request workflow.
- The application continues using Java 17, Spring Boot, Spring MVC, Spring Security, Thymeleaf, Bootstrap, Spring JDBC/JdbcTemplate, Maven, and MySQL.
- Milestone 8 preserves the functionality developed during previous milestones, including Registration, Login, Dashboard, Soldier Create, Display, Search, Update, Delete, and the two REST API endpoints introduced in Milestone 7.
- The primary objective of Milestone 8 is to restrict Soldier data and management operations according to the authenticated user's assigned role and organizational formation.
- The application recognizes five access levels: `ADMIN`, `FULL_TIME_STAFF`, `FIRST_SERGEANT`, `COMMANDER`, and `BASIC`.
- `ADMIN` users can access Soldier records across formations, create Soldiers, edit records, directly delete Soldiers, and review Soldier deletion requests.
- `FULL_TIME_STAFF`, `FIRST_SERGEANT`, and `COMMANDER` users can create, view, and edit Soldiers within their assigned formation. These users cannot directly delete Soldiers and must instead submit a deletion request with a required justification.
- `BASIC` users can view only the Soldier record linked to their account. They cannot create, edit, or delete Soldier records or access formation-level management functionality.
- The Dashboard is updated to display navigation options based on the authenticated user's access level.
- The `AccessService` component is used to enforce role-based and formation-level authorization rules on the server.
- Formation-level access is based on the account's assigned formation and the Soldier's `formation_id`, with unit information used to maintain the correct organizational assignment.
- The `user_access` table stores account-specific access levels, assigned units, and optional Soldier associations.
- The `users` table continues storing account information and BCrypt password hashes and also supports formation associations through `formation_id`.
- The `formations` table stores formation identifiers, names, types, and locations.
- The `soldiers` table stores Soldier information and the `formation_id` used to determine formation-level access.
- The `soldier_delete_requests` table records deletion requests, reasons, request statuses, requesting users, reviewing administrators, review comments, and timestamps.
- Soldier deletion requests remain pending until reviewed by an administrator. Submitting a request does not immediately delete the Soldier.
- The administrator review workflow is designed to support approval and rejection of pending deletion requests.
- Milestone 8 retains the existing Controller → Business Service → DAO → JdbcTemplate → MySQL architecture.
- Spring Security continues providing database-backed authentication, BCrypt password verification, form-based Login, Logout, session management, and CSRF protection.
- The REST API endpoints `GET /api/soldiers` and `GET /api/soldiers/{soldierId}` remain part of the application and continue using HTTP Basic authentication.

## Updated Application Flow

The updated application flow is:

```text
                       Application User
                              |
                              v
                     Spring Security
                              |
                              v
                  Database Authentication
                              |
                              v
                  BCrypt Password Verification
                              |
                              v
                    Authentication Valid?
                       /             \
                      No             Yes
                      |               |
                      v               v
               Login Failure    Role-Based Dashboard
                                      |
                                      v
                              AccessService
                                      |
                 +--------------------+--------------------+
                 |                    |                    |
                 v                    v                    v
               ADMIN             MANAGEMENT               BASIC
                 |                 ROLES                    |
                 |                    |                     |
                 v                    v                     v
         All Soldier Records   Assigned Formation   Assigned Soldier
                 |                    |                     |
          +------+------+       +-----+------+              |
          |      |      |       |     |      |              |
          v      v      v       v     v      v              v
        Create  Edit  Delete  Create Edit Request       View My Soldier
                                            Delete           Data
                                              |
                                              v
                                       Deletion Request
                                              |
                                              v
                                         PENDING
                                              |
                                              v
                                      Administrator
                                          Review
                                       /        \
                                      v          v
                                   Approve     Reject
                                      |          |
                                      v          v
                                   Delete      Retain
                                   Soldier     Soldier
```
The management roles are `FULL_TIME_STAFF`, `FIRST_SERGEANT`, and `COMMANDER`.

## General Technical Approach

- The Soldier Management System remains a Java 17 Spring Boot MVC application.
- Thymeleaf and Bootstrap provide the user interface, while Spring MVC controllers handle browser requests.
- Spring Security authenticates registered users against the MySQL database.
- `DatabaseUserDetailsService` continues loading authentication information from the existing user database.
- `BCryptPasswordEncoder` continues protecting stored passwords.
- `SecurityConfig` retains the application's browser-based and REST API authentication configurations.
- `AccessService` applies role-based and formation-level authorization rules to protected Soldier operations.
- The application uses the authenticated username to identify the user's access level and assigned organizational information.
- The Dashboard conditionally displays available navigation options according to the user's permissions.
- BASIC users are restricted to the Soldier record associated with their account.
- ADMIN users are permitted to access Soldier records across all formations.
- FULL_TIME_STAFF, FIRST_SERGEANT, and COMMANDER users are restricted to Soldier records associated with their assigned formation.
- Soldier creation must validate the requested formation and unit against the authenticated user's authorization.
- Management users cannot directly delete Soldier records. Instead, they submit a deletion request containing the Soldier ID, requesting username, and required reason.
- Deletion requests are stored with a pending status until an administrator reviews them.
- Administrator approval and rejection must update the request's status and review information.
- The existing `SoldierService` continues providing Soldier business operations.
- The existing `SoldierDataService` continues using `JdbcTemplate` for parameterized database queries.
- Soldier IDs remain String values to preserve leading zeros.
- Jakarta Bean Validation continues validating submitted Soldier information.
- REST API endpoints continue returning JSON representations of Soldier records.
- REST API authentication continues using HTTP Basic authentication.

### Role-Based Dashboard Flow

```text
Authenticated User
        |
        v
Determine Assigned Role
        |
        +-- ADMIN
        |     |
        |     +-- View All Soldiers
        |     +-- Create Soldier
        |     +-- Edit Soldier
        |     +-- Direct Delete Soldier
        |     +-- Review Delete Requests
        |
        +-- FULL_TIME_STAFF
        |     |
        |     +-- Formation Level Access
        |     +-- Create Soldier
        |     +-- Edit Authorized Soldier
        |     +-- Request Delete
        |
        +-- FIRST_SERGEANT
        |     |
        |     +-- Formation Level Access
        |     +-- Create Soldier
        |     +-- Edit Authorized Soldier
        |     +-- Request Delete
        |
        +-- COMMANDER
        |     |
        |     +-- Formation Level Access
        |     +-- Create Soldier
        |     +-- Edit Authorized Soldier
        |     +-- Request Delete
        |
        +-- BASIC
              |
              +-- View My Soldier Data
              +-- Logout
```

### BASIC User: Individual Soldier Data Flow

```text
BASIC User Logs In
        |
        v
Spring Security Authentication
        |
        v
Determine Authenticated Username
        |
        v
Look Up User Access Information
        |
        v
Retrieve Linked Soldier ID
        |
        v
Query Authorized Soldier Record
        |
        v
Display View My Soldier Data
        |
        v
Only Assigned Soldier Is Visible
```

### Formation-Level Access Flow

```text
Management User Logs In
        |
        v
Determine Role and Assigned Formation
        |
        v
Open Formation Level Access
        |
        v
AccessService Validates Authorization
        |
        v
SoldierService
        |
        v
SoldierDataService
        |
        v
MySQL soldiers Table
        |
        v
Filter by Authorized Formation
        |
        v
Display Authorized Soldier Records
        |
        +-- Create Soldier
        |
        +-- Edit Soldier
        |
        +-- Request Delete
```

## Key Technical Decisions

- Treat Soldier records as the products required by the Milestone 7 REST API assignment.
- Reuse the existing `soldiers` table instead of creating a separate `products` table.
- Reuse `SoldierModel`, `SoldierService`, and `SoldierDataService` rather than duplicating existing business logic and database access code.
- Create `SoldierRestController.java` to separate REST API functionality from the existing Thymeleaf-based `SoldierController`.
- Use `@RestController` to return JSON responses directly from REST API methods.
- Use `@RequestMapping("/api/soldiers")` to define the REST API base path.
- Use `@GetMapping` to implement the REST API that returns all Soldiers.
- Use `@GetMapping("/{soldierId}")` to implement the REST API that retrieves a specific Soldier.
- Use `@PathVariable String soldierId` to retrieve the requested Soldier ID from the URL.
- Keep Soldier IDs as Strings to match the existing Soldier model and service methods.
- Use `ResponseEntity` to control HTTP response status codes and response bodies.
- Return HTTP `200 OK` when Soldier information is retrieved successfully.
- Return HTTP `404 Not Found` when a requested Soldier does not exist.
- Return HTTP `401 Unauthorized` when REST API requests contain missing or invalid authentication credentials.
- Use Spring Security HTTP Basic authentication to meet the Milestone 7 REST API security requirement.
- Create a dedicated `SecurityFilterChain` for `/api/**` requests.
- Give the REST API security filter chain a higher priority using `@Order(1)`.
- Preserve the existing browser security filter chain with a lower priority using `@Order(2)`.
- Use `SessionCreationPolicy.STATELESS` for REST API requests.
- Disable CSRF protection only for the stateless REST API filter chain.
- Preserve CSRF protection for the existing browser-based application.
- Continue using Spring Security form-based authentication for Login, Dashboard, and Soldier Management pages.
- Reuse `DatabaseUserDetailsService` for database-backed authentication.
- Continue using `UserDataService.findByUsername()` to retrieve registered users from MySQL.
- Continue using `BCryptPasswordEncoder` through the `PasswordEncoder` interface for secure password verification.
- Require valid authentication for all `/api/**` requests.
- Continue requiring authentication for Dashboard and Soldier CRUD pages.
- Preserve the existing Spring Security Login and Logout behavior.
- Continue using `POST /logout` for secure Logout.
- Continue using constructor-based dependency injection for controllers and services.
- Preserve the Controller → Business Service → DAO → JdbcTemplate → MySQL architecture.
- Keep SQL queries inside the `@Repository` data access layer.
- Continue using parameterized `JdbcTemplate` queries to prevent SQL injection.
- Preserve existing Soldier Create, Display, Search, Update, and Delete functionality.
- Preserve existing Soldier validation using `@Valid` and `BindingResult`.
- Preserve case-insensitive partial Soldier ID and name searches.
- Preserve the immutable Soldier ID behavior during editing.
- Preserve POST-based Delete operations with browser confirmation.
- Continue storing user-entered Unit information in `soldiers.unit`.
- Leave `formation_id` assignment as a future enhancement.
- Retain the existing MySQL `formations`, `users`, and `soldiers` tables.
- Use an OpenAPI 3.0.3 specification in `api-design.yaml` to document REST API endpoints.
- Document REST API methods, URL parameters, authentication requirements, JSON responses, and HTTP status codes.
- Use JUnit, Spring Security Test, and MockMvc for automated REST API security testing.
- Test requests without credentials and requests using invalid HTTP Basic credentials.
- Add functional tests for valid credentials, JSON responses, and nonexistent Soldier IDs.
- Execute all regression test cases from the Milestone 6 test plan.
- Perform code review to verify functionality, security, naming conventions, separation of concerns, and documentation.
- Include JavaDoc and inline comments for new REST API components.
- Use Maven Wrapper for compilation, automated testing, and executable JAR packaging.
- Use HTTPS for HTTP Basic authentication in production environments because Basic credentials are not encrypted by the authentication scheme itself.
- Record a screencast demonstrating the REST APIs, authentication, and existing Soldier Management functionality from the local development environment.

## Future Enhancements

## Future Enhancements

The following features remain possible future enhancements:

- REST API endpoints for creating Soldiers using `POST`.
- REST API endpoints for updating Soldiers using `PUT` or `PATCH`.
- REST API endpoints for deleting Soldiers using `DELETE`, with appropriate authorization.
- Pagination and sorting for large Soldier datasets.
- Advanced REST API filtering and searching.
- Dedicated API response DTOs to limit exposed Soldier information.
- A graphical administrative interface for assigning and updating user roles.
- More detailed formation hierarchy management.
- Additional automated tests for FULL_TIME_STAFF and FIRST_SERGEANT accounts.
- Automated notifications for newly submitted Soldier deletion requests.
- Email notifications when deletion requests are approved or rejected.
- Duplicate deletion request prevention.
- Expanded deletion request audit history.
- Soft deletion or archival of Soldier records instead of permanent deletion.
- Forgot Username functionality.
- Forgot Password and password reset functionality.
- Account lockout after repeated failed Login attempts.
- Multifactor authentication.
- Token-based REST API authentication using JWT or OAuth 2.0.
- REST API versioning and standardized error responses.
- REST API rate limiting.
- Additional automated integration and performance tests.
- Production deployment hardening, HTTPS configuration, and monitoring.
- Automatic logout after inactivity.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone8 README.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/README.md)
- [Milestone8 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/design.md)
- [Milestone8 projectStatus.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/projectStatus.md)
- [Milestone8 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/test.md)