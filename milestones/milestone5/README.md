# Milestone 5

- Author: John Dearing
- Date: 10/10/2026

## Introduction

This is **Milestone 5**

-For Milestone 5 of the Soldier Management System (SMS) of CST-339. After adding the MySQL/Spring JDBC persistence in Milestone 4, in this milestone we have added the Soldier Display, Soldier Search, Soldier Update, and Soldier Delete functionality to the existing create soldier process flow. In this way, users can perform searching by exact or partial soldier Id, first name, last name, and full name (first name and last name combination), can edit an existing soldier record and can delete a selected record.

Java 17, Spring Boot, Spring MVC, Thymeleaf, Bootstrap, Jakarta Bean Validation, Spring Beans, constructor based dependency injection, Spring JDBC/`JdbcTemplate`, MySQL, Maven, and Controller → Service → DAO → Database architecture will still be used. Accessing of temporary `HttpSession` variable check is there but will be removed in further milestones after adding Spring Security functionality.

**Pages in milestone 5:** `/dashboard`, `/soldiers/create`, `/soldiers` (display/search) and `/soldiers/edit/{soldierId}`. Soldier Delete functionality is performed by posting data to `/soldiers/delete/{soldierId}`.

## Code Review and Documentation

Milestone 5 source code was reviewed for overall project structure, naming convention, validation, Spring MVC framework, Dependency Injection, separation of concerns and database actions.

Implementation of Soldier Display, Search, Update and Delete functionalities and maintaining N-Layer architecture were the focus areas in the review. SoldierController receives the user requests, SoldierService contains the business logic and SoldierDataService uses Spring JdbcTemplate to run parameterized SQL queries on MySQL database.

Searching functionality works using full or partial Soldier Ids and names. Update and delete functionalities were reviewed for proper validation and record handling.

As the project is done individually, the individual code review is done unless it is required to do the peer code review by the instructor. JavaDoc and comments are provided for classes and methods wherever necessary.

All the test cases for Milestone 5 have been passed successfully including CRUD operations, database persistence, validation and JAR execution tests.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone5 analysisPlanning.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/analysisPlanning.md)
- [Milestone5 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/design.md)
- [Milestone5 projectStatus.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/projectStatus.md)
- [Milestone5 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/test.md)
