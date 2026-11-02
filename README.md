# CST-339 Programming in Java III

## Introduction

Grand Canyon University's Programming in Java III (CST-339) focuses on developing enterprise web applications using the Java programming language and the Spring framework. The course covers object-oriented programming, Spring Boot, Spring MVC, Spring Core, dependency injection, validation, responsive web design, relational database design, application security, REST API development, and other enterprise application development principles.

My milestone course project is the **Soldier Management System (SMS)**. This application has been developed incrementally throughout the course using an N-Layer architecture. The purpose of the application is to provide a web-based system for managing Soldier information, including individual Soldier records, formation-level personnel management, and role-based access to authorized information.

Through Milestone 8, the application includes user registration, secure database-backed authentication, role-based Dashboard navigation, Soldier creation, display, search, editing, deletion, formation-level access restrictions, individual Soldier data access, and Soldier deletion requests requiring administrator review.

Spring Security provides authentication and authorization, while Spring business services and dependency injection separate application logic from the presentation layer. Spring JDBC and MySQL provide persistent storage for registered users, Soldiers, formations, user access information, and deletion requests.

The project also includes two authenticated REST API endpoints for retrieving Soldier information in JSON format. These services were introduced in Milestone 7 and remain part of the application.

Milestone 8 focuses on implementing role-based and formation-level authorization, improving application security, reviewing and documenting the code, performing regression testing, and preparing the Soldier Management System for final submission.

### Technologies

The project currently uses:

- Java 17
- Spring Boot
- Spring MVC
- Spring Core
- Spring Beans
- Spring Security
- Spring Security Test
- Spring REST Controllers
- Spring JDBC
- JdbcTemplate
- Dependency Injection
- MySQL
- MAMP
- Thymeleaf
- Jakarta Bean Validation
- Bootstrap
- HTML
- CSS
- Maven
- Maven Wrapper
- Maven JavaDoc Plugin
- JUnit
- MockMvc
- HTTP Basic Authentication
- BCrypt Password Encoding
- OpenAPI 3.0.3
- Swagger Editor
- Git
- GitHub
- Visual Studio Code
- Embedded Tomcat

The application uses Spring Security for form-based browser authentication and HTTP Basic authentication for its REST API endpoints.

### Application Features

Through Milestone 8, the Soldier Management System includes:

- Responsive Home page
- User Registration page
- Registration form validation
- Persistent user registration
- Duplicate username checking
- Secure Login page
- Database-backed user authentication
- BCrypt password hashing
- Spring Security authentication
- Spring Security session management
- CSRF protection for browser-based forms
- Secure Logout functionality
- Role-based Soldier Dashboard
- Role-specific Dashboard navigation
- Create Soldier functionality
- Soldier form validation
- Persistent Soldier records
- Duplicate Soldier ID checking
- Display Soldier functionality
- Soldier Search functionality
- Case-insensitive partial Soldier ID and name searches
- Edit Soldier functionality
- Delete Soldier functionality
- Full Soldier CRUD operations for authorized administrators
- Formation-level Soldier management
- Formation-based Soldier filtering
- Formation assignment integration
- Role-based access control
- ADMIN access to Soldiers across formations
- FULL_TIME_STAFF formation-level management permissions
- FIRST_SERGEANT formation-level management permissions
- COMMANDER formation-level management permissions
- BASIC individual Soldier data access
- Server-side authorization checks
- Restricted Soldier creation based on assigned formation
- Soldier deletion request submission
- Required justification for Soldier deletion requests
- Persistent deletion request records
- Administrator deletion request review workflow
- REST API for retrieving all Soldiers
- REST API for retrieving a Soldier by ID
- JSON REST API responses
- HTTP Basic authentication for REST API requests
- HTTP 401 responses for unauthenticated REST API requests
- HTTP 404 responses for nonexistent Soldier records
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
- OpenAPI REST API documentation
- Automated authentication testing
- Milestone regression testing
- Code review and refactoring
- JavaDoc and inline documentation
- Maven executable JAR packaging

The application uses fictional test information during development and demonstration.

## Milestone Assignments

| Milestone | Description |
| --- | --- |
| [Milestone 1](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone1/README.md) | Established the initial design and project plan for the Soldier Management System. Defined the application's purpose, requirements, proposed architecture, user interface concepts, sitemap, and initial technical design. |
| [Milestone 2](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone2/README.md) | Implemented the initial Spring MVC web application. Introduced Home, Registration, Login, and Dashboard pages using Spring MVC, Thymeleaf, Bootstrap, Jakarta Bean Validation, and a consistent application theme. Authentication was initially simulated without database persistence. |
| [Milestone 3](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone3/README.md) | Expanded the application with Spring Beans, Spring Core, business services, and dependency injection. Registration and Login were refactored to use business service classes. Implemented Create Soldier using a Soldier business model, Spring MVC, Thymeleaf, and Jakarta Bean Validation. Designed the initial relational database structure. |
| [Milestone 4](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone4/README.md) | Implemented persistent relational database storage using MySQL and Spring JDBC. Registration, Login, and Create Soldier were refactored to use data access services with JdbcTemplate. Added persistent user and Soldier records, database-backed authentication, duplicate record checking, and Maven executable JAR packaging. |
| [Milestone 5](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/README.md) | Expanded Soldier Management functionality by implementing Soldier display, search, editing, and deletion. Completed the Soldier CRUD workflow and preserved the existing N-Layer architecture using Spring MVC, business services, Spring JDBC, and MySQL. |
| [Milestone 6](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone6/README.md) | Integrated Spring Security into the Soldier Management System. Replaced the earlier authentication process with Spring Security form-based Login, database-backed UserDetailsService authentication, BCrypt password hashing, protected application routes, session management, CSRF protection, and secure Logout. |
| [Milestone 7](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone7/README.md) | Implemented two authenticated REST API endpoints for retrieving Soldier information in JSON format. Added GET /api/soldiers and GET /api/soldiers/{soldierId}, HTTP Basic authentication, a dedicated stateless API security filter chain, OpenAPI documentation, and REST API authentication testing. |
| [Milestone 8](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone8/README.md) | Implemented role-based access control and formation-level Soldier management. Introduced ADMIN, FULL_TIME_STAFF, FIRST_SERGEANT, COMMANDER, and BASIC permissions; restricted Soldier access by formation or linked Soldier record; added role-based Dashboard navigation, authorized Soldier creation, deletion requests, and administrator review functionality. Also includes final code review, documentation, and regression testing preparation. |

## Course Project

### Soldier Management System

The **Soldier Management System (SMS)** is the primary application developed throughout CST-339.

The application is designed to manage Soldier information within military formations while restricting access according to organizational assignments and user roles.

The project uses fictional test information during development and demonstration.

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
- Formation ID

The application also maintains supporting information for:

- Registered user accounts
- User roles and permissions
- Organizational formations
- User-to-Soldier associations
- Soldier deletion requests
- Administrator deletion request reviews

The MySQL database includes five primary tables:

| Table | Purpose |
| --- | --- |
| `formations` | Stores organizational formation information. |
| `users` | Stores registered user accounts, password hashes, and formation associations. |
| `soldiers` | Stores Soldier information and formation assignments. |
| `user_access` | Stores account roles, units, and linked Soldier IDs. |
| `soldier_delete_requests` | Stores Soldier deletion requests, justifications, statuses, and administrator review information. |

The application uses an N-Layer architecture consisting of the Presentation Layer, Controller Layer, Business Service Layer, Data Access Layer, and Database Layer.

Spring Security and the application's authorization services protect Soldier information according to the authenticated user's role and formation assignment.

## Project Progress


### Milestone 1

For Milestone 1, I started by planning out the Soldier Management System (SMS) and figuring out how I wanted the application to work. I went over the project requirements and worked on the basic design, including the architecture, navigation, page layouts, and development plan.

The main idea was to create a website that could store and manage Soldier information. I also wanted to eventually make it possible to manage Soldiers based on the military formation they belong to.

### Milestone 2

In Milestone 2, I started actually building the application instead of just planning it. I used Spring Boot and Spring MVC to create the basic website.

I created the Home, Registration, Login, and Dashboard pages using Thymeleaf, Bootstrap, and Spring MVC controllers. I also added validation to the forms so users would have to enter the correct information.

At this point, the Login page was still using simulated authentication because I had not connected the application to a database or added Spring Security yet.

### Milestone 3

For Milestone 3, I worked on making the code more organized by adding a business service layer. Instead of having all the logic inside the controllers, I moved some of it into separate service classes using Spring Beans and dependency injection.

I also updated the Registration and Login processes to use these services.

Another big part of this milestone was adding the Create Soldier feature. I used `SoldierModel`, `SoldierController`, and `SoldierService` to let users enter Soldier information through a form.

I used Jakarta Bean Validation and `BindingResult` to check the information before processing it. I also started designing the database tables and how they would connect to each other, which helped prepare for the next milestone.

### Milestone 4

Milestone 4 was when I finally connected the application to a MySQL database. Before this milestone, the application could collect information, but it was not saving everything permanently.

I added `UserDataService` and `SoldierDataService` to handle database operations. These classes use `@Repository` and Spring `JdbcTemplate` to run SQL queries.

I updated Registration so new users would be saved in the `users` table. I also added a check to make sure someone could not register with a username that already existed.

Login was changed to check the user's information against the database instead of using simulated authentication.

I also updated Create Soldier so Soldier information would be saved in the `soldiers` table. The application checks for duplicate Soldier IDs before adding a new record.

Another thing I worked on was building the application into an executable JAR file using Maven. I was able to run it from the terminal instead of having to start it through Visual Studio Code.

### Milestone 5

In Milestone 5, I added more features to the Soldier Management System so users could do more than just create Soldier records.

I added the ability to view all Soldiers, search for a Soldier, edit existing information, and delete Soldier records. This completed the basic CRUD functionality.

The Search feature allows users to look up Soldiers by entering part of their name or Soldier ID instead of having to type the exact information.

I also made it so the Soldier ID could not be changed while editing a record. This helps keep the original ID connected to the correct Soldier.

For deleting Soldiers, I used a form submission with a confirmation so a record would not be deleted just by clicking on a regular link.

I kept using the same Spring MVC, service, data access, and MySQL structure from the earlier milestones.

### Milestone 6

Milestone 6 focused on making the application more secure by adding Spring Security.

Before this milestone, I had my own Login process that checked information from the database. I replaced that with Spring Security's form-based authentication.

I added `DatabaseUserDetailsService` to retrieve registered user information from MySQL through `UserDataService`.

I also started using `BCryptPasswordEncoder` so passwords would be stored as hashes instead of plain text. Spring Security checks the password when someone tries to log in.

I protected the Dashboard and Soldier Management pages so users have to be logged in before they can access them.

I also added secure Logout functionality, session management, and CSRF protection for the forms. The Soldier CRUD features from Milestone 5 were still available after these changes.

### Milestone 7

For Milestone 7, I added REST APIs to the Soldier Management System. This made it possible to retrieve Soldier information as JSON instead of only viewing it through the website.

I created two REST API endpoints:

- `GET /api/soldiers` — Gets all Soldier records and returns them as a JSON array.
- `GET /api/soldiers/{soldierId}` — Gets one Soldier using their Soldier ID and returns the information as JSON.

I created `SoldierRestController` to handle these requests. It uses the existing `SoldierService` and `SoldierDataService`, so I did not have to create another system for getting Soldier information from the database.

I also updated `SecurityConfig` by adding a separate security filter chain for `/api/**` requests. The APIs use HTTP Basic authentication, while the regular website still uses the Login page and sessions.

If someone tries to use the API without valid credentials, the application returns `401 Unauthorized`. If the requested Soldier ID does not exist, it returns `404 Not Found`.

I created an `api-design.yaml` file using OpenAPI 3.0.3 to document the endpoints and how they work.

I also worked on automated tests for API authentication and checked that the REST API changes did not break the features from previous milestones.

### Milestone 8

In Milestone 8, I worked on controlling what different users can see and do in the Soldier Management System. This was important because not every user should be able to view or change every Soldier record.

I added five different access levels:

- `ADMIN`
- `FULL_TIME_STAFF`
- `FIRST_SERGEANT`
- `COMMANDER`
- `BASIC`

I updated the Dashboard so the options change depending on the user's role.

ADMIN users have the most access. They can view Soldiers from all formations, create new Soldier records, edit information, directly delete Soldiers, and review deletion requests.

FULL_TIME_STAFF, FIRST_SERGEANT, and COMMANDER users can manage Soldiers, but only within their assigned formation. They can create and edit Soldier records, but they cannot directly delete them. Instead, they have to submit a deletion request and explain why the Soldier should be removed.

BASIC users have much more limited access. They can only view the Soldier record connected to their own account. They cannot create, edit, or delete Soldiers, and they cannot use the formation management features.

I added `AccessService` to help check user roles and formation assignments. This is important because hiding a button on the Dashboard is not enough to stop someone from trying to access a page directly.

I also updated how formation information is used in the database. The application uses the `formations`, `users`, `user_access`, and `soldiers` tables to figure out which Soldiers a user is allowed to access.

For Create Soldier, I made changes so management users are assigned the correct formation and unit. The application checks this information on the server to prevent someone from changing it to a formation they are not supposed to manage.

Another feature I worked on was the Soldier deletion request process. Instead of letting management users delete records themselves, they have to enter a reason and submit a request. The request is saved in the `soldier_delete_requests` table with a `PENDING` status.

I also kept the REST APIs, Spring Security authentication, Soldier CRUD features, and N-Layer architecture from the previous milestones. This milestone was mainly about adding more control over who can access Soldier information while keeping the existing application working.

## Future Development

- REST API endpoints for creating Soldiers using POST
- REST API endpoints for updating Soldiers using PUT or PATCH
- REST API endpoints for deleting Soldiers using DELETE
- Pagination and sorting for large Soldier datasets
- Advanced Soldier filtering and searching
- Dedicated REST API response DTOs
- Expanded administrator role management interface
- More detailed formation hierarchy management
- Automated deletion request notifications
- Deletion request audit history and reporting
- Soft deletion and Soldier record archival
- Forgot Username functionality
- Forgot Password and password reset functionality
- Account lockout after repeated failed Login attempts
- Multifactor authentication
- JWT or OAuth 2.0 authentication for REST APIs
- API versioning and standardized error responses
- REST API rate limiting
- Additional automated integration testing
- Performance testing and optimization
- Production HTTPS configuration
- Deployment monitoring and logging
- Automatic Logout after inactivity

## Conclusion

Throughout the eight milestones of CST-339, the Soldier Management System has evolved from an initial project concept into a Java Spring Boot application using an N-Layer architecture, persistent relational database storage, secure authentication, REST APIs, and role-based Soldier management.

Milestone 1 established the initial project concept and design. Milestone 2 implemented the Spring MVC web interface, Registration, Login, validation, and Soldier Dashboard. Milestone 3 introduced Spring Beans, dependency injection, business services, and Soldier creation.

Milestone 4 implemented MySQL database persistence using Spring JDBC and JdbcTemplate. Milestone 5 expanded the application to support complete Soldier CRUD operations.

Milestone 6 introduced Spring Security, database-backed authentication, BCrypt password hashing, protected application routes, session management, CSRF protection, and secure Logout.

Milestone 7 added authenticated REST API endpoints for retrieving Soldier information in JSON format using HTTP Basic authentication.

Milestone 8 expanded the application with role-based access control, formation-level Soldier management, individual Soldier data restrictions, authorized Soldier creation, and administrator-reviewed deletion requests.