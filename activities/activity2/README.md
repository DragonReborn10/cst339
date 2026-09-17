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

## Links / Images

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)

#### Part 1 Screenshots:

- Hello World Controller – Test 1:

![Hello World Controller – Test 1]( "Hello World Controller – Test 1" )

- Spring MVC Model – Test 2:

![Spring MVC Model – Test 2]( "Spring MVC Model – Test 2" )

- ModelAndView – Test 3:

![ModelAndView – Test 3]( "ModelAndView – Test 3" )

- Request Parameter – Test 4:

![Request Parameter – Test 4]( "Request Parameter – Test 4" )

- ModelAndView – Test 3 with Navigation Links:

![ModelAndView – Test 3 with Navigation Links]( "ModelAndView – Test 3 with Navigation Links" )

- Activity 2 Home Page:

![Activity 2 Home Page]( "Activity 2 Home Page" )
  
#### Part 2 Screenshots:

#### Part 3 Screenshots:

## Research Questions



## What I learned

