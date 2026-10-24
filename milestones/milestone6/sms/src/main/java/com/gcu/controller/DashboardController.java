
package com.gcu.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for the authenticated
 * Soldier Management System dashboard.
 *
 * Access control is enforced by Spring Security.
 */
@Controller
public class DashboardController {

    /**
     * Displays the dashboard for an authenticated user.
     *
     * Spring Security automatically redirects
     * unauthenticated requests to /login.
     *
     * @param model Spring MVC model
     * @param principal currently authenticated user
     * @return dashboard template name
     */
    @GetMapping("/dashboard")
    public String displayDashboard(
            Model model,
            Principal principal) {

        // Set the dashboard page title.
        model.addAttribute("title", "Dashboard");

        // Display the authenticated username if needed.
        model.addAttribute(
                "username",
                principal.getName());

        return "dashboard";
    }
}