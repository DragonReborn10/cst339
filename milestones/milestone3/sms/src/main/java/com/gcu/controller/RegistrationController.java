package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.LoginModel;
import com.gcu.model.UserModel;
import com.gcu.service.RegistrationService;

import jakarta.validation.Valid;

/**
 * Controller responsible for user registration.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    /**
     * Constructor injection allows Spring to provide
     * the registration business service.
     *
     * @param registrationService registration service
     */
    public RegistrationController(
            RegistrationService registrationService) {

        this.registrationService = registrationService;
    }

    /**
     * Displays the registration page.
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
     * Processes submitted registration information.
     *
     * @param userModel registration information
     * @param bindingResult validation results
     * @param model Spring MVC model
     * @return registration or login view
     */
    @PostMapping
    public String registerUser(
            @Valid UserModel userModel,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "title",
                    "Register");

            return "register";
        }

        boolean registered =
                registrationService.registerUser(userModel);

        if (!registered) {

            model.addAttribute(
                    "registrationError",
                    "Registration could not be completed.");

            return "register";
        }

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