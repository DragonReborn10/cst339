
package com.gcu.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.data.UserDataService;
import com.gcu.model.UserModel;

/**
 * Business service responsible for registering
 * application users.
 *
 * Validates registration requests, checks for duplicate
 * usernames, and securely hashes passwords using BCrypt
 * before storing them in the MySQL database.
 */
@Service
public class RegistrationService {

    private final UserDataService userDataService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor-based dependency injection.
     *
     * @param userDataService database access service for users
     * @param passwordEncoder Spring Security password encoder
     */
    public RegistrationService(
            UserDataService userDataService,
            PasswordEncoder passwordEncoder) {

        this.userDataService = userDataService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new application user.
     *
     * Rejects null requests and duplicate usernames.
     * Hashes the password before saving the user.
     *
     * @param userModel user registration information
     * @return true if registration succeeds
     */
    public boolean registerUser(UserModel userModel) {

        // Reject invalid registration requests.
        if (userModel == null) {
            return false;
        }

        // Reject blank usernames or passwords.
        if (userModel.getUsername() == null
                || userModel.getUsername().isBlank()
                || userModel.getPassword() == null
                || userModel.getPassword().isBlank()) {

            return false;
        }

        // Prevent duplicate usernames.
        if (userDataService.usernameExists(
                userModel.getUsername())) {

            return false;
        }

        /*
         * Convert the plaintext password into a
         * secure BCrypt password hash.
         */
        String hashedPassword = passwordEncoder.encode(
                userModel.getPassword());

        /*
         * Store the hash instead of the original password.
         */
        userModel.setPassword(hashedPassword);

        // Save the registered user in MySQL.
        return userDataService.createUser(userModel);
    }
}
