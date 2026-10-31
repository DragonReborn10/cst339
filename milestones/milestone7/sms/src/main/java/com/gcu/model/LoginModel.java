package com.gcu.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Represents credentials entered on the login page.
 */
public class LoginModel {

    @NotBlank(message = "User Name is required.")
    @Size(min = 4, max = 32, message = "User Name must be between 4 and 32 characters.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 32, message = "Password must be between 6 and 32 characters.")
    private String password;

    /**
     * Default constructor.
     */
    public LoginModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}