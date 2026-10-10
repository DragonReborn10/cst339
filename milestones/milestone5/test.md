# Milestone 5 Project Testing

- Author: John Dearing
- Date: 10/10/2026

## Milestone 5 Testing

**Test execution summary:** The Milestone 5 test suite was executed locally. Testing covered search, CRUD, validation, session access, regression, Maven packaging, and executable JAR startup. Fictional Soldier records were used.

| Test ID | Test Case | Expected Result | Actual Result |
| --- | --- | --- | --- |
| TC-01 | View Soldier list while logged in | All Soldiers displayed | All Soldiers displayed |
| TC-02 | Open list while logged out | Redirect to Login | Redirect to Login |
| TC-03 | Search exact Soldier ID | Correct Soldier displayed | Correct Soldier displayed |
| TC-04 | Search partial Soldier ID | Matching Soldiers displayed | Matching Soldiers displayed |
| TC-05 | Search first name | Matching Soldiers displayed | Matching Soldiers displayed |
| TC-06 | Search last name | Matching Soldiers displayed | Matching Soldiers displayed |
| TC-07 | Search full first and last name | Correct Soldier displayed | Correct Soldier displayed |
| TC-08 | Search partial first and last name | Matching Soldiers displayed | Matching Soldiers displayed |
| TC-09 | Search with different capitalization | Equivalent matches | Equivalent matches |
| TC-10 | Search nonexistent ID/name | No Soldiers found | No Soldiers found |
| TC-11 | Clear search | All Soldiers displayed | All Soldiers displayed |
| TC-12 | Open Edit Soldier | Existing data populated | Existing data populated |
| TC-13 | Update valid Soldier information | Saved changes appear in database | Saved changes appear in database |
| TC-14 | Submit invalid Edit data | Validation errors shown | Validation errors shown |
| TC-15 | Edit nonexistent Soldier ID | Error and redirect | Error and redirect |
| TC-16 | Cancel Edit | No changes persisted | No changes persisted |
| TC-17 | Delete test Soldier | Record removed | Record removed |
| TC-18 | Cancel Delete confirmation | Record retained | Record retained |
| TC-19 | Delete nonexistent Soldier ID | Error displayed | Error displayed |
| TC-20 | Create valid Soldier | New row persisted | New row persisted |
| TC-21 | Create duplicate Soldier ID | Duplicate rejected | Duplicate rejected |
| TC-22 | Verify Dashboard navigation | View My Soldier Data opens /soldiers | View My Soldier Data opens /soldiers |
| TC-23 | Search with blank text | All Soldiers displayed | All Soldiers displayed |
| TC-24 | Verify unchanged Soldier ID after edit | Original primary key retained | Original primary key retained |
| TC-25 | Verify existing Registration/Login | Milestone 4 functions remain operational | Milestone 4 functions remain operational |

## Screenshots

- Soldier list with all records:

![Soldier list with all records](imgs\SoldierListAll.png "Soldier list with all records")

- Search by partial ID:

![Search by partial ID](imgs\PartialID.png "Search by partial ID")

- Search by partial name:

![Search by partial name](imgs\PartialName.png "Search by partial name")

- Edit Soldier with populated fields:

![Edit Soldier with populated fields](imgs\EditSoldier.png "Edit Soldier with populated fields")

- Delete confirmation:

![Delete confirmation](imgs\DeleteConfirmation.png "Delete confirmation")

- Messed up Case Search:

![Messed up Case Search](imgs\MessedUpCase.png "Messed up Case Search")

## Links

- [John Dearing CST339 Repository](https://github.com/DragonReborn10/cst339/tree/main)
- [Grand Canyon University](https://www.gcu.edu/)
- [Milestone5 README.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/README.md)
- [Milestone5 analysisPlanning.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/analysisPlanning.md)
- [Milestone5 design.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/design.md)
- [Milestone5 projectStatus.md](https://github.com/DragonReborn10/cst339/blob/main/milestones/milestone5/projectStatus.md)