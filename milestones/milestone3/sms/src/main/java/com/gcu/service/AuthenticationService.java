package com.gcu.service;

import org.springframework.stereotype.Service;

import com.gcu.model.LoginModel;

/**
 * Business service responsible for authenticating users.
 *
 * Authentication is simulated for Milestone 3 because
 * database persistence is not required until a later milestone.
 */
@Service 
public class AuthenticationService {

    /**
     * Simulates authentication of a user.
     *
     * @param loginModel submitted login credentials
     * @return true when the supplied credentials are accepted
     */
    public boolean authenticate(LoginModel loginModel) {

        /*
         * Authentication is intentionally simulated
         * during Milestone 3.
         */
        return loginModel != null
                && loginModel.getUsername() != null
                && loginModel.getPassword() != null;
    }
}