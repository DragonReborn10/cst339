
package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller responsible for displaying the login page.
 *
 * Spring Security processes login submissions and
 * authenticates users against the MySQL database.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * Displays the custom login page.
     *
     * @param model Spring MVC model
     * @return login template name
     */
    @GetMapping
    public String displayLogin(Model model) {

        // Set the page title for Thymeleaf.
        model.addAttribute("title", "Login");

        return "login";
    }
}
