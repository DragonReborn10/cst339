package com.gcu.service;

import org.springframework.stereotype.Service;

import com.gcu.model.UserModel;

/**
 * Business service responsible for user registration.
 *
 * Registration data is not persisted during Milestone 3.
 */
@Service
public class RegistrationService {

    /**
     * Processes a new user registration.
     *
     * @param userModel registration information
     * @return true when registration is accepted
     */
    public boolean registerUser(UserModel userModel) {

        /*
         * Database persistence will be added
         * during a later milestone.
         */
        return userModel != null;
    }
}