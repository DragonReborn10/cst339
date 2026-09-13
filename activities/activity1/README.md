# Activity 1

- Author:  John Dearing
- Date:  09/13/2026

## Introduction

- This is **Activity 1: Tools Installation, Validation, and Learning Maven**

- The goal of this activity is to gain knowledge on the tools and techniques used in the development and deployment of Spring Boot application. This activity gives us the opportunity to practice and apply our knowledge on how to configure Spring Tool Suite, create and test a simple “Hello World” Spring Boot application, and ensure that our local development environment is functioning properly. This activity serves as an introduction to the use of Maven in creating a project and handling its dependencies. With the use of the POM file of Maven, creation of JAR file and executing the application will allow us to develop necessary skills for the future.

## Activity 1 file layout
1. activity1part1: Tools Installation and Validation
     1. src\main\java\com\gcu\activity1part1\Activity1part1Application.java (Spring Boot Hello World! java code)
     2. src\main\resources\static\index.html (localhost web Hello World!)
2. activity1part2: Learning Maven 
     1. src\main\java\com\gcu\activity1part2\Activity1part2Application.java (Spring Boot Hello World! java code)
     2. src\main\resources\static\index.html (localhost web Hello World!)
     3. target\helloworld.jar (generated helloworld.jar)
3. imgs
     1. Part 1 (Screenshots for activity 1 part 1)
     2. Part 2 (Screenshots for activity 1 part 2)

## Links / Images

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)

#### Part 1 Screenshots:

- Spring Tools Install:

![Spring Tools Install](https://github.com/DragonReborn10/cst339/blob/main/activities/activity1/imgs/Part1/SpringInstall.png?raw=true "Spring Tools Install" )

- Spring Hello World Console Message:

![Spring Hello World Console Message](https://github.com/DragonReborn10/cst339/blob/main/activities/activity1/imgs/Part1/SpringHelloWorldConsoleMessage.png?raw=true "Spring Hello World Console Message")

- White label ErrorPage:

![White label ErrorPage](https://github.com/DragonReborn10/cst339/blob/main/activities/activity1/imgs/Part1/WhitelabelErrorPage.png?raw=true "White label ErrorPage")

- html Hello World Message:

![html Hello World Message](https://github.com/DragonReborn10/cst339/blob/main/activities/activity1/imgs/Part1/htmlHelloWorldMessage.png?raw=true "html Hello World Message")

#### Part 2 Screenshots:

- Maven Build Success:

![Maven Build Success](https://github.com/DragonReborn10/cst339/blob/main/activities/activity1/imgs/Part2/MavenBuildSuccess.png?raw=true "Maven Build Success" )

- helloworld.jar Target File:

![helloworld.jar Target File](https://github.com/DragonReborn10/cst339/blob/main/activities/activity1/imgs/Part2/HelloWorldJarTargetFile.png?raw=true "helloworld.jar Target File")

- Hello World! LocalHost:

![Hello World! LocalHost](https://github.com/DragonReborn10/cst339/blob/main/activities/activity1/imgs/Part2/HelloWorldLocalHost.png?raw=true "Hello World! LocalHost")

## Research Questions

- Research Spring Boot. Compare building dynamic web applications when using Spring Boot versus just using the Spring framework. How do they differ? 
     - Spring Boot is a subset of the Spring Framework, which helps in developing dynamic web applications easily. The Spring Framework provides basic functionalities like Dependency Injection (DI) and Inversion of Control (loC), but it needs lots of configuration. Spring Boot follows convention over configuration to configure automatically the components of the framework according to the dependencies of the project. Spring Boot has an inbuilt web server, where the application can be started with a JAR file without using any server externally. Spring Boot uses starter packages for easy management of dependencies and uses Actuator for monitoring and checking the health of the application. By using Spring Boot, developers can reduce the efforts of configuration and setup of the application.

     - Reference:
     Spring vs Spring Boot. (25 Apr, 2026). GeeksforGeeks. https://www.geeksforgeeks.org/java/difference-between-spring-and-spring-boot/

- Research Gradle, which is another popular build and dependency management tool. How does it differ from Maven?

     - Gradle and Maven are build automation tools that help in compiling code, testing and managing project dependencies.
     Maven employs XML files for configuration whereas Gradle has a flexible code based DSL that can be written using Groovy or Kotlin language.
     Gradle is usually faster due to eliminating unnecessary tasks. It is faster as well due to its advanced dependency management and API/implementation dependency segregation.
     Maven is the recommendation for normal Java applications due to its ease of use. Gradle is more suitable for bigger multimodule projects.

     - Referance:
     Difference between Gradle and Maven. (14 Mar, 2024). GeeksforGeeks. https://www.geeksforgeeks.org/java/difference-between-gradle-and-maven/

## What I learned

Through Activity, I gained knowledge on how to create and execute a simple Spring Boot Application with Java. I was introduced to how to add dependencies, create a static HTML page, and test the program through localhost:8080. In Part 2, I was introduced to how Maven works on projects through pom.xml, how to build a Spring Boot Application into a runnable JAR file through clean package and finally how to execute the JAR file through the command prompt.