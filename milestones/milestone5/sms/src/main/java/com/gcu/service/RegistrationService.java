package com.gcu.service;

import org.springframework.stereotype.Service;

import com.gcu.data.UserDataService;
import com.gcu.model.UserModel;

/**
 * Business service responsible for registering application users.
 */
@Service
public class RegistrationService {

    private final UserDataService userDataService;

    /**
     * Constructor-based dependency injection.
     *
     * @param userDataService data access service for users
     */
    public RegistrationService(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    /**
     * Registers a new user.
     *
     * Duplicate usernames are rejected before attempting
     * to insert the user into the database.
     *
     * @param userModel registration information
     * @return true if registration succeeds
     */
    public boolean registerUser(UserModel userModel) {

        if (userModel == null) {
            return false;
        }

        if (userDataService.usernameExists(
                userModel.getUsername())) {

            return false;
        }

        return userDataService.createUser(userModel);
    }
}