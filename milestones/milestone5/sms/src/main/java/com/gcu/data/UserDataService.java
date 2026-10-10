package com.gcu.data;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.model.UserModel;

/**
 * Data access service responsible for database operations
 * involving application users.
 *
 * This class implements the DAO design pattern and uses
 * Spring JdbcTemplate to communicate with MySQL.
 */
@Repository
public class UserDataService {

    /**
     * Spring JDBC template used to execute SQL statements.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor-based dependency injection.
     *
     * Spring automatically provides the configured
     * JdbcTemplate instance.
     *
     * @param jdbcTemplate Spring JDBC template
     */
    public UserDataService(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    /**
    * Creates a Soldier record in the database.
    *
    * The Soldier's assigned unit is provided by the
    * Create Soldier form and stored with the Soldier record.
    *
    * @param soldierModel Soldier information
    * @return true when exactly one row is inserted
    */
    public boolean createUser(UserModel userModel) {

        String sql = """
                INSERT INTO users
                    (first_name,
                     last_name,
                     email,
                     phone_number,
                     username,
                     password)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        int rowsAffected = jdbcTemplate.update(
                sql,
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getEmail(),
                userModel.getPhoneNumber(),
                userModel.getUsername(),
                userModel.getPassword());

        return rowsAffected == 1;
    }

    /**
     * Determines whether a username already exists.
     *
     * @param username username to search for
     * @return true when the username already exists
     */
    public boolean usernameExists(String username) {

        String sql = """
                SELECT COUNT(*)
                FROM users
                WHERE username = ?
                """;

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                username);

        return count != null && count > 0;
    }

    /**
     * Authenticates a user against records stored in
     * the users table.
     *
     * @param username submitted username
     * @param password submitted password
     * @return true when matching credentials are found
     */
    public boolean authenticate(
            String username,
            String password) {

        String sql = """
                SELECT user_id
                FROM users
                WHERE username = ?
                  AND password = ?
                """;

        List<Long> users = jdbcTemplate.query(
                sql,
                (resultSet, rowNumber) -> resultSet.getLong("user_id"),
                username,
                password);

        return !users.isEmpty();
    }
}