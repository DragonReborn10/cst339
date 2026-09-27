package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the main application page.
 */
@Controller
public class HomeController {

    /**
     * Displays the home page at the application's root URI.
     *
     * @param model Spring MVC model
     * @return home view
     */
    @GetMapping("/")
    public String displayHome(Model model) {

        model.addAttribute(
                "title",
                "Soldier Management System");

        return "home";
    }
}