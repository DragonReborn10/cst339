package com.gcu.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gcu.data.UserDataService;
import com.gcu.model.UserModel;

/**
 * Loads application users from MySQL for Spring Security.
 *
 * Implements the Spring Security UserDetailsService
 * interface for database-backed authentication.
 */
@Service
public class DatabaseUserDetailsService
        implements UserDetailsService {

    private final UserDataService userDataService;

    /**
     * Constructor-based dependency injection.
     *
     * @param userDataService user database repository
     */
    public DatabaseUserDetailsService(
            UserDataService userDataService) {

        this.userDataService = userDataService;
    }

    /**
     * Retrieves a user by username for authentication.
     *
     * Spring Security uses the returned UserDetails
     * object to verify the submitted password.
     *
     * @param username submitted username
     * @return Spring Security UserDetails
     * @throws UsernameNotFoundException if user is missing
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // Retrieve the user from the MySQL database.
        UserModel user = userDataService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(
                    "User not found: " + username);
        }

        /*
         * Convert the application's UserModel into
         * Spring Security's UserDetails representation.
         *
         * The password is expected to be a BCrypt hash
         * once the registration migration is complete.
         */
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}