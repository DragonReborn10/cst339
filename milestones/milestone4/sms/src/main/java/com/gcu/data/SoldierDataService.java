package com.gcu.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.model.SoldierModel;

/**
 * Data access service responsible for database operations
 * involving Soldier records.
 *
 * This class uses Spring JdbcTemplate to communicate
 * with the MySQL database.
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
     * Creates a new Soldier record in the database.
     * The Soldier's assigned unit is provided by the
     * Create Soldier form and stored with the Soldier record.
     *
     * @param soldierModel Soldier information to persist
     * @return true if exactly one Soldier was inserted
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
                     phone_number)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
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
                soldierModel.getPhoneNumber());

        return rowsAffected == 1;
    }

    /**
     * Checks whether a Soldier ID already exists.
     *
     * @param soldierId Soldier ID to search for
     * @return true if the Soldier ID already exists
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