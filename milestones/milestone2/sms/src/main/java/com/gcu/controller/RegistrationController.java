package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.LoginModel;
import com.gcu.model.UserModel;

import jakarta.validation.Valid;

/**
 * Controller responsible for user registration.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    /**
     * Displays the registration form.
     *
     * @param model Spring MVC model
     * @return registration view
     */
    @GetMapping
    public String displayRegistration(Model model) {

        model.addAttribute(
                "title",
                "Register");

        model.addAttribute(
                "userModel",
                new UserModel());

        return "register";
    }

    /**
     * Processes registration form data.
     *
     * Database persistence is intentionally not
     * implemented during Milestone 2.
     *
     * @param userModel     registration information
     * @param bindingResult validation results
     * @param model         Spring MVC model
     * @return register or login view
     */
    @PostMapping
    public String registerUser(
            @Valid UserModel userModel,
            BindingResult bindingResult,
            Model model) {

        /*
         * Return to the registration page when
         * validation errors are detected.
         */
        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "title",
                    "Register");

            return "register";
        }

        /*
         * No database is required yet.
         * Successful registration simply sends
         * the user to the login screen.
         */
        model.addAttribute(
                "registrationSuccess",
                "Registration successful. Please log in.");

        model.addAttribute(
                "loginModel",
                new LoginModel());

        model.addAttribute(
                "title",
                "Login");

        return "login";
    }
}