# Milestone 5 Project Status

- Author: John Dearing
- Date: 10/10/2026

## Tasks Completed

1. Retained the Milestone 4 Home, Registration, Login, Dashboard, and Create Soldier functionality.
2. Extended `SoldierDataService` with all-record retrieval, exact-ID retrieval, partial ID/name searching, Update, and Delete using `JdbcTemplate`.
3. Extended `SoldierService` with matching business operations.
4. Added Soldier list/search, Edit, Update, and Delete routes to `SoldierController`.
5. Added `soldiers.html` to display records and search by Soldier ID or name.
6. Added `edit-soldier.html` to load and validate existing Soldier data.
7. Connected Dashboard **View My Soldier Data** to `/soldiers`.
8. Preserved the Soldier ID as the immutable primary key during Update.
9. Implemented POST-based Delete with a browser confirmation prompt.
10. Preserved `HttpSession` checks on Soldier CRUD routes.
11. Preserved the MySQL schema and the direct `soldiers.unit` mapping.
12. Updated Milestone 5 planning, design, service API, project status, and test documentation.

## Known Issues

- Passwords are still stored/compared without secure hashing; Spring Security is planned for Milestone 6.
- Session checks are temporary. The current code does not enforce per-user roles or formation-level permissions; any logged-in user can view/edit/delete the available Soldier records.
- CSRF protection is not yet integrated. POST-based Delete and browser confirmation do not provide CSRF defense.
- `formation_id` is not assigned by the current Create/Edit Soldier workflow; the entered Unit is stored in `soldiers.unit`.
- Formation Level Access remains a future feature.
- Forgot Username and Forgot Password are not implemented.
- Search uses database `LIKE` patterns and is intended for a course-scale fictional dataset; paging may be needed for larger datasets.
- The application depends on local MySQL availability.

## Risks

- Deletion permanently removes a record unless the database is backed up; use fictional test data and verify the selected Soldier ID.
- A logged-in user currently has broad Soldier data access; role/formation authorization must be added before production use.
- Plaintext passwords and missing CSRF protection are security risks requiring remediation.
- MySQL downtime or incorrect credentials interrupt Registration, Login, and Soldier CRUD operations.
- Concurrent edits or deletes may affect the same record; row-count checks detect missing records but do not prevent lost updates.
- Database constraints and input validation must remain aligned to avoid data-integrity errors.
- Real Soldier/personnel information must not be used in this course demonstration.

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone5 README.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/README.md)
- [Milestone5 analysisPlanning.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/analysisPlanning.md)
- [Milestone5 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/design.md)
- [Milestone5 test.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/test.md)