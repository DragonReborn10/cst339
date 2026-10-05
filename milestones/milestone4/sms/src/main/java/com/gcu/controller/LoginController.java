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
 * the application login page.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationService authenticationService;

    /**
     * Constructor-based dependency injection allows Spring
     * to provide the authentication business service.
     *
     * @param authenticationService authentication service
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
     * The AuthenticationService verifies the submitted
     * credentials against the users stored in MySQL.
     *
     * @param loginModel submitted login information
     * @param bindingResult validation results
     * @param model Spring MVC model
     * @param session HTTP session
     * @return login page or redirect to dashboard
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
         * Authenticate the submitted credentials through
         * the business service and data access layer.
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
         * Store basic login information in the session.
         * Spring Security will replace this temporary
         * session-based access control in a later milestone.
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