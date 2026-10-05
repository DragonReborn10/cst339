# Milestone 4 Analysis Planning

- Author: John Dearing
- Date: 10/04/2026

## Planning Documentation

The Soldier Management System will be further developed in a gradual manner based on CST-339 milestones. Milestone 3 included such components as Spring business services, Dependency Injection, and the "Create Soldier" feature. In Milestone 4, there is an addition of persistent database storage that works via a relational database.

In the case of Milestone 4, the main architectural aim was not to include any code related to the database in Spring MVC controllers and Thymeleaf views but to create a separate layer of data access.

The resulting application flow follows this pattern:

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

`RegistrationController`, `LoginController`, and `SoldierController` continue to process HTTP requests and form submissions. The controllers delegate application operations to `RegistrationService`, `AuthenticationService`, and `SoldierService`.

The business services then communicate with `UserDataService` and `SoldierDataService`. These data services use Spring `JdbcTemplate` to execute parameterized SQL queries against MySQL.

Such segregation of concerns lays the groundwork for the complete CRUD implementation planned for the next milestone.

The version control system Git is still being used for tracking code modifications. There is also an update to the Design Report to reflect any architectural changes made to the application.

## General Technical Approach

Soldier Management System is an application written in Java as a web application developed using Spring Boot and Spring MVC frameworks.

Soldier Management System uses the Model-View-Controller architectural pattern to separate presentation, application models, and controllers.

Thymeleaf is employed for the HTML page layout and component templating as well as form binding. Bootstrap and Custom CSS are used for responsive design and styling.

Business service layer uses the Spring @Service annotations and constructor based Dependency Injection. Business Services in Spring include:

- `AuthenticationService`
- `RegistrationService`
- `SoldierService`

Milestone 4 introduces a data access layer containing:

- `UserDataService`
- `SoldierDataService`

The data services use the Spring `@Repository` stereotype and Spring `JdbcTemplate`.

MySQL is used to store persistent relational databases for application users, Soldiers, and units.

Soldier is the main business/product object in the Soldier Management System. Creation of the Soldier consists of the following components: `SoldierModel`, `SoldierController`, `SoldierService`, `SoldierDataService`, Create Soldier Thymeleaf view, and the MySQL table soldiers.

User registration follows:

```text
register.html
      ↓
RegistrationController
      ↓
RegistrationService
      ↓
UserDataService
      ↓
JdbcTemplate
      ↓
users table
```

Login authentication follows:

```text
login.html
      ↓
LoginController
      ↓
AuthenticationService
      ↓
UserDataService
      ↓
JdbcTemplate
      ↓
users table
```

Soldier creation follows:

```text
create-soldier.html
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

## Key Technical Decisions

- Java 17 and Spring Boot are used as the primary application platform.

- Spring MVC is used to implement the Model-View-Controller architecture.

- Thymeleaf is used for HTML templates and form binding.

- Bootstrap and custom CSS are used for responsive design and the application theme.

- Common Thymeleaf fragments are used for the application header and footer.

- Spring `@Service` components implement the business service layer.

- Spring `@Repository` components implement the data access layer.

- Constructor-based Dependency Injection is used between controllers, services, and data services.

- Spring JDBC is used for relational database access.

- Spring `JdbcTemplate` is used to execute parameterized SQL queries.

- MySQL is used as the relational database.

- MAMP provides the local MySQL database server during development.

- `UserDataService` provides user registration, username lookup, and authentication database operations.

- `SoldierDataService` provides Soldier creation and Soldier ID lookup database operations.

- `AuthenticationService` handles database-backed authentication business logic.

- `RegistrationService` handles user registration business logic and duplicate username checking.

- `SoldierService` handles Soldier creation business logic and duplicate Soldier ID checking.

- `SoldierModel` represents the Soldier business object.

- Jakarta Bean Validation is used to validate user and Soldier information.

- `@Valid` and `BindingResult` are used by controllers to process validation results.

- `HttpSession` is currently used to maintain logged-in state.

- Create Soldier is accessed from the Dashboard after a successful login.

- Soldier Unit information entered on the Create Soldier form is stored in the `soldiers.unit` column.

- The database contains a `formation_id` relationship for future formation-level functionality, but the current Create Soldier workflow does not assign `formation_id`.

- Maven is used to compile, package, and create an executable Spring Boot JAR.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone4 README.md]()
- [Milestone4 design.md]()
- [Milestone4 projectStatus.md]()
- [Milestone4 test.md]()