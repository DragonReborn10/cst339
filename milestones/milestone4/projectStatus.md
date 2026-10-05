# Milestone 4 Project Status

- Author: John Dearing
- Date: 10/04/2026

## Tasks Completed

1. Maintained the Soldier Management System application theme, including colors, fonts, page layouts, navigation, and responsive styling.

2. Maintained the Home, Registration, Login, Dashboard, and Create Soldier pages using Spring MVC and Thymeleaf.

3. Maintained reusable header and footer fragments using `defaultTemplate.html`.

4. Added Spring JDBC and the MySQL JDBC driver to the Maven project.

5. Created the `soldier_management_system` MySQL database.

6. Created the `formations`, `users`, and `soldiers` relational database tables.

7. Added `UserDataService` as a Spring Repository and DAO for user database operations.

8. Added `SoldierDataService` as a Spring Repository and DAO for Soldier database operations.

9. Implemented Spring `JdbcTemplate` for database communication.

10. Refactored `RegistrationService` to persist registered users through `UserDataService`.

11. Added duplicate username checking before user registration.

12. Refactored `AuthenticationService` to authenticate Login credentials against the users stored in MySQL.

13. Refactored `SoldierService` to persist Soldier records through `SoldierDataService`.

14. Added duplicate Soldier ID checking before creating Soldier records.

15. Persisted the Soldier's Unit information from the Create Soldier form to the `soldiers.unit` database column.

16. Maintained Jakarta Bean Validation for Registration, Login, and Create Soldier forms.

17. Added database-related success and error messages to the Thymeleaf views.

18. Maintained constructor-based Dependency Injection between controllers, business services, and data services.

19. Maintained temporary HttpSession checks to prevent unauthenticated users from accessing the Dashboard and Create Soldier functionality.

20. Configured the Spring Boot application to connect to the MySQL database running through MAMP.

21. Tested user registration against the MySQL database.

22. Tested database-backed user authentication.

23. Tested Soldier creation and database persistence.

24. Tested duplicate username and duplicate Soldier ID handling.

25. Updated the application architecture, sitemap, UML Class Diagram, Entity Relationship Diagram, and DDL documentation.

26. Packaged the Spring Boot application as an executable JAR using Maven.

27. Tested the packaged application from a terminal outside Visual Studio Code.

## Known Issues

- Passwords are currently stored and compared directly in the database without password hashing or encoding.

- The current password implementation is intended only for the current development milestone and fictional test accounts.

- Spring Security has not yet been implemented.

- `HttpSession` checks provide temporary access control but are not a replacement for Spring Security authentication and authorization.

- Forgot Username and Forgot Password functionality has not yet been implemented.

- Formation Level Access and View My Soldier Data remain Dashboard options whose complete functionality will be implemented during later milestones.

- The database schema includes `formation_id` relationships, but the current Create Soldier workflow does not assign a Soldier to a `formation_id`.

- Soldier Unit information is currently stored directly in the `soldiers.unit` column.

- Full Soldier CRUD functionality has not yet been implemented. Viewing, updating, and deleting Soldier records will be implemented during the CRUD milestone.

- Database availability is required for Registration, Login, and Soldier creation functionality.

- Only fictional demonstration information is used. No real Soldier or personnel information is stored in the application.

## Risks

- The application depends on the MySQL database server being available. If MAMP or MySQL is not running, persistence operations will fail.

- Database credentials should not be committed to a public source code repository. Environment variables should be used for sensitive database credentials.

- The current authentication implementation does not use password hashing and must be replaced with secure password handling during the Spring Security milestone.

- Duplicate usernames and Soldier IDs could cause data integrity problems. The application performs duplicate checks, and database primary key and unique constraints provide additional protection.

- Temporary HttpSession access control does not provide the authentication and authorization capabilities required for a production application.

- Future changes to the Formation data model may require additional database and application refactoring.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone4 README.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone4/README.md)
- [Milestone4 analysisPlanning.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone4/analysisPlanning.md)
- [Milestone4 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone4/design.md)
- [Milestone4 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone4/test.md)