# Milestone 4 Project Testing

- Author: John Dearing
- Date: 10/04/2026

## Milestone 4 Testing

The following functionality was tested:

1. Home page loads successfully.

2. Registration page loads successfully.

3. Invalid registration information displays Jakarta Bean Validation errors.

4. Valid registration information is persisted to the MySQL `users` table.

5. Duplicate usernames are rejected.

6. Login page loads successfully.

7. Invalid Login form information displays validation errors.

8. A registered database user can successfully authenticate.

9. Incorrect database credentials are rejected.

10. Successful Login redirects the user to the Dashboard.

11. The Dashboard is available after successful Login.

12. Create Soldier is accessible from the Dashboard.

13. Direct access to Create Soldier without a valid Login session redirects to Login.

14. Empty or invalid Soldier information displays validation errors.

15. Valid Soldier information is persisted to the MySQL `soldiers` table.

16. The Unit entered through the Create Soldier form is stored in the `soldiers.unit` column.

17. Duplicate Soldier IDs are rejected.

18. Successful Soldier creation displays a success message.

19. Failed Soldier creation displays an error message.

20. Logout invalidates the current session and returns the user to the Home page.

21. Application pages maintain the common theme and responsive layout.

22. The application successfully connects to MySQL through Spring `JdbcTemplate`.

23. The application successfully compiles and packages using Maven.

24. The executable Spring Boot JAR runs successfully from the terminal outside Visual Studio Code.

25. Registration, Login, Dashboard navigation, and Create Soldier functionality operate successfully from the packaged application.

## Screenshots

- Registration success page:

![Registration success page](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone3/imgs/Dashboard.png "Registration success page")

- MySQL users table showing fictional registered user:

![MySQL users table showing fictional registered user](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone3/imgs/CreateSolder.png "MySQL users table showing fictional registered user")

- Create Soldier success message:

![Create Soldier success message](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone3/imgs/CreateSoldierError.png "Create Soldier success message")

- MySQL soldiers table showing fictional Soldier record:

![MySQL soldiers table showing fictional Soldier record](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone3/imgs/CreateSoldierError.png "MySQL soldiers table showing fictional Soldier record")

- Registration duplicate username error:

![Registration duplicate username error](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone3/imgs/CreateSoldierError.png "Registration duplicate username error")

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone4 README.md]()
- [Milestone4 analysisPlanning.md]()
- [Milestone4 design.md]()
- [Milestone4 projectStatus.md]()