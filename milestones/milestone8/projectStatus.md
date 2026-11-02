# Milestone 8 Project Status

- Author: John Dearing
- Date: 11/01/2026

## Tasks Completed

1. Retained the Soldier Management System (SMS) functionality developed during Milestones 5, 6, and 7, including Home, Registration, Login, Dashboard, Soldier management, Spring Security, and REST API functionality.
2. Continued using Java 17, Spring Boot, Spring MVC, Spring Security, Thymeleaf, Maven, Spring JDBC/JdbcTemplate, and MySQL.
3. Extended the existing Spring Security implementation with role-based access control to restrict application features and Soldier information based on the authenticated user's assigned permissions.
4. Introduced five application access levels: ADMIN, FULL_TIME_STAFF, FIRST_SERGEANT (1SG), COMMANDER, and BASIC.
5. Configured ADMIN accounts to have full Soldier management access, including viewing Soldier records across formations, creating Soldiers, editing records, and directly deleting Soldiers.
6. Configured FULL_TIME_STAFF, FIRST_SERGEANT, and COMMANDER access levels to support Soldier management within their assigned unit or formation.
7. Restricted BASIC users to viewing only the Soldier record linked to their authenticated account.
8. Updated the Dashboard to display navigation options according to the authenticated user's access level.
9. Restricted the Create Soldier Dashboard option to ADMIN, FULL_TIME_STAFF, FIRST_SERGEANT, and COMMANDER accounts.
10. Restricted the Formation Level Access Dashboard option to ADMIN, FULL_TIME_STAFF, FIRST_SERGEANT, and COMMANDER accounts.
11. Configured the BASIC Dashboard to display View My Soldier Data and Logout without displaying Create Soldier or Formation Level Access.
12. Implemented the View My Soldier Data feature to display the Soldier record associated with the logged-in account rather than displaying all Soldier records.
13. Implemented formation-level Soldier filtering so management users can view Soldier records associated with their authorized formation.
14. Verified that the COMMANDER formation-level Soldier list excludes Soldier records belonging to other formations.
15. Updated the Create Soldier workflow to support automatic formation and unit assignments based on the authenticated user's permissions.
16. Enforced formation and unit restrictions through server-side authorization checks rather than relying only on HTML form restrictions.
17. Retained the existing Soldier search and edit functionality while introducing role-based restrictions on which records users can access.
18. Implemented a Soldier deletion request workflow for authorized non-administrator management roles.
19. Replaced direct Soldier deletion for COMMANDER, FULL_TIME_STAFF, and FIRST_SERGEANT accounts with a Request Delete action requiring a justification.
20. Retained direct Soldier deletion privileges for ADMIN accounts.
21. Implemented a deletion request submission form that allows an authorized user to provide a reason for requesting Soldier deletion.
22. Preserved the Soldier record after deletion request submission so that the record is not immediately deleted.
23. Added or incorporated the `soldier_delete_requests` MySQL table to support deletion request tracking, including requester, justification, status, reviewer, review comment, and timestamps.
24. Added or incorporated the `user_access` MySQL table to support account-specific roles, unit assignments, and individual Soldier associations.
25. Used the `formations` table to identify organizational formations and associate Soldier records with their assigned formation.
26. Updated the `soldiers` table to include `formation_id`, supporting formation-level access restrictions.
27. Used the existing `users` table to maintain registered account information, authentication credentials, and formation associations.
28. Preserved the existing MySQL database structure and Soldier ID format, including String-based Soldier IDs with leading zeros.
29. Retained the two REST API endpoints introduced in Milestone 7:
    - `GET /api/soldiers`
    - `GET /api/soldiers/{soldierId}`
30. Retained Spring Security HTTP Basic authentication for REST API requests and form-based authentication for the web application.
31. Preserved the existing application architecture: Controller → Business Service → DAO → JdbcTemplate → MySQL Database.

## Known Issues

- The REST APIs currently support retrieving Soldier records only. REST API endpoints for creating, updating, and deleting Soldiers have not been implemented. These operations remain available through the existing Spring MVC web application.
- The REST APIs use HTTP Basic authentication. HTTP Basic credentials are Base64-encoded rather than encrypted by the authentication scheme itself, so HTTPS is required when using these APIs outside the local development environment.
- Existing user accounts created before Milestone 6 may contain plaintext passwords. These accounts must be migrated to BCrypt or have their passwords reset before they can authenticate successfully through Spring Security.
- The current security configuration assigns the default `USER` role to authenticated accounts but does not implement administrator roles or role-based permissions for REST API or Soldier Management operations.
- Any authenticated user can currently retrieve Soldier records through the REST APIs. Formation-level authorization and more restrictive access controls have not been implemented.
- Any authenticated user can currently view, create, update, and delete available Soldier records through the web application. Role-based authorization remains a future enhancement.
- The REST APIs currently return JSON representations of the existing `SoldierModel`. Dedicated Data Transfer Objects (DTOs) have not been implemented to restrict which Soldier properties are exposed.
- The REST API does not currently support pagination, sorting, or advanced filtering. The `GET /api/soldiers` endpoint retrieves the available Soldier records as a list.
- The REST API uses the existing String-based Soldier ID. API clients must provide a valid Soldier ID that matches a record in the MySQL database.
- The `formation_id` field is not assigned through the current Create/Edit Soldier workflow. The entered Unit continues to be stored directly in `soldiers.unit`.
- Forgot Username and Forgot Password functionality have not been implemented.
- Account lockout, password reset, email verification, and multifactor authentication are not currently implemented.
- Soldier Search uses database `LIKE` patterns and is intended for a course-scale fictional dataset. Pagination may be necessary for larger datasets.
- The application depends on local MySQL availability for Registration, Login, Soldier CRUD operations, and REST API data retrieval.
- Automated REST API tests have been added for unauthorized access and invalid credentials, but successful authentication, JSON response content, and missing-record behavior require additional test coverage.

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
- [Milestone8 README.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/README.md)
- [Milestone8 analysisPlanning.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/analysisPlanning.md)
- [Milestone8 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/design.md)
- [Milestone8 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/test.md)