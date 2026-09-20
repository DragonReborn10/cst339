# Activity 2

- Author:  John Dearing
- Date:  09/17/2026

## Introduction

- This is **Activity 2: Spring MVC**

- In general, the objective is to find out how a Spring Boot web application follows the Model View Controller architectural design pattern. The process will include creating controller route mappings to URL addresses, passing data from the controller to HTML views via models, developing form to pass data back to the controllers, validating data inputs, displaying order data, and lastly developing a reusable Thymeleaf layout with header, navigation, logo, and footer. At the end of it all, there should be a Spring Boot application through which the user can go to a login form page, pass data, get validation errors if any, display orders, among other things.

## Activity 2 file layout
1. activity2part1: Creating Models, Views, and Controllers Using Spring MVC
     1. src\main\java\com\gcu\activity2part1\Activity2part1Application.java (The main Spring Boot application file that starts the application and configures component scanning.)
     2. src\main\java\com\gcu\HelloWorldController.java (The Spring MVC controller that handles the /hello routes)
     3. src\main\resources\templates\hello.html (The Thymeleaf view that displays messages received from HelloWorldController.)
     4. src\main\java\com\gcu\HomeController.java (The Spring MVC controller that handles the root / URL and directs the user to the Home view.)
     5. src\main\resources\templates\home.html (The Thymeleaf Home view that displays the Activity 2 welcome message and provides a link to /hello/test2.)
2. activity2part2: Creating Models, Views, and Controllers Using Spring MVC
     1. src\main\java\com\gcu\activity2part2\Activity2part2Application.java (The main Spring Boot application file that starts the application and configures component scanning.)
     2. src\main\java\com\gcu\controller\LoginController.java (The Spring MVC controller that displays the login form, processes submitted login information, validates the input, and sends order data to the Orders view.)
     3. src\main\java\com\gcu\model\LoginModel.java (The model class that stores the username and password entered on the login form and defines their validation requirements.)
     4. src\main\resources\templates\login.html (The Thymeleaf view that displays the login form and shows validation error messages for invalid user input.)
     5. src\main\java\com\gcu\model\OrderModel.java (The model class that represents an order using its ID, order number, product name, price, and quantity.)
     6. src\main\resources\templates\orders.html (The Thymeleaf view that displays the list of sample orders in a table.)
3. activity2part3: Creating Layouts Using Thymeleaf
     1. src\main\java\com\gcu\activity2part3\Activity2part3Application.java (The main Spring Boot application file that starts the application and configures component scanning.)
     2. src\main\java\com\gcu\controller\LoginController.java (The Spring MVC controller that displays the login form, processes submitted login information, validates the input, and sends order data to the Orders view.)
     3. src\main\java\com\gcu\model\LoginModel.java (The model class that stores the username and password entered on the login form and defines their validation requirements.)
     4. src\main\resources\templates\login.html (The Thymeleaf view that displays the login form and shows validation error messages for invalid user input.)
     5. src\main\java\com\gcu\model\OrderModel.java (The model class that represents an order using its ID, order number, product name, price, and quantity.)
     6. src\main\resources\templates\orders.html (The Thymeleaf view that displays the list of sample orders in a table.)
     7. src\main\resources\templates\layouts\common.html(The Thymeleaf layout file that defines the reusable header, navigation bar, GCU logo, page title, and footer.)
     8. src\main\resources\templates\layouts\defaultTemplate.html (The Thymeleaf template that combines the common header and footer with the page-specific content.)
     9. src\main\resources\static\imgs\gcu-logo.jpg.png (The image file containing the GCU logo displayed in the application's common header.)

## Links / Images

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)

#### Part 1 Screenshots:

- Hello World Controller – Test 1:

![Hello World Controller – Test 1](https://github.com/DragonReborn10/cst339/blob/main/activities/activity2/imgs/part1/test1.png "Hello World Controller – Test 1" )

- Spring MVC Model – Test 2:

![Spring MVC Model – Test 2](https://github.com/DragonReborn10/cst339/blob/main/activities/activity2/imgs/part1/test2.png "Spring MVC Model – Test 2" )

- ModelAndView – Test 3:

![ModelAndView – Test 3](https://github.com/DragonReborn10/cst339/blob/main/activities/activity2/imgs/part1/test3.png "ModelAndView – Test 3" )

- Request Parameter – Test 4:

![Request Parameter – Test 4](https://github.com/DragonReborn10/cst339/blob/main/activities/activity2/imgs/part1/test4.png "Request Parameter – Test 4" )

- ModelAndView – Test 3 with Navigation Links:

![ModelAndView – Test 3 with Navigation Links](https://github.com/DragonReborn10/cst339/blob/main/activities/activity2/imgs/part1/Test%20links.png "ModelAndView – Test 3 with Navigation Links")

- Activity 2 Home Page:

![Activity 2 Home Page](https://github.com/DragonReborn10/cst339/blob/main/activities/activity2/imgs/part1/homepage.png "Activity 2 Home Page" )
  
#### Part 2 Screenshots:

- Login Form – Submitted User Credentials:

![Login Form – Submitted User Credentials](https://github.com/DragonReborn10/cst339/blob/main/activities/activity2/imgs/part2/LoginForm.png "Login Form – Submitted User Credentials" )

- Orders Page – Sample Order Data:

![Orders Page – Sample Order Data](https://github.com/DragonReborn10/cst339/blob/main/activities/activity2/imgs/part2/OrdersPage.png "Orders Page – Sample Order Data")

- Login Form – Data Validation Errors:

![Login Form – Data Validation Errors](https://github.com/DragonReborn10/cst339/blob/main/activities/activity2/imgs/part2/LoginFormError.png "Login Form – Data Validation Errors" )

#### Part 3 Screenshots:

- Login Form – Thymeleaf Layout:

![Login Form – Thymeleaf Layout](https://github.com/DragonReborn10/cst339/blob/main/activities/activity2/imgs/part2/OrdersPage.png "Login Form – Thymeleaf Layout")

- Orders Page – Thymeleaf Layout:

![Orders Page – Thymeleaf Layout](https://github.com/DragonReborn10/cst339/blob/main/activities/activity2/imgs/part2/LoginFormError.png "Orders Page – Thymeleaf Layout" )

## Research Questions

- How does Spring MVC support the MVC design pattern? Draw a diagram that supports the answer to this question.
    - Spring MVC is a framework based on the Model-View-Controller architecture for organizing the application. Spring MVC uses one DispatcherServlet which receives user requests and directs them to the proper controller. The Model is responsible for application data and business logic, and the View is responsible for rendering the UI. The Controller handles user requests and returns data needed by the View. There is a special View Resolver that finds the right page template (for example, JSP or Thymeleaf).

    - Reference: Spring - MVC Framework. (30 Apr, 2026). GeeksforGeeks. https://www.geeksforgeeks.org/springboot/spring-mvc-framework/

- Research and identify 2 MVC Frameworks other than Spring MVC. What are the frameworks and how do they differ from Spring MVC?

    - Two other MVC-type frameworks include Django and Angular. The first one is a server-side framework written in Python that uses the Model-View-Template architecture and has a set of built-in mechanisms for database manipulation, authentication, and administrative tasks. Angular is a frontend framework created by Google in TypeScript. In contrast to Spring MVC and Django, Angular follows the component-oriented approach and works in the browser, communicating with databases using API. Spring MVC and Django produce HTML on the server side, whereas Angular, in most cases, generates a Single Page Application which is updated dynamically without a page reload.

    - Reference: AngularJS Tutorial. (31 Jan, 2026). GeeksforGeeks. https://www.geeksforgeeks.org/angular-js/angularjs/
    Django Tutorial. (10 Aug, 2026). GeeksforGeeks. https://www.geeksforgeeks.org/python/django-tutorial/

## What I learned

- In Activity 2, I learned how to make use of the Spring MVC framework for organizing the Spring Boot application with the help of models, views, and controllers. I was also able to understand how to work with forms and form validation, view data with the help of Thymeleaf templates, and even create reusable pages with common headers and footers. Finally, I was able to gain more knowledge about working with Maven for managing dependencies and building my Spring Boot application.