# Milestone 7 Analysis Planning

- Author: John Dearing
- Date: 10/31/2026

## Planning Documentation

- Milestone 7 extends the Milestone 6 Soldier Management System (SMS) by implementing REST API functionality within the existing Java 17 Spring Boot application.
- Milestone 7 focuses on creating two REST API endpoints that retrieve Soldier information from the MySQL database and return the results in JSON format.
- For this milestone, Soldier records represent the products required by the CST-339 Milestone 7 assignment. The existing Soldier model, service, DAO, and database table are reused rather than introducing separate Product classes or tables.
- The first REST API, `GET /api/soldiers`, returns all Soldier records. The second REST API, `GET /api/soldiers/{soldierId}`, returns a specific Soldier using the requested Soldier ID.
- Both REST APIs are secured using Spring Security HTTP Basic authentication with credentials stored in the existing MySQL `users` table.
- The existing `DatabaseUserDetailsService`, `UserDataService`, and `BCryptPasswordEncoder` components from Milestone 6 continue providing database-backed authentication.
- An additional Spring Security filter chain is introduced for `/api/**` requests. This filter chain uses stateless HTTP Basic authentication, while the existing web application continues using form-based Login, Logout, session management, and CSRF protection.
- The existing Controller → Business Service → DAO → JdbcTemplate → MySQL architecture is preserved. The new `SoldierRestController` provides REST API functionality without changing the existing `SoldierController` used for Thymeleaf pages.
- The existing `formations`, `users`, and `soldiers` tables remain in use. No new database tables or database schema changes are required for the REST API implementation.
- The REST API design is documented using an OpenAPI 3.0.3 specification in `api-design.yaml`, which can be viewed using Swagger Editor.
- Milestone 7 also requires code review, JavaDoc and inline documentation, execution of the Milestone 6 test plan, updated design documentation, and a screencast demonstrating the application.

| Task | Planned Sequence | Deliverable | Status |
| --- | --- | --- | --- |
| Review Milestone 7 requirements | 1 | Requirements analysis for REST APIs, authentication, testing, and documentation | Completed |
| Design REST API endpoints | 2 | REST API definitions for retrieving all Soldiers and a specific Soldier | Completed |
| Create REST API design specification | 3 | `api-design.yaml` using OpenAPI 3.0.3 | Completed |
| Implement Soldier REST controller | 4 | `SoldierRestController.java` with two GET endpoints | Completed |
| Implement Get All Soldiers API | 5 | `GET /api/soldiers` returning JSON Soldier records | Completed |
| Implement Get Soldier by ID API | 6 | `GET /api/soldiers/{soldierId}` returning one Soldier or HTTP 404 | Completed |
| Configure REST API authentication | 7 | Additional `SecurityFilterChain` using HTTP Basic authentication | Completed |
| Integrate database-backed authentication | 8 | Reuse `DatabaseUserDetailsService`, `UserDataService`, and BCrypt | Completed |
| Preserve existing web security | 9 | Maintain form-based Login, Logout, session security, and CSRF protection | Completed |
| Preserve Soldier Management functionality | 10 | Existing Create, Display, Search, Update, and Delete functionality retained | Completed |
| Test successful REST API responses | 12 | Verify authenticated JSON responses, Soldier lookup, and HTTP 404 | Completed |
| Execute Milestone 6 regression testing | 13 | Run all test cases from the Milestone 6 test plan | Completed |
| Perform code review | 14 | Review REST controller, security configuration, naming, and documentation | Completed |
| Update JavaDoc and inline comments | 15 | Document REST API classes, methods, and security behavior |Completed |
| Update Design Report | 16 | Architecture, sitemap, UI diagrams, ER diagram, DDL, UML, and Service API | Completed |
| Package application | 17 | Compile, test, package, and launch executable Spring Boot JAR | Completed |
| Record application screencast | 18 | Local technical and functional demonstration URL | Completed |
| Update project documentation | 19 | README, Analysis Planning, Design, Project Status, and Testing | Completed |


he updated application flow is:

```text
REST Client / Postman / Web Browser
                |
                v
     Spring Security Filter Chain
                |
                +-- /api/** Requests
                |        |
                |        v
                |   HTTP Basic Authentication
                |        |
                |        v
                |   DatabaseUserDetailsService
                |        |
                |        v
                |   UserDataService
                |        |
                |        v
                |   MySQL users Table
                |        |
                |        v
                |   Authentication Result
                |        |
                |        +-- Invalid --> 401 Unauthorized
                |        |
                |        +-- Valid
                |               |
                |               v
                |       SoldierRestController
                |               |
                |               v
                |         SoldierService
                |               |
                |               v
                |       SoldierDataService
                |               |
                |               v
                |         JdbcTemplate
                |               |
                |               v
                |       MySQL soldiers Table
                |               |
                |               v
                |          JSON Response
                |
                +-- Web Application Requests
                         |
                         v
                  Form-Based Login
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
                    JdbcTemplate
                         |
                         v
                    MySQL Database
```

## General Technical Approach

- The Soldier Management System remains a Java 17 Spring Boot MVC project.
- The application continues using Thymeleaf and Bootstrap for the user interface, Jakarta Bean Validation for form validation, Spring-managed beans with constructor-based dependency injection, Spring JDBC for database access, and MySQL for persistent storage.
- Milestone 7 introduces REST API functionality using Spring MVC's `@RestController`, `@RequestMapping`, `@GetMapping`, and `@PathVariable` annotations.
- REST API responses use JSON instead of returning Thymeleaf HTML views.
- The new `SoldierRestController` is responsible for handling REST API requests and returning HTTP responses.
- The existing `SoldierService` provides the business logic for retrieving Soldier records.
- The existing `SoldierDataService` performs database queries using `JdbcTemplate`.
- The first REST API retrieves all Soldiers using `SoldierService.getAllSoldiers()`.
- The second REST API retrieves a specific Soldier using `SoldierService.getSoldierById(String)`.
- Soldier IDs remain String values, matching the existing `SoldierModel` and database implementation.
- If a requested Soldier does not exist, the REST API returns HTTP `404 Not Found`.
- Spring Security authenticates REST API requests before they reach `SoldierRestController`.
- HTTP Basic authentication is used for REST API requests, while form-based authentication continues to protect the existing web application.
- The REST API security filter chain uses stateless authentication, so REST clients provide credentials with each request.
- The existing MySQL `users` table is used for authentication. No additional authentication database is required.
- Existing BCrypt password encoding remains in place for password verification.
- An OpenAPI specification documents the REST endpoints, authentication requirements, parameters, JSON response schemas, and HTTP status codes.
- Automated tests are included to verify unauthorized access behavior. Additional functional testing and Milestone 6 regression testing are required before final submission.

### REST API 1: Retrieve All Soldiers

```text
REST Client
     |
     v
GET /api/soldiers
     |
     v
Spring Security HTTP Basic Authentication
     |
     +-- Invalid Credentials --> 401 Unauthorized
     |
     +-- Valid Credentials
              |
              v
     SoldierRestController
              |
              v
     SoldierService.getAllSoldiers()
              |
              v
     SoldierDataService
              |
              v
     JdbcTemplate
              |
              v
     MySQL soldiers Table
              |
              v
     List<SoldierModel>
              |
              v
     HTTP 200 OK
     JSON Array Response
```

### REST API 2: Retrieve a Specific Soldier

```text
REST Client
     |
     v
GET /api/soldiers/{soldierId}
     |
     v
Spring Security HTTP Basic Authentication
     |
     +-- Invalid Credentials --> 401 Unauthorized
     |
     +-- Valid Credentials
              |
              v
     SoldierRestController
              |
              v
     SoldierService.getSoldierById(soldierId)
              |
              v
     SoldierDataService
              |
              v
     JdbcTemplate
              |
              v
     MySQL soldiers Table
              |
              v
     Soldier Found?
         |
         +-- No --> HTTP 404 Not Found
         |
         +-- Yes
              |
              v
         HTTP 200 OK
         JSON Soldier Response
```

### REST API Authentication Flow

```text
REST Client
     |
     v
HTTP Request with Basic Authorization Header
     |
     v
Spring Security API Filter Chain
     |
     v
Extract Username and Password
     |
     v
DatabaseUserDetailsService
     |
     v
UserDataService.findByUsername()
     |
     v
MySQL users Table
     |
     v
Verify Password Using BCrypt
     |
     v
Authentication Successful?
     |
     +-- No --> HTTP 401 Unauthorized
     |
     +-- Yes
          |
          v
     Allow REST API Request
          |
          v
     Return Requested Soldier Data
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

The following features are planned for future updates:

- REST API endpoints for creating Soldiers using `POST`.
- REST API endpoints for updating Soldiers using `PUT` or `PATCH`.
- REST API endpoints for deleting Soldiers using `DELETE`.
- Pagination and sorting for large Soldier datasets.
- Advanced REST API filtering and searching.
- Dedicated API response DTOs to control exposed Soldier fields.
- Administrator and user role-based authorization.
- Formation-level access restrictions.
- Assignment of Soldiers to formations through `formation_id`.
- Forgot Username functionality.
- Forgot Password and password reset functionality.
- Account lockout after repeated failed Login attempts.
- Multifactor authentication.
- Token-based authentication using JWT or OAuth 2.0.
- API versioning and improved error response formatting.
- Rate limiting for REST API requests.
- Additional automated integration and performance tests.
- Additional production security configuration and deployment hardening.
- Automatic logout after inactivity for the browser-based application.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone7 README.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/README.md)
- [Milestone7 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/design.md)
- [Milestone7 projectStatus.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/projectStatus.md)
- [Milestone7 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/test.md)