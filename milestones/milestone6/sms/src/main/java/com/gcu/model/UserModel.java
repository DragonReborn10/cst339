package com.gcu.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Represents a registered Soldier Management System user.
 */
public class UserModel {

    @NotBlank(message = "First Name is required.")
    @Size(min = 2, max = 30, message = "First Name must be between 2 and 30 characters.")
    private String firstName;

    @NotBlank(message = "Last Name is required.")
    @Size(min = 2, max = 30, message = "Last Name must be between 2 and 30 characters.")
    private String lastName;

    @NotBlank(message = "Email Address is required.")
    @Email(message = "Enter a valid Email Address.")
    private String email;

    @NotBlank(message = "Phone Number is required.")
    @Pattern(regexp = "^\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$", message = "Enter a valid 10-digit Phone Number.")
    private String phoneNumber;

    @NotBlank(message = "User Name is required.")
    @Size(min = 4, max = 32, message = "User Name must be between 4 and 32 characters.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 32, message = "Password must be between 6 and 32 characters.")
    private String password;

    /**
     * Default constructor.
     */
    public UserModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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