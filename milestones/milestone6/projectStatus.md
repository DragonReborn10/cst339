# Milestone 6 Project Status

- Author: John Dearing
- Date: 10/24/2026

## Tasks Completed

1. Retained all functionality from Milestone 5, including Home, Registration, Login, Dashboard, and Soldier Create, Display, Search, Update, and Delete.
2. Added the Spring Security framework to replace the previous manual `HttpSession` authentication checks.
3. Created `SecurityConfig.java` to configure Spring Security authentication, authorization, login, logout, password encoding, and protected application routes.
4. Implemented database-backed authentication using `DatabaseUserDetailsService`, which implements Spring Security's `UserDetailsService` interface.
5. Extended `UserDataService` with `findByUsername()` to retrieve registered user credentials from the MySQL database.
6. Updated `RegistrationService` to use Spring Security's `PasswordEncoder` and `BCryptPasswordEncoder` to securely hash user passwords before database storage.
7. Updated `LoginController` to display the Login page while allowing Spring Security to process authentication requests through `POST /login`.
8. Updated `login.html` to submit authentication credentials through Spring Security and display appropriate login error and logout messages.
9. Updated `DashboardController` to use Spring Security authentication instead of manually checking session variables.
10. Updated `SoldierController` to remove manual `HttpSession` authentication checks while preserving all Soldier CRUD and Search functionality.
11. Configured Spring Security to allow public access to Home, Login, Registration, and static resources while requiring authentication for the Dashboard and Soldier Management pages.
12. Implemented Spring Security logout functionality using `POST /logout`, session invalidation, and redirection to the Login page.
13. Updated the shared navigation template and Dashboard to use POST-based Logout forms with CSRF tokens.
14. Integrated CSRF protection for state-changing POST requests, including Registration, Soldier Create, Update, Delete, and Logout.
15. Removed the legacy `AuthenticationService` and plaintext-password authentication method from `UserDataService`.
16. Preserved the existing N-Layer architecture: Controller → Service → DAO → MySQL Database.
17. Preserved Spring JDBC/`JdbcTemplate` database access and parameterized SQL queries for user authentication and Soldier CRUD operations.
18. Preserved Soldier Search functionality for exact and partial Soldier IDs, first names, last names, and full names.
19. Preserved the Soldier ID as the immutable primary key during Update operations.
20. Preserved the existing MySQL database schema and direct `soldiers.unit` mapping.
21. Prepared automated Spring Security tests using JUnit, Spring Security Test, and MockMvc to verify public routes, protected routes, authentication requirements, CSRF protection, and Logout.
22. Updated the Milestone 6 README, testing documentation, project status, and security design documentation to reflect the Spring Security implementation.
23. Prepared additional Milestone 6 security test cases and a regression test plan covering the existing Milestone 5 functionality.

## Known Issues

- Existing user accounts created before Milestone 6 may contain plaintext passwords. These accounts must be migrated to BCrypt or have their passwords reset before they can authenticate successfully through Spring Security.
- The current security configuration assigns the default `USER` role to authenticated accounts but does not implement administrator roles or role-based permissions for Soldier operations.
- Any authenticated user can currently view, create, update, and delete available Soldier records. Formation-level authorization has not been implemented.
- `formation_id` is not assigned through the current Create/Edit Soldier workflow. The entered Unit continues to be stored directly in `soldiers.unit`.
- Formation Level Access remains a future feature.
- Forgot Username and Forgot Password functionality have not been implemented.
- Account lockout, password reset, email verification, and multifactor authentication are not currently implemented.
- Soldier Search uses database `LIKE` patterns and is intended for a course-scale fictional dataset. Pagination may be necessary for larger datasets.
- The application depends on local MySQL availability for user registration, authentication, and Soldier CRUD operations.
- The current implementation is intended for course demonstration and has not been evaluated for production deployment.

## Risks

- Existing plaintext-password accounts cannot authenticate through BCrypt password verification until their passwords are migrated or reset.
- Although Spring Security protects application routes, authenticated users currently have broad access to Soldier records. Role-based and formation-level authorization should be implemented before production use.
- CSRF protection requires valid tokens for state-changing requests. Missing tokens in HTML forms may cause HTTP 403 Forbidden responses.
- MySQL downtime or incorrect database credentials can interrupt Registration, Login, and Soldier CRUD operations.
- Deleting a Soldier permanently removes the record unless the database has been backed up. Fictional test data should be used, and the selected Soldier ID should be verified before deletion.
- Concurrent updates or deletions may affect the same Soldier record. Existing row-count checks can detect missing records but do not prevent lost updates.
- Database constraints and Jakarta Bean Validation rules must remain aligned to avoid data-integrity errors.
- BCrypt password hashes require sufficient database column length. The `users.password` column should support the full encoded hash, such as `VARCHAR(255)`.
- The application must use HTTPS and appropriate database credential management before deployment outside the local development environment.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone6 README.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/README.md)
- [Milestone6 analysisPlanning.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/analysisPlanning.md)
- [Milestone6 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/design.md)
- [Milestone6 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/test.md)