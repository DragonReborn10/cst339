package com.gcu.service;

import org.springframework.stereotype.Service;

import com.gcu.data.UserDataService;
import com.gcu.model.LoginModel;

/**
 * Business service responsible for authenticating users.
 *
 * Authentication is performed against the users table
 * through the UserDataService.
 */
@Service
public class AuthenticationService {

    private final UserDataService userDataService;

    /**
     * Constructor-based dependency injection.
     *
     * @param userDataService data access service for users
     */
    public AuthenticationService(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    /**
     * Authenticates submitted login credentials.
     *
     * @param loginModel submitted username and password
     * @return true if matching credentials exist
     */
    public boolean authenticate(LoginModel loginModel) {

        if (loginModel == null) {
            return false;
        }

        return userDataService.authenticate(
                loginModel.getUsername(),
                loginModel.getPassword());
    }
}