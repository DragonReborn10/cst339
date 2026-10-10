# Milestone 5 Analysis Planning

- Author: John Dearing
- Date: 10/10/2026

## Planning Documentation

Milestone 5 extends the Milestone 4 Spring JDBC/MySQL application from Create Soldier to complete Soldier CRUD. The work is organized around the existing Controller → Business Service → DAO → JdbcTemplate → MySQL architecture. No new database table is required.

| Task | Planned sequence | Deliverable | Status |
| --- | --- | --- | --- |
| Extend Soldier DAO | 1 | Read, search, update, and delete SQL methods | Completed; tests passed |
| Extend Soldier business service | 2 | CRUD/search service methods | Completed; tests passed |
| Extend Soldier MVC controller | 3 | List, edit, update, and delete routes | Completed; tests passed |
| Create Soldier list UI | 4 | `soldiers.html` with search/results/actions | Completed; tests passed |
| Create Edit Soldier UI | 5 | `edit-soldier.html` with validation | Completed; tests passed |
| Connect Dashboard | 6 | View My Soldier Data links to `/soldiers` | Completed; tests passed |
| Validate application | 7 | CRUD, search, session, and regression tests | Completed; 28 of 28 passed |
| Package application | 8 | Executable Spring Boot JAR | Completed; build and launch passed |
| Update documentation | 9 | Report, diagrams, screenshots, Service API | In progress |

The application flow remains:

```text
Thymeleaf View
    ↓
Spring MVC Controller
    ↓
Business Service
    ↓
Data Service / DAO
    ↓
Spring JdbcTemplate
    ↓
MySQL Database
```

## General Technical Approach

The Soldier Management System is a Java 17 Spring Boot MVC project. The interface is provided by Thymeleaf and Bootstrap, validation of forms is performed by Jakarta Bean Validation, and Spring-managed beans use constructor dependency injection.

Existing user workflows remain `register.html → RegistrationController → RegistrationService → UserDataService → users` and `login.html → LoginController → AuthenticationService → UserDataService → users`.

Milestone 5 extends the Soldier workflow:

```text
soldiers.html (display and partial search)
    ↓
SoldierController
    ↓
SoldierService
    ↓
SoldierDataService
    ↓
JdbcTemplate
    ↓
soldiers table
```
Soldiers are inserted using Create form. Soldiers list shows all records or searches through exact/particular Soldier ID, Soldier’s first name, Soldier’s last name, and full name. Edit form fetches particular record with exact ID and saves valid updates. Delete form makes use of POST method and gives user a confirm dialog box. Update/Delete operation takes exact primary key for a particular record.

## Key Technical Decisions

- Reuse `SoldierModel`, `SoldierController`, `SoldierService`, and `SoldierDataService` rather than creating parallel CRUD classes.
- Keep all SQL in the `@Repository` DAO; business methods remain in the `@Service` layer.
- Use parameterized `JdbcTemplate` SQL to prevent query text from being assembled from user input.
- Use case-insensitive `LIKE` searching for partial Soldier IDs and names, including partial first/last name pairs.
- Keep `soldier_id` immutable during editing and use the URL path ID as the authoritative update target.
- Use POST for Delete, not GET, and display a browser confirmation before submission.
- Continue `@Valid` and `BindingResult` checks on Create and Update.
- Continue temporary `HttpSession` login checks pending Spring Security in Milestone 6.
- Preserve `soldiers.unit` as the user-entered Unit; `formation_id` is still not assigned by the form.
- Retain the existing MySQL `formations`, `users`, and `soldiers` tables; no schema migration is needed for CRUD.
- Use Maven Wrapper for compilation, tests, and executable JAR packaging.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone5 README.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/README.md)
- [Milestone5 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/design.md)
- [Milestone5 projectStatus.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/projectStatus.md)
- [Milestone5 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/test.md)