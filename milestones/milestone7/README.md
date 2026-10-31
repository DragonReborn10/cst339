# Milestone 7

- Author: John Dearing
- Date: 10/31/2026

## Introduction

This is **Milestone 7**

- The objective in Milestone 7 of the Soldier Management System (SMS) in CST-339 is to add REST API functionality to the SMS, where in Milestone 6 we integrated the Spring Security framework in the Soldier Management System. In Milestone 7, Soldiers will be used as the products that need to be added as per the requirement of the assignment. There are two REST APIs available in this milestone which get all the Soldiers or retrieve a particular Soldier using ID. The REST API calls use HTTP GET protocol and get the Soldier data as JSON.

- The first REST API, `GET /api/soldiers`, provides data about all the Soldiers in the MySQL database. The second REST API, `GET /api/soldiers/{soldierId}`, provides data about the particular Soldier depending on the provided Soldier Id. If the Soldier requested does not exist, then the API responds with an HTTP `404 Not Found` status. Both APIs require valid credentials for users to access Soldier data. Spring Security makes use of the current `DatabaseUserDetailsService` class to get the user credentials that are registered in the MySQL database via `UserDataService`. Passwords will still be encrypted using `BCryptPasswordEncoder` as developed during Milestone 6.

- To enable the REST API security without impacting the existing web application, another Spring Security filter chain was added to the `SecurityConfig` class. This filter chain makes use of HTTP Basic authentication for the requests that start with `/api/**`, whereas the existing Login, Registration, Dashboard, and Soldier Management pages use their existing Spring Security filter chain. Stateless authentication is used by the REST API, and CSRF protection is not available just for the REST API security filter chain. For the existing web application, CSRF protection is available. SoldierRestController makes use of the existing `SoldierService` and `SoldierDataService` to access the Soldier record using Spring JDBC/JdbcTemplate. No need for another Product model or database table, as Soldiers are considered products for this milestone.

**Pages and REST API Endpoints in Milestone 7:**

- `/` — Home page
- `/login` — Login page, with authentication handled by Spring Security
- `/register` — User Registration page
- `/dashboard` — Protected Dashboard page
- `/soldiers/create` — Protected Create Soldier page
- `/soldiers` — Protected Soldier Display and Search page
- `/soldiers/edit/{soldierId}` — Protected Edit Soldier page
- `/api/soldiers` — Protected REST API endpoint that returns all Soldiers in JSON format
- `/api/soldiers/{soldierId}` — Protected REST API endpoint that returns a specific Soldier by ID in JSON format
- `POST /logout` — Secure logout handled by Spring Security

## Code Review and Documentation

Overall project structure, naming conventions, input validation, Spring MVC, Spring Security, REST API, dependency injection, separation of concerns, interactions with databases, and authentication. The main aim of Milestone 7 is the integration of REST API in the existing Soldier Management System, while keeping the Soldier Management and security introduced in previous milestones intact. To achieve that objective, the class `SoldierRestController` has been created to create two REST API endpoints `/api/soldiers` and `/api/soldiers/{soldierId}`. These endpoints make calls to the existing `SoldierService` and `SoldierDataService` to fetch the data of Soldiers. The response of the REST API endpoints is in JSON format and not in Thymeleaf view templates.

The `SecurityConfig` class was modified by adding another Spring Security filter chain to handle REST API requests. This new filter chain enforces HTTP Basic authentication for all `/api/**` routes, without affecting the current form-based authentication method that is used to authenticate users of the web pages. The `DatabaseUserDetailsService` class implements Spring Security’s `UserDetailsService` interface and uses the `UserDataService` to obtain registered user details from the MySQL database. The existing `BCryptPasswordEncoder` still checks password encodings for authentication. A new test class named `SoldierRestControllerTest` was added for writing automation test cases that would check whether unauthenticated requests and requests with invalid HTTP Basic credentials return HTTP `401 Unauthorized` responses. An OpenAPI specification named `api-design.yaml` was written to describe REST API endpoints and requirements for authentication, responses, and HTTP statuses.

As the application development is taking place individually, the code review is done by me and not any other teammate. The existing classes and services were used where needed to keep the N-Layer architecture and avoid redundancy. JavaDoc and inline comments were added to the new REST API modules to document their purpose and security needs. As part of the testing in the Milestone 7, the regression test cases created in the Milestone 6 are going to be executed to ensure that the integration of REST API has no negative impact on the existing Soldier Management functionality.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone7 analysisPlanning.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/analysisPlanning.md)
- [Milestone7 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/design.md)
- [Milestone7 projectStatus.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/projectStatus.md)
- [Milestone7 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/test.md)
