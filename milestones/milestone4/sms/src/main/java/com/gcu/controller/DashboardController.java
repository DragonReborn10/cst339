package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

/**
 * Controller responsible for Dashboard operations.
 */
@Controller
public class DashboardController {


    /**
     * Displays the Dashboard for a logged-in user.
     *
     * @param model Spring MVC model
     * @param session current HTTP session
     * @return Dashboard or Login page
     */
    @GetMapping("/dashboard")
    public String displayDashboard(
            Model model,
            HttpSession session) {

        /*
         * Redirect users to Login when they do not
         * have an active login session.
         */
        if (session.getAttribute("loggedIn") == null) {

            return "redirect:/login";
        }

        model.addAttribute(
                "title",
                "Dashboard");

        return "dashboard";
    }


    /**
     * Logs the current user out of the application.
     *
     * @param session current HTTP session
     * @return Home page
     */
    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
}