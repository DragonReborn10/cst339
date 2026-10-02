# Activity 3

- Author: John Dearing
- Date: 10/02/2026

## Introduction

- This is **Activity 3: Spring Core, Bean Scopes, and REST Services**

- As a whole, the purpose of Activity 3 is learning how Spring manages business services using dependency injection and the Spring IoC container. Among others, the activities that will be performed in the course of Activity 3 include creating business service interfaces and implementations, setting up Spring Beans, dependency injection of services into controllers, and shifting the order data from the controller to the business service. In addition to the above, there will be an exploration of the life cycle of the Spring Bean as well as the comparison of Prototype, Request, Session, and Singleton bean scopes. Finally, REST services will be introduced in the context of creating JSON and XML endpoints for order data and testing them in a web browser and Postman tool.

## Activity 3 File Layout

1. activity3part1: Creating Spring Bean Services Using Spring Core
     1. src\main\java\com\gcu\activity3part1\Activity3part1Application.java (The main Spring Boot application file that starts the application and configures component scanning.)
     2. src\main\java\com\gcu\business\OrdersBusinessServiceInterface.java (The business service interface that defines the methods used to test the service and retrieve order data.)
     3. src\main\java\com\gcu\business\OrdersBusinessService.java (The primary business service implementation that provides order data and demonstrates Spring dependency injection.)
     4. src\main\java\com\gcu\business\AnotherOrdersBusinessService.java (An alternate implementation of the orders business service interface used to demonstrate how Spring can switch between service implementations.)
     5. src\main\java\com\gcu\business\SecurityBusinessService.java (The Spring service that provides the authentication method used by the login controller.)
     6. src\main\java\com\gcu\SpringConfig.java (The Spring configuration class that defines and creates the OrdersBusinessService bean.)
     7. src\main\java\com\gcu\controller\LoginController.java (The Spring MVC controller that processes login information and uses injected business services to authenticate users and retrieve orders.)
     8. src\main\java\com\gcu\model\LoginModel.java (The model that stores the username and password entered by the user.)
     9. src\main\java\com\gcu\model\OrderModel.java (The model that represents an order using its ID, order number, product name, price, and quantity.)
     10. src\main\resources\templates\login.html (The Thymeleaf view that displays the login form.)
     11. src\main\resources\templates\orders.html (The Thymeleaf view that displays order information returned by the business service.)

2. activity3part2: Spring Bean Life Cycle and Scopes
     1. src\main\java\com\gcu\activity3part2\Activity3part2Application.java (The main Spring Boot application file that starts Activity 3 Part 2.)
     2. src\main\java\com\gcu\business\OrdersBusinessServiceInterface.java (The business service interface that defines the test, order retrieval, initialization, and destruction methods.)
     3. src\main\java\com\gcu\business\OrdersBusinessService.java (The business service implementation that demonstrates the Spring Bean initialization and destruction life cycle.)
     4. src\main\java\com\gcu\business\AnotherOrdersBusinessService.java (The alternate business service implementation that implements the same business service contract and life-cycle methods.)
     5. src\main\java\com\gcu\business\SecurityBusinessService.java (The Spring-managed security service used for authentication.)
     6. src\main\java\com\gcu\SpringConfig.java (The Spring configuration class used to configure the bean life cycle and test Prototype, Request, Session, and Singleton scopes.)
     7. src\main\java\com\gcu\controller\LoginController.java (The controller that uses the Spring-managed business services when processing login requests.)
     8. src\main\java\com\gcu\model\LoginModel.java (The model used to store login information.)
     9. src\main\java\com\gcu\model\OrderModel.java (The model used to represent order information.)

3. activity3part3: Creating REST Services Using Spring REST Controllers
     1. src\main\java\com\gcu\activity3part3\Activity3part3Application.java (The main Spring Boot application file that starts Activity 3 Part 3.)
     2. src\main\java\com\gcu\business\OrdersBusinessServiceInterface.java (The business service interface used by the REST controller to retrieve order data.)
     3. src\main\java\com\gcu\business\OrdersBusinessService.java (The business service implementation that supplies the order data returned by the REST API.)
     4. src\main\java\com\gcu\business\SecurityBusinessService.java (The Spring service responsible for the authentication functionality used by the application.)
     5. src\main\java\com\gcu\business\OrdersRestService.java (The Spring REST controller that provides JSON and XML order endpoints under the /service URL.)
     6. src\main\java\com\gcu\SpringConfig.java (The Spring configuration class responsible for creating the OrdersBusinessService bean.)
     7. src\main\java\com\gcu\model\OrderModel.java (The model that represents individual order information.)
     8. src\main\java\com\gcu\model\OrderList.java (The XML wrapper model that contains the list of orders and defines the XML orders and order elements.)
     9. pom.xml (The Maven configuration file that contains the Spring Boot dependencies and XML Binding support required by the REST service.)

## Links / Images

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)

#### Part 1 Screenshots:

- OrdersBusinessService – Console Output:

![OrdersBusinessService – Console Output](https://github.com/DragonReborn10/cst339/blob/main/activities/activity3/imgs/part1/ConsoleOutputOrders.png "OrdersBusinessService – Console Output" )

- AnotherOrdersBusinessService – Console Output:

![AnotherOrdersBusinessService – Console Output](https://github.com/DragonReborn10/cst339/blob/main/activities/activity3/imgs/part1/ConsoleOutputAnotherOrders.png "AnotherOrdersBusinessService – Console Output" )

- SecurityBusinessService – Console Output:

![SecurityBusinessService – Console Output](https://github.com/DragonReborn10/cst339/blob/main/activities/activity3/imgs/part1/ConsoleOutputSecurity.png "SecurityBusinessService – Console Output" )

- Orders Page – Business Service Data:

![rders Page – Business Service Data](https://github.com/DragonReborn10/cst339/blob/main/activities/activity3/imgs/part1/OrdersPage.png "rders Page – Business Service Data" )
  
#### Part 2 Screenshots:

- OrdersBusinessService – Prototype Scope:

![OrdersBusinessService – Prototype Scope](https://github.com/DragonReborn10/cst339/blob/main/activities/activity3/imgs/part2/PrototypeScope.png "OrdersBusinessService – Prototype Scope" )

- Using the Prototype Scope, Spring will create a new OrdersBusinessService instance each time a bean is requested by Spring. In such a case, init() will be executed several times because of creation of multiple beans rather than using a single singleton instance. 

- OrdersBusinessService – Request Scope:

![OrdersBusinessService – Request Scope](https://github.com/DragonReborn10/cst339/blob/main/activities/activity3/imgs/part2/RequestScope.png "OrdersBusinessService – Request Scope")

- Using the Request Scope, Spring will create an instance of OrdersBusinessService per HTTP request. As such, init() will be executed as new request scoped beans are created each time a request is processed.

- OrdersBusinessService – Session Scope:

![OrdersBusinessService – Session Scope](https://github.com/DragonReborn10/cst339/blob/main/activities/activity3/imgs/part2/SessionScope.png "OrdersBusinessService – Session Scope" )

- Using the Session Scope, Spring will keep one OrdersBusinessService instance per browser session. In case the same session makes repeated requests, the same bean will be used; otherwise, the new bean instance will be created for a new browser session.

- OrdersBusinessService – Singleton Scope:

![OrdersBusinessService – Singleton Scope](https://github.com/DragonReborn10/cst339/blob/main/activities/activity3/imgs/part2/SingletonScope.png "OrdersBusinessService – Singleton Scope" )

- Using the Singleton Scope, Spring will create one OrdersBusinessService bean and re-use it through the application context. In this case, init() will be executed when the singleton bean instance is initialized instead of execution on each request.

#### Part 3 Screenshots:

- REST Service – JSON Browser Response:

![REST Service – JSON Browser Response](https://github.com/DragonReborn10/cst339/blob/main/activities/activity3/imgs/part3/jsonPage.png "REST Service – JSON Browser Response")

- REST Service – XML Browser Response:

![REST Service – XML Browser Response](https://github.com/DragonReborn10/cst339/blob/main/activities/activity3/imgs/part3/XMLPage.png "REST Service – XML Browser Response" )

## Research Questions

- What is the difference between the @Component, @Service, and @Bean annotations? When you would use one versus the other?

     - The @Component, @Service, and @Bean annotations are used to register objects into Spring IoC(Inversion of Control) container, however, they have different usages. The @Component is a general-purpose annotation for automatic detection of classes which are considered as utilities or helper classes. The @Service annotation is a special type of @Component for classes implementing business logic. The @Bean annotation allows registering objects created manually in the method level, and it can be used for third-party libraries or custom objects.

     - Examples:
     ```java
     @Component
     public class SoldierHelper {
     public String formatName(String name) {
        return name.toUpperCase();
     }
     }
     ```
     ```java 
     @Service
     public class SoldierService {
     public String getSoldierRank() {
        return "Sergeant";
     }
     }
     ```
     ```java
     @Configuration
     public class AppConfig {

     @Bean
     public ObjectMapper objectMapper() {
        return new ObjectMapper();
     }
     }
     ```

     - Reference: Difference Between @Component, @Repository, @Service, and @Controller Annotations in Spring. (10 Jun, 2026). GeeksforGeeks. https://www.geeksforgeeks.org/java/difference-between-component-repository-service-and-controller-annotations-in-spring/

- Why does an inversion of control (IoC) container force you to design and code to interface contracts?

    - Inversion of Control (IoC) container is responsible for the instantiation and dependency management of objects, promoting the practice of coding against interfaces rather than class. This results in increased flexibility and easier maintenance of code as implementations may change without affecting the business logic of the system. Interfaces are not strictly needed in IoC, as Spring can instantiate concrete classes too.

- For instance, in a Army application, a NotificationService could use a Mailer interface to send reminders about training.

- References: Introduction to the Spring loC container and beans. (N.d). Spring. https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/beans.html

## What I learned

- In Activity 3, I gained knowledge about how the Spring Framework makes use of the IoC container for dependency injection and business services creation and management. I learned how to design service interfaces and their various implementations, configuring spring beans and injecting services into controllers. Furthermore, I was able to gain knowledge regarding the lifecycle of spring beans, along with the influence of the Prototypes, Requests, Sessions and Singleton scopes on bean instantiation and re-usability. In addition, I learned how to implement REST services which provide data about orders in JSON and XML format.