package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

/**
 * Controller for the Soldier Management System dashboard.
 */
@Controller
public class DashboardController {

    /**
     * Displays the dashboard.
     *
     * @param model   Spring MVC model
     * @param session current HTTP session
     * @return dashboard view
     */
    @GetMapping("/dashboard")
    public String displayDashboard(
            Model model,
            HttpSession session) {

        model.addAttribute(
                "title",
                "Dashboard");

        return "dashboard";
    }

    /**
     * Logs the user out of the simulated session.
     *
     * @param session current HTTP session
     * @return redirect to home
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
}
