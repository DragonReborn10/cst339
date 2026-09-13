# Milestone 1

- Author:  John Dearing
- Date:  09/13/2026

## Introduction

- This is **Milestone 1: Tools Installation, Validation, and Learning Maven**

- The goal of this activity is to gain knowledge on the tools and techniques used in the development and deployment of Spring Boot application. This activity gives us the opportunity to practice and apply our knowledge on how to configure Spring Tool Suite, create and test a simple “Hello World” Spring Boot application, and ensure that our local development environment is functioning properly. This activity serves as an introduction to the use of Maven in creating a project and handling its dependencies. With the use of the POM file of Maven, creation of JAR file and executing the application will allow us to develop necessary skills for the future.

## Project Proposal

- My proposed project is a Soldier Management System (SMS). This application aims to offer a web-based solution for managing soldiers' information in which the authorized user can create, view, update, and delete soldier data.

- The main business entity of this application will be Soldier. Soldiers' data will consist of basic data, such as a soldier ID, first name, last name, rank, unit, Military Occupational Specialty (MOS), duty status, email, and phone number. Fake data will be used during implementation.

- This application will be designed as an enterprise N-Layer application using the Spring Boot framework. The presentation layer will be designed using Spring MVC and Thymeleaf to render web pages. The bootstrap technology will be utilized to design a responsive interface. As a further step when implementing subsequent milestones, business and persistence services will be developed separately so that business logic won't be implemented directly into MVC views/models/controllers.

## Project Objectives

- The major goal of the Soldier Management System is to show all concepts and technologies learned in CST-339 by using them in the personnel management domain.

- The final system will have to provide features for user registration/login, where users can then use their accounts to manipulate the soldier records. 

- The following operations need to be provided: 
The system will perform the required CRUD operations in its main business domain.
Other features like form validation, Bootstrap pages, database persistence, dependency injection, Spring Security, and REST APIs will be added in accordance with the future milestones.

## Proposed Soldier Data

|Field|Description|
|--|--|
|Soldier ID|Unique identifier for each soldier record|
|First Name|Soldier's first name|
|Last Name|Soldier's last name|
|Rank|Current military rank|
|Unit|Soldier's assigned unit|
|MOS|Military Occupational Specialty|
|Duty Status|Current duty status|
|Email|Contact email|
|Phone Number|Contact phone number|
|Formation Access Level|Defines the user's level of access within the organization. For example, a Company Commander or authorized full-time staff member can be granted access to view and manage all soldier records assigned to their company or formation.|

## Proposed Application Functionality

- The fully developed Soldier Management System is expected to consist of registration and login modules as well as the capability to manage soldier data. Once authenticated, the user will navigate to a soldier dashboard where he/she can either look at soldiers already stored in the database or CRUD operation on the soldier.

- This application will eventually have:
     - User registration and login
     - Soldier dashboard
     - View all soldiers
     - Add new soldier
     - View single soldier
     - Update soldier
     - Delete soldier
     - Form validation
     - Database persistence
     - Secured application pages
     - RESTful API to soldier data

## Division of Work

- This Soldier Management System project will be done by me individually. Thus, I will be accountable for all the project planning, designing, development, testing, documentation, and presentation processes.

- The processes I will be accountable for include Spring Boot project development and maintenance; Spring MVC Models, Views, and Controllers creation; Thymeleaf templates development; responsive Bootstrap interface implementation; Soldier business model designing; business and persistence layer development; database connectivity implementation; registration and login feature development; Spring Security implementation; REST API development; testing and debugging of the project; management of the project's Git repository; design reports and screencast development; JavaDocs generation; and project presentation.

- Since the project is done individually, I will also be accountable for the review and refactoring of my code at every milestone based on instructors' feedback.

## Planned Technical Architecture 

```mermaid
---
title: Soldier Management System - Planned Technical Architecture
---
flowchart TD
    A["Presentation Layer<br/>Spring MVC<br/>Thymeleaf<br/>Bootstrap"]
    B["Business Layer<br/>Spring Core Services<br/>Business Logic"]
    C["Persistence Layer<br/>Spring JDBC / Spring Data JDBC"]
    D["MySQL Relational Database"]
    E["Spring Security<br/>Authentication and Authorization"]
    F["REST API Layer<br/>Spring REST Services"]

    A --> B
    B --> C
    C --> D
    E --> A
    E --> F
    F --> B
```

## Sitemap

```mermaid
---
title: Soldier Management System - Sitemap
---
flowchart TD
    A["Soldier Management System"]
    B["Home"]
    C["Register"]
    D["Login"]
    E["Soldier Dashboard"]
    F["View Soldiers"]
    H["Formation Management"]
    I["Logout"]
    J["Soldier Details"]
    G["Add Soldier"]
    K["Edit Soldier"]
    L["Delete Soldier"]
    M["View Formation Soldiers"]
    N["Soldier Details"]
    O["Edit Soldier"]
    P["Delete Soldier"]

    A --> B
    B --> C
    B --> D
    D --> E
    E --> F
    E --> H
    E --> I
    F --> J
    J --> G
    J --> K
    J --> L
    H --> M
    M --> N
    N --> O
    N --> P
```

## UML Class Diagram

```mermaid
---
title: Soldier Management System - UML Class Diagram
---
classDiagram

    class Soldier {
        -Long id
        -String firstName
        -String lastName
        -String rank
        -String unit
        -String mos
        -String dutyStatus
        -String email
        -String phoneNumber
        -Long formationId
        +getId() Long
        +getFirstName() String
        +getLastName() String
        +getRank() String
        +getUnit() String
        +getMos() String
        +getDutyStatus() String
        +getEmail() String
        +getPhoneNumber() String
        +getFormationId() Long
        +setId(Long id)
        +setFirstName(String firstName)
        +setLastName(String lastName)
        +setRank(String rank)
        +setUnit(String unit)
        +setMos(String mos)
        +setDutyStatus(String dutyStatus)
        +setEmail(String email)
        +setPhoneNumber(String phoneNumber)
        +setFormationId(Long formationId)
    }

    class User {
        -Long id
        -String username
        -String password
        -String role
        -String formationAccessLevel
        -Long formationId
        +login()
        +logout()
        +canAccessFormation() boolean
    }

    class Formation {
        -Long id
        -String formationName
        -String unitType
        -String location
        +getFormationDetails()
    }

    class SoldierController {
        +listSoldiers()
        +viewSoldier(Long id)
        +createSoldier()
        +updateSoldier(Long id)
        +deleteSoldier(Long id)
    }

    class SoldierService {
        +getAllSoldiers()
        +getSoldierById(Long id)
        +createSoldier(Soldier soldier)
        +updateSoldier(Soldier soldier)
        +deleteSoldier(Long id)
        +getSoldiersByFormation(Long formationId)
    }

    class SoldierRepository {
        +findAll()
        +findById(Long id)
        +save(Soldier soldier)
        +deleteById(Long id)
    }

    Formation "1" --> "*" Soldier : contains
    Formation "1" --> "*" User : grants access
    User --> Formation : assigned to
    SoldierController --> SoldierService : uses
    SoldierService --> SoldierRepository : uses
    SoldierRepository --> Soldier : manages
```
## WireFrame

![America's Flagship Seal](https://gitlab.com/bobby.estey/wikibob/-/raw/master/docs/icons/cv64AmericasFlagShip100x100.png "America's Flag Ship")

![America's Flagship Seal](https://gitlab.com/bobby.estey/wikibob/-/raw/master/docs/icons/cv64AmericasFlagShip100x100.png "America's Flag Ship")

![America's Flagship Seal](https://gitlab.com/bobby.estey/wikibob/-/raw/master/docs/icons/cv64AmericasFlagShip100x100.png "America's Flag Ship")

![America's Flagship Seal](https://gitlab.com/bobby.estey/wikibob/-/raw/master/docs/icons/cv64AmericasFlagShip100x100.png "America's Flag Ship")


## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)