package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.LoginModel;
import com.gcu.service.AuthenticationService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Controller responsible for displaying and processing
 * the login page.
 *
 * Authentication is handled by AuthenticationService,
 * which is injected into this controller by Spring.
 *
 * Database authentication and Spring Security will be
 * implemented during later milestones.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * Business service used to authenticate users.
     */
    private final AuthenticationService authenticationService;

    /**
     * Constructor-based dependency injection.
     *
     * Spring automatically injects the AuthenticationService
     * bean when the controller is created.
     *
     * @param authenticationService authentication business service
     */
    public LoginController( 
            AuthenticationService authenticationService) {

        this.authenticationService = authenticationService;
    }

    /**
     * Displays the login page.
     *
     * @param model Spring MVC model
     * @return login view
     */
    @GetMapping
    public String displayLogin(Model model) {

        model.addAttribute(
                "title",
                "Login");

        model.addAttribute(
                "loginModel",
                new LoginModel());

        return "login";
    }

    /**
     * Processes submitted login credentials.
     *
     * @param loginModel submitted credentials
     * @param bindingResult validation results
     * @param model Spring MVC model
     * @param session current HTTP session
     * @return dashboard redirect or login view
     */
    @PostMapping
    public String doLogin(
            @Valid LoginModel loginModel,
            BindingResult bindingResult,
            Model model,
            HttpSession session) {

        /*
         * Return to the login page when validation fails.
         */
        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "title",
                    "Login");

            return "login";
        }

        /*
         * Authentication is delegated to the injected
         * AuthenticationService.
         */
        if (!authenticationService.authenticate(loginModel)) {

            model.addAttribute(
                    "title",
                    "Login");

            model.addAttribute(
                    "loginError",
                    "Invalid username or password.");

            return "login";
        }

        /*
         * Store the simulated authenticated state
         * in the HTTP session.
         */
        session.setAttribute(
                "loggedIn",
                true);

        session.setAttribute(
                "username",
                loginModel.getUsername());

        return "redirect:/dashboard";
    }
}