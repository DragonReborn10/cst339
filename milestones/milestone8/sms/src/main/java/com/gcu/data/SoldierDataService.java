package com.gcu.data;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.model.SoldierModel;

/**
 * Data access service responsible for database operations
 * involving Soldier records.
 *
 * Implements the DAO design pattern using Spring JdbcTemplate.
 */
@Repository
public class SoldierDataService {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor-based dependency injection.
     *
     * @param jdbcTemplate Spring JDBC template
     */
    public SoldierDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Maps a database result row to a SoldierModel object.
     *
     * @param rs database result set
     * @param rowNum current row number
     * @return populated SoldierModel
     */
    private SoldierModel mapSoldier(
            java.sql.ResultSet rs,
            int rowNum) throws java.sql.SQLException {

        SoldierModel soldier = new SoldierModel();

        soldier.setSoldierId(rs.getString("soldier_id"));
        soldier.setFormationId(rs.getObject("formation_id", Long.class));
        soldier.setFirstName(rs.getString("first_name"));
        soldier.setLastName(rs.getString("last_name"));
        soldier.setRank(rs.getString("rank"));
        soldier.setUnit(rs.getString("unit"));
        soldier.setMos(rs.getString("mos"));
        soldier.setDutyStatus(rs.getString("duty_status"));
        soldier.setEmail(rs.getString("email"));
        soldier.setPhoneNumber(rs.getString("phone_number"));

        return soldier;
    }

    /**
     * Creates a new Soldier record.
     *
     * @param soldierModel Soldier information
     * @return true if exactly one row was inserted
     */
    public boolean createSoldier(SoldierModel soldierModel) {

        String sql = """
                INSERT INTO soldiers
                    (soldier_id,
                     first_name,
                     last_name,
                     `rank`,
                     unit,
                     mos,
                     duty_status,
                     email,
                     phone_number, formation_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        int rowsAffected = jdbcTemplate.update(
                sql,
                soldierModel.getSoldierId(),
                soldierModel.getFirstName(),
                soldierModel.getLastName(),
                soldierModel.getRank(),
                soldierModel.getUnit(),
                soldierModel.getMos(),
                soldierModel.getDutyStatus(),
                soldierModel.getEmail(),
                soldierModel.getPhoneNumber(),
                soldierModel.getFormationId());

        return rowsAffected == 1;
    }

    /**
     * Retrieves all Soldiers, sorted by last and first name.
     *
     * @return list of all Soldiers
     */
    public List<SoldierModel> getAllSoldiers() {

        String sql = """
                SELECT soldier_id,
                       first_name,
                       last_name,
                       `rank`,
                       unit,
                       mos,
                       duty_status,
                       email,
                       phone_number,
                       formation_id
                FROM soldiers
                ORDER BY last_name, first_name
                """;

        return jdbcTemplate.query(sql, this::mapSoldier);
    }

    /**
     * Retrieves one Soldier using an exact Soldier ID.
     *
     * Used when editing or deleting a specific Soldier.
     *
     * @param soldierId exact Soldier ID
     * @return matching Soldier or null if not found
     */
    public SoldierModel getSoldierById(String soldierId) {

        String sql = """
                SELECT soldier_id,
                       first_name,
                       last_name,
                       `rank`,
                       unit,
                       mos,
                       duty_status,
                       email,
                       phone_number,
                       formation_id
                FROM soldiers
                WHERE soldier_id = ?
                """;

        List<SoldierModel> soldiers = jdbcTemplate.query(
                sql,
                this::mapSoldier,
                soldierId);

        return soldiers.isEmpty() ? null : soldiers.get(0);
    }

    /**
     * Searches Soldiers by Soldier ID, first name,
     * last name, or full name.
     *
     * Supports:
     * - Exact and partial Soldier IDs
     * - Full and partial first names
     * - Full and partial last names
     * - Full and partial names entered together
     * - Case-insensitive matching
     *
     * Examples:
     * TEST001, TEST, James, Smi, James Smith, Jam Smi
     *
     * @param searchTerm search text entered by the user
     * @return list of matching Soldiers
     */
    public List<SoldierModel> searchSoldiers(String searchTerm) {

        // An empty search returns all Soldiers.
        if (searchTerm == null || searchTerm.isBlank()) {
            return getAllSoldiers();
        }

        String trimmedSearch = searchTerm.trim();

        // Normalize whitespace between words.
        trimmedSearch = trimmedSearch.replaceAll("\\s+", " ");

        // Escape SQL LIKE wildcard characters so user input
        // is treated as literal text rather than a pattern.
        String escapedSearch = trimmedSearch
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_");

        String searchPattern = "%" + escapedSearch + "%";

        String sql = """
                SELECT soldier_id,
                       first_name,
                       last_name,
                       `rank`,
                       unit,
                       mos,
                       duty_status,
                       email,
                       phone_number,
                       formation_id
                FROM soldiers
                WHERE LOWER(soldier_id) LIKE LOWER(?) ESCAPE '!'
                   OR LOWER(first_name) LIKE LOWER(?) ESCAPE '!'
                   OR LOWER(last_name) LIKE LOWER(?) ESCAPE '!'
                   OR LOWER(CONCAT(first_name, ' ', last_name))
                      LIKE LOWER(?) ESCAPE '!'
                ORDER BY last_name, first_name
                """;

        // For a two-part name search, allow partial matching
        // of the first and last names independently.
        String[] nameParts = trimmedSearch.split(" ");

        if (nameParts.length == 2) {

            String firstPattern = "%" + nameParts[0]
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_") + "%";

            String lastPattern = "%" + nameParts[1]
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_") + "%";

            sql = """
                    SELECT soldier_id,
                           first_name,
                           last_name,
                           `rank`,
                           unit,
                           mos,
                           duty_status,
                           email,
                           phone_number,
                       formation_id
                    FROM soldiers
                    WHERE LOWER(soldier_id) LIKE LOWER(?) ESCAPE '!'
                       OR LOWER(first_name) LIKE LOWER(?) ESCAPE '!'
                       OR LOWER(last_name) LIKE LOWER(?) ESCAPE '!'
                       OR LOWER(CONCAT(first_name, ' ', last_name))
                          LIKE LOWER(?) ESCAPE '!'
                       OR (
                           LOWER(first_name) LIKE LOWER(?) ESCAPE '!'
                           AND LOWER(last_name) LIKE LOWER(?) ESCAPE '!'
                       )
                    ORDER BY last_name, first_name
                    """;

            return jdbcTemplate.query(
                    sql,
                    this::mapSoldier,
                    searchPattern,
                    searchPattern,
                    searchPattern,
                    searchPattern,
                    firstPattern,
                    lastPattern);
        }

        return jdbcTemplate.query(
                sql,
                this::mapSoldier,
                searchPattern,
                searchPattern,
                searchPattern,
                searchPattern);
    }

    /**
     * Updates an existing Soldier record.
     *
     * Soldier ID remains unchanged because it is
     * the primary key.
     *
     * @param soldierModel updated Soldier information
     * @return true if exactly one row was updated
     */
    public boolean updateSoldier(SoldierModel soldierModel) {

        String sql = """
                UPDATE soldiers
                SET first_name = ?,
                    last_name = ?,
                    `rank` = ?,
                    unit = ?,
                    mos = ?,
                    duty_status = ?,
                    email = ?,
                    phone_number = ?,
                    formation_id = ?
                WHERE soldier_id = ?
                """;

        int rowsAffected = jdbcTemplate.update(
                sql,
                soldierModel.getFirstName(),
                soldierModel.getLastName(),
                soldierModel.getRank(),
                soldierModel.getUnit(),
                soldierModel.getMos(),
                soldierModel.getDutyStatus(),
                soldierModel.getEmail(),
                soldierModel.getPhoneNumber(),
                soldierModel.getFormationId(),
                soldierModel.getSoldierId());

        return rowsAffected == 1;
    }

    /**
     * Deletes a Soldier using the exact Soldier ID.
     *
     * @param soldierId Soldier ID to delete
     * @return true if exactly one row was deleted
     */
    public boolean deleteSoldier(String soldierId) {

        String sql = """
                DELETE FROM soldiers
                WHERE soldier_id = ?
                """;

        int rowsAffected = jdbcTemplate.update(
                sql,
                soldierId);

        return rowsAffected == 1;
    }

    /**
     * Checks whether a Soldier ID already exists.
     *
     * @param soldierId exact Soldier ID
     * @return true if Soldier exists
     */
    public boolean soldierExists(String soldierId) {

        String sql = """
                SELECT COUNT(*)
                FROM soldiers  
                WHERE soldier_id = ?
                """;

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                soldierId);

        return count != null && count > 0;
    }
} 