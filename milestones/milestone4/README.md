# Milestone 4

- Author: John Dearing
- Date: 10/04/2026

## Introduction

his is **Milestone 4:**

- For Milestone 4, the Soldier Management System was enhanced further by adding persistent database storage through MySQL and Spring JDBC. The Login, Registration, and Create Soldier components were enhanced by adding the data access layer which conforms to the DAO design pattern. Spring JdbcTemplate is used in the application to perform interaction with the MySQL relational database. Registration data entered by the user is stored in the users table, while the login details entered by the user are authenticated based on the registered users in the database. Also, the data entered by the user through the Create Soldier component is stored in the soldiers table. Spring MVC, Spring Beans, Constructor-based Dependency Injection, Thymeleaf, Jakarta Bean Validation, Bootstrap, Custom CSS, Thymeleaf Layouts, and HttpSession continue to be used in the application. The application implements N-Layer architecture which includes Presentation Layer, Business Service Layer, Data Access Layer, and Persistence Layer. The application is packaged into a Spring Boot JAR file using Maven to enable execution of the application from a terminal outside Visual Studio Code.

## Code Review and Documentation

Source code of Milestone 4 was reviewed on the following topics: project structure, naming convention, validation, Spring MVC architecture, Dependency Injection, separation of concerns, database access, and correct realization of the N-Layer architecture.

The focus was made on the fact that SQL and logic of accessing database should be placed in the data access layer but not in Spring MVC controllers and Thymeleaf pages.

`UserDataService` and `SoldierDataService` classes access database with Spring `JdbcTemplate`. Controllers interact with business services which in turn work with data services through Dependency Injection.

In case when this project is done individually, the code review is done individually unless peer code review is explicitly needed by the instructor.

Classes and methods are documented with JavaDoc comments. In some cases, inline comments are needed for explaining application logic.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone4 analysisPlanning.md]()
- [Milestone4 design.md]()
- [Milestone4 projectStatus.md]()
- [Milestone4 test.md]()
