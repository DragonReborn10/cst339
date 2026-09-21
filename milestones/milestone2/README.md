# Milestone 2

- Author: John Dearing
- Date: 09/20/2026

## Introduction

- This is **Milestone 2:**

- In Milestone 2, I have fulfilled the requirements of the project by designing and implementing the core components of the Soldier Management System with the help of Spring Boot, Spring MVC, Thymeleaf, and Bootstrap. I have used the techniques described in Activity 2 for building MVC controllers, models, binding user inputs through Thymeleaf forms and validating user data with Jakarta Bean validation @Valid and BindingResult tags. Header and footer fragments have been designed in defaultTemplate.html. The responsiveness of Home, Registration, Login, and Dashboard pages has been achieved using Bootstrap and custom CSS. The Registration and Login features have been developed without any database.

## Task Compleated

1. Developed the main Soldier Management System application using Spring Boot and Spring MVC.

2. Created the Home, Registration, Login, and Dashboard pages using Thymeleaf.

3. Created reusable header and footer fragments using defaultTemplate.html.

4. Implemented responsive page layouts using Bootstrap and custom CSS.

5. Created UserModel and LoginModel classes for registration and login data.

6. Implemented registration and login forms using Thymeleaf form binding.

7. Implemented Jakarta Bean Validation using @Valid, validation annotations, and BindingResult.

8. Added validation error messages to the Registration and Login pages.

9. Implemented simulated login functionality without a database.

10. Implemented navigation between the Home, Registration, Login, and Dashboard pages.

11. Created and updated the Current Architecture, Sitemap, and UML Class Diagrams.

12. Tested page navigation, form validation, responsive layouts, and application functionality.

## Known Issues

- Registration information is not currently saved to a database.
- Login authentication is simulated for Milestone 2.
- Forgot Username and Forgot Password functionality has not yet been implemented.
- Formation Level Access and View My Soldier Data are currently dashboard options but their full functionality will be implemented in later milestones.
- Full authentication and authorization will be implemented during a later security milestone.

## Current Architecture

```mermaid
---
title: Soldier Management System - Current Architecture
---
flowchart TB

    User["User / Web Browser"]

    subgraph Presentation["Presentation Layer"]
        HomeView["Home Page - home.html"]
        RegisterView["Registration Page - register.html"]
        LoginView["Login Page - login.html"]
        DashboardView["Dashboard Page - dashboard.html"]
    end

    subgraph Controllers["Spring MVC Controllers"]
        HomeController["HomeController"]
        RegistrationController["RegistrationController"]
        LoginController["LoginController"]
        DashboardController["DashboardController"]
    end

    subgraph Models["Application Models"]
        UserModel["UserModel"]
        LoginModel["LoginModel"]
    end

    subgraph Validation["Validation"]
        JakartaValidation["Jakarta Bean Validation"]
        BindingResult["BindingResult"]
    end

    subgraph Session["Session Management"]
        HttpSession["HttpSession"]
    end

    User --> HomeView
    User --> RegisterView
    User --> LoginView
    User --> DashboardView

    HomeView <--> HomeController
    RegisterView <--> RegistrationController
    LoginView <--> LoginController
    DashboardView <--> DashboardController

    RegistrationController --> UserModel
    LoginController --> LoginModel

    UserModel --> JakartaValidation
    LoginModel --> JakartaValidation

    JakartaValidation --> BindingResult

    RegistrationController --> BindingResult
    LoginController --> BindingResult

    LoginController --> HttpSession
    DashboardController --> HttpSession
```

## Current Sitemap

```mermaid
---
title: Soldier Management System - Current Sitemap
---
flowchart TD

    A["Soldier Management System"]
    B["Home"]
    C["Register"]
    D["Login"]
    E["Soldier Dashboard"]
    F["Formation Level Access"]
    G["View My Soldier Data"]
    H["Logout"]

    A --> B

    B --> C
    B --> D

    C --> D

    D --> E

    E --> F
    E --> G
    E --> H

    H --> B
```

## Current UML Class Diagram

```mermaid
---
title: Soldier Management System - Current UML Class Diagram
---
classDiagram

    class SoldierManagementSystemApplication {
        +main(String[] args) void
    }

    class HomeController {
        +home(Model model) String
    }

    class RegistrationController {
        +showRegistration(Model model) String
        +register(UserModel userModel, BindingResult bindingResult, Model model) String
    }

    class LoginController {
        +showLogin(Model model) String
        +login(LoginModel loginModel, BindingResult bindingResult, Model model, HttpSession session) String
    }

    class DashboardController {
        +dashboard(Model model) String
        +logout(HttpSession session) String
    }

    class UserModel {
        -String firstName
        -String lastName
        -String email
        -String phoneNumber
        -String username
        -String password
        +getFirstName() String
        +setFirstName(String firstName) void
        +getLastName() String
        +setLastName(String lastName) void
        +getEmail() String
        +setEmail(String email) void
        +getPhoneNumber() String
        +setPhoneNumber(String phoneNumber) void
        +getUsername() String
        +setUsername(String username) void
        +getPassword() String
        +setPassword(String password) void
    }

    class LoginModel {
        -String username
        -String password
        +getUsername() String
        +setUsername(String username) void
        +getPassword() String
        +setPassword(String password) void
    }

    SoldierManagementSystemApplication ..> HomeController : scans
    SoldierManagementSystemApplication ..> RegistrationController : scans
    SoldierManagementSystemApplication ..> LoginController : scans
    SoldierManagementSystemApplication ..> DashboardController : scans

    RegistrationController --> UserModel : uses
    LoginController --> LoginModel : uses
```

## Screenshots

- Login page - empty form:

![Login page - empty form](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone2/imgs/login.png "Login page - empty form")

- Login page - empty form with errors:

![Login page - empty form with errors](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone2/imgs/loginerror.png "Login page - empty form with errors")

- Register page - empty form:

![Register page - empty form](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone2/imgs/register.png "Register page - empty form")

- Register page - empty form with errors:

![Register page - empty form with errors](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone2/imgs/registererror.png "Register page - empty form with errors")

- Home page:

![Home page](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone2/imgs/home.png "Home page")

- Dashboard page:

![Dashboard page](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone2/imgs/dashboard.png "Dashboard page")

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
