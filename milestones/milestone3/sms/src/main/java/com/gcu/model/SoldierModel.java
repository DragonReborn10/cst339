package com.gcu.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Represents a soldier managed by the
 * Soldier Management System.
 */
public class SoldierModel {

    @NotBlank(message = "Soldier ID is required.")
    @Size(max = 20, message = "Soldier ID cannot exceed 20 characters.")
    private String soldierId;

    @NotBlank(message = "First Name is required.")
    @Size(min = 2, max = 30,
            message = "First Name must be between 2 and 30 characters.")
    private String firstName;

    @NotBlank(message = "Last Name is required.")
    @Size(min = 2, max = 30,
            message = "Last Name must be between 2 and 30 characters.")
    private String lastName;

    @NotBlank(message = "Rank is required.")
    private String rank;

    @NotBlank(message = "Unit is required.")
    private String unit;

    @NotBlank(message = "MOS is required.")
    @Size(max = 10,
            message = "MOS cannot exceed 10 characters.")
    private String mos;

    @NotBlank(message = "Duty Status is required.")
    private String dutyStatus;

    @NotBlank(message = "Email Address is required.")
    @Email(message = "Enter a valid Email Address.")
    private String email;

    @NotBlank(message = "Phone Number is required.")
    @Pattern(
            regexp = "^\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$",
            message = "Enter a valid 10-digit Phone Number.")
    private String phoneNumber;

    /**
     * Default constructor.
     */
    public SoldierModel() {
    }

    public String getSoldierId() {
        return soldierId;
    }

    public void setSoldierId(String soldierId) {
        this.soldierId = soldierId;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMos() {
        return mos;
    }

    public void setMos(String mos) {
        this.mos = mos;
    }

    public String getDutyStatus() {
        return dutyStatus;
    }

    public void setDutyStatus(String dutyStatus) {
        this.dutyStatus = dutyStatus;
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
}