package com.gcu.data;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.model.UserModel;

/**
 * Data access service responsible for application users.
 *
 * Uses Spring JdbcTemplate to communicate with MySQL.
 */
@Repository
public class UserDataService {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor-based dependency injection.
     *
     * @param jdbcTemplate Spring JDBC template
     */
    public UserDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a new user in the database.
     *
     * The password must be BCrypt-encoded by the
     * RegistrationService before calling this method.
     *
     * @param userModel user registration information
     * @return true when one row is inserted
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
     * Checks whether a username already exists.
     *
     * @param username username to check
     * @return true if the username exists
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
     * Retrieves a registered user by username.
     *
     * This method is used by Spring Security to load
     * authentication information from MySQL.
     *
     * @param username username to search for
     * @return matching user or null if not found
     */
    public UserModel findByUsername(String username) {

        String sql = """
                SELECT first_name,
                       last_name,
                       email,
                       phone_number,
                       username,
                       password
                FROM users
                WHERE username = ?
                """;

        List<UserModel> users = jdbcTemplate.query(
                sql,
                (resultSet, rowNumber) -> {

                    UserModel user = new UserModel();

                    user.setFirstName(
                            resultSet.getString("first_name"));

                    user.setLastName(
                            resultSet.getString("last_name"));

                    user.setEmail(
                            resultSet.getString("email"));

                    user.setPhoneNumber(
                            resultSet.getString("phone_number"));

                    user.setUsername(
                            resultSet.getString("username"));

                    user.setPassword(
                            resultSet.getString("password"));

                    return user;
                },
                username);

        return users.isEmpty() ? null : users.get(0);
    }
}