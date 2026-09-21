package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.LoginModel;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Controller responsible for simulated login.
 *
 * Database authentication and Spring Security
 * will be implemented during later milestones.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

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
     * Processes login credentials.
     *
     * @param loginModel    submitted credentials
     * @param bindingResult validation results
     * @param model         Spring MVC model
     * @param session       current session
     * @return dashboard or login
     */
    @PostMapping
    public String doLogin(
            @Valid LoginModel loginModel,
            BindingResult bindingResult,
            Model model,
            HttpSession session) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "title",
                    "Login");

            return "login";
        }

        /*
         * Milestone 2 only requires simulated
         * authentication.
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