# Milestone 8

- Author: John Dearing
- Date: 11/01/2026

## Introduction

This is **Milestone 8**.

- The goal of Milestone 8 in the Soldier Management System (SMS) course (CST-339) is the implementation of Role-Based Access Control and Formation Level Soldier management within the existing Java 17 Spring Boot application. In Milestone 7, the two REST APIs were added to fetch data about Soldiers stored in MySQL database via HTTP GET method and return the information in JSON format. Milestone 8 adds functionality to the existing system and controls the access to the Soldiers' records and possible management actions by the users.

- Milestone 8 adds the following five access levels to the user authentication: `ADMIN`, `FULL_TIME_STAFF`, `FIRST_SERGEANT`, `COMMANDER`, and `BASIC`. Each access level defines the set of functions available for the authenticated user after login. The `ADMIN` access level allows managing Soldiers fully, including viewing of Soldiers from any formations, creating, editing and directly deleting Soldier records and the ability to see the requests for Soldier deletions. The `FULL_TIME_STAFF`, `FIRST_SERGEANT` and `COMMANDER` access levels allow only creating, viewing and editing Soldiers of the assigned formations.

- The `BASIC` role can only view the record of the Soldier associated with the authenticated user account. The BASIC role does not allow the creation, modification, or deletion of Soldiers or Soldier management at the formation level. Navigation options available in the Dashboard have been modified depending on the role the user has been assigned. For instance, a BASIC user will only be able to see the View My Soldier Data and Logout options, but the management roles will have Create Soldier and Formation Level Access.

- Formation level access is accomplished using the organizational assignments contained in the MySQL database. The formation details are contained in the `formations` table, the association between Soldiers and formations is made using the `formation_id` in the `soldiers` table, and the user accounts with formation and role details are found in the `users` and `user_access` tables. The `AccessService` has been used by the application to control access according to the authorization rules and ensure that no one accesses Soldier records outside their formations.

- The application keeps using Spring Security for authentication and authorization. The current `DatabaseUserDetailsService` and `UserDataService` implementations fetch registered user details from the MySQL database, and the `BCryptPasswordEncoder` keeps securing the password details. The web application still uses the form-based Login, Logout, session management, and CSRF protection. The REST API endpoints created in Milestone 7 keep using HTTP Basic authentication via Spring Security’s custom filter chain.

**Pages and REST API Endpoints in Milestone 7:**

- `/` — Home page.
- `/login` — Login page, with authentication handled by Spring Security.
- `/register` — User Registration page.
- `/dashboard` — Protected role-based Dashboard page.
- `/soldiers/create` — Protected Create Soldier page for ADMIN and authorized formation management roles.
- `/soldiers` — Protected Soldier Display and Search page with role-based and formation-level access restrictions.
- `/soldiers/edit/{soldierId}` — Protected Edit Soldier page for authorized users.
- View My Soldier Data — Displays only the Soldier record associated with a BASIC user's account.
- Formation Level Access — Displays Soldiers belonging to the authenticated management user's assigned formation.
- Soldier Delete Request — Allows authorized management users to request Soldier deletion with a required justification.
- Administrator Delete Request Review — Allows ADMIN users to review, approve, or reject pending Soldier deletion requests.
- `/api/soldiers` — Protected REST API endpoint that returns Soldier records in JSON format.
- `/api/soldiers/{soldierId}` — Protected REST API endpoint that returns a specific Soldier by ID in JSON format.
- `POST /logout` — Secure Logout handled by Spring Security.

## Role-Based Access Control

Milestone 8 introduces five application access levels to control Soldier Management functionality.

| Role | Soldier Access | Create Soldier | Edit Soldier | Direct Delete | Request Delete |
| --- | --- | --- | --- | --- | --- |
| ADMIN | All formations | Yes | Yes | Yes | Not required |
| FULL_TIME_STAFF | Assigned formation | Yes | Yes | No | Yes |
| FIRST_SERGEANT | Assigned formation | Yes | Yes | No | Yes |
| COMMANDER | Assigned formation | Yes | Yes | No | Yes |
| BASIC | Assigned Soldier only | No | No | No | No |

## Code Review and Documentation

## Code Review and Documentation

Milestone 8 covers an examination of project design, naming conventions, input validation, Spring MVC controllers, Spring Security setup, role-based authorization, formation-level access, dependency injection, database transactions, and separation of concerns. The goal here is to enhance the current Soldier Management System by adding even more restrictive user permissions while keeping the Soldier management and REST API features from Milestone 7.

`AccessService` is the service component that controls both role-based and formation-based authorization. Role of the authenticated user, assigned formation, and Soldier data are verified before allowing a user to perform any protected operations. Thus, BASIC users cannot access other Soldier entries, while COMMANDER, FIRST_SERGEANT, and FULL_TIME_STAFF users are restricted to use only their assigned formations.

The `SoldierController`, `SoldierService`, `SoldierDataService`, and `SoldierModel` are the components that support Soldier management functionality in the current Milestone. Also, the `SoldierRestController` from Milestone 7 is responsible for two REST API endpoints. These components remain unchanged and are used when necessary to maintain N-Layer architecture of the project.

As the application development is taking place individually, the code review is done by me and not any other teammate. The existing classes and services were used where needed to keep the N-Layer architecture and avoid redundancy. JavaDoc and inline comments were added to the new REST API modules to document their purpose and security needs.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone8 analysisPlanning.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/analysisPlanning.md)
- [Milestone8 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/design.md)
- [Milestone8 projectStatus.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/projectStatus.md)
- [Milestone8 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/test.md)
