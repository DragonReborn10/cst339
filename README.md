# CST-339 Programming in Java III

## Introduction

Grand Canyon University’s Programming in Java III (CST-339) is designed for building web applications of enterprises using the Java programming language and the Spring framework. Various components of the project include object-oriented programming, Spring Boot, Spring MVC, Thymeleaf, Spring Core, dependency injection, validation, responsive web design, database design, and others enterprise application development principles.

 My milestone course project is the **Soldier Management System (SMS)**. This application will be developed in stages within the course using the N-Layer architecture. The purpose of this application is to build a web-based Soldier information management application and at the later milestones, information management of formation-level personnel.

The current project includes user registration, simulation of user login, sessions, Soldier Dashboard, and Soldier Creation. Spring business services and dependency injection were used in order to separate business operations from the presentation layer.

Further, the project will be extended to cover database persistence, CRUD operations, Spring Security, REST services, and final application documentation.

### Technologies

The project currently uses:

- Java 17
- Spring Boot
- Spring MVC
- Spring Core
- Spring Beans
- Dependency Injection
- Spring JDBC
- JdbcTemplate
- MySQL
- MAMP
- Thymeleaf
- Jakarta Bean Validation
- Bootstrap
- HTML
- CSS
- Maven
- Git
- GitHub
- Visual Studio Code
- Embedded Tomcat

Additional technologies will be introduced as the course project progresses, including Spring Security and REST services.

### Application Features

Through Milestone 4, the Soldier Management System includes:

- Responsive Home page
- User Registration page
- Registration form validation
- Persistent user registration
- User Login page
- Login form validation
- Database-backed authentication
- HTTP session management
- Authenticated Soldier Dashboard
- Create Soldier functionality
- Soldier form validation
- Persistent Soldier records
- Duplicate username checking
- Duplicate Soldier ID checking
- Spring-managed business services
- Spring-managed data services
- Constructor-based dependency injection
- Spring JDBC database access
- MySQL relational database persistence
- Shared Thymeleaf page components
- Bootstrap responsive styling
- Consistent application theme
- Relational database design
- UML, architecture, sitemap, and ER design documentation
- Maven executable JAR packaging

## Milestone Assignments

| Milestone | Description |
| --- | --- |
| [Milestone 1](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone1/README.md) | Established the initial design and project plan for the Soldier Management System. The milestone defined the application's purpose, requirements, proposed architecture, user interface concepts, sitemap, and initial technical design. |
| [Milestone 2](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone2/README.md) | Implemented the initial Spring MVC web application. The application introduced the Home, Registration, Login, and Dashboard pages using Spring MVC, Thymeleaf, Bootstrap, Jakarta Bean Validation, and a common application theme. Registration and login were implemented without database persistence. |
| [Milestone 3](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone3/README.md) | Expanded the application with Spring Beans, Spring Core, business services, and dependency injection. Registration and login were refactored to use business service classes, and the product creation requirement was implemented as Create Soldier. Soldier creation uses Spring MVC, Thymeleaf, Jakarta validation, and a Soldier business model. Create Soldier is accessed through the authenticated Dashboard. A relational database model was also designed in preparation for database implementation in the next milestone. |
| [Milestone 4](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone4/README.md) | Implemented persistent relational database storage using MySQL and Spring JDBC. Registration, Login, and Create Soldier were refactored to use a data access layer with Spring `JdbcTemplate`. User and Soldier information is now persisted in MySQL, authentication is database-backed, duplicate username and Soldier ID checks are implemented, and the application can be packaged and executed as a Spring Boot JAR using Maven. |

> Additional milestone assignments will be added as they are completed.

## Course Project

### Soldier Management System

The **Soldier Management System (SMS)** is the primary application being developed throughout CST-339.

The application is designed to manage Soldier information within military formations. The project uses fictional test information during development and demonstration.

The current Soldier model includes:

- Soldier ID
- First Name
- Last Name
- Rank
- Unit
- MOS
- Duty Status
- Email
- Phone Number

The planned application design associates Soldiers and authorized users with formations. This design will support future functionality for viewing and managing personnel at the appropriate formation level.

## Project Progress

### Milestone 1

Milestone 1 focused primarily on project planning and system design. The Soldier Management System concept, application requirements, architecture, navigation, user interface concepts, and development plan were established.

### Milestone 2

Milestone 2 moved the development of the project from design phase to developing a Spring Boot web application. The use of Spring MVC, controllers, models, Thymeleaf, Bootstrap, registration, login, validation, and Soldier Dashboard were achieved.

Authentication was just simulated since database persistence and Spring Security were not included in Milestone 2.

### Milestone 3

In Milestone 3, we introduced a business service layer specifically for the business logic via Spring Beans with dependency injection. The Authentication and Registration process were refactored, and the logic was moved out from Spring MVC Controller.

For implementing the creation of products, we use the business domain of our application. This functionality is provided via SoldierModel, SoldierController, and SoldierService.

Create Soldier is accessible via authenticated Soldier Dashboard. For validating Soldier information that is submitted by users, Jakarta Bean Validation and `BindingResult` are utilized.

Tables and Relationships are developed for the Database, although database persistence will be done later in another milestone.

### Milestone 4

In Milestone 4, persistent relational database storage was implemented by using MySQL and Spring JDBC.

Data access layers were implemented using `UserDataService` and `SoldierDataService`. Both data services are Spring-managed `@Repository` components, which utilize `JdbcTemplate` to execute parameterized SQL statements on the MySQL database.

User registration was modified in order to store the details of valid users in the `users` table. Duplicate usernames are checked before creating a user.

User authentication was modified from fake authentication to database-based authentication. `AuthenticationService` works with `UserDataService` to check whether the provided Login credentials correspond to a registered user in MySQL.

Create Soldier was modified in order to store the details of valid soldiers in the `soldiers` table. `SoldierService` checks for duplicates of Soldier ID and uses `SoldierDataService` to save the Soldier details in the database, which includes Unit provided via Create Soldier form.

Moreover, the Spring Boot application was assembled as a JAR file using Maven and executed from the console outside of Visual Studio Code.

## Future Development

The Soldier Management System will continue to evolve throughout the remaining CST-339 milestones. Planned development includes:

- View Soldier functionality
- Update Soldier functionality
- Delete Soldier functionality
- Full Soldier CRUD operations
- Formation-level Soldier management
- Formation assignment integration
- User roles and authorization
- Spring Security
- Secure password encoding
- REST API services
- JavaDoc and final code documentation
- Final testing and application refinement

## Conclusion

Over the first four milestones of CST-339, the Soldier Management System has evolved from the idea and design into an N-layer Spring Boot application that stores data in relational persistent databases.

Milestone 2 was responsible for implementing the web interface, forms of Registration and Login, validation, shared page template, and Soldier Dashboard. Milestone 3 added the business service layer and implemented the Create Soldier flow.

Milestone 4 created the persistence layer of the application. User Registration information is now stored in MySQL database, Login is done with registered database user accounts, and Create Soldier information is stored in the `soldiers` table. Now the application is built with the following architecture: Controller to Service to Data Service where Spring `JdbcTemplate` communicates with MySQL database.

At the moment, the application is using `HttpSession` to keep track of authenticated user sessions, and passwords are not encrypted. Spring Security implementation, password encryption, full Soldier CRUD capabilities, formation management, and REST services will be implemented in future milestones.

The Soldier Management System will evolve into a full-fledged N-Layer enterprise application.