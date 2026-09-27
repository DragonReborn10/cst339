package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.SoldierModel;
import com.gcu.service.SoldierService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Controller responsible for Soldier creation operations.
 */
@Controller
@RequestMapping("/soldiers")
public class SoldierController {

    private final SoldierService soldierService;


    /**
     * Constructor injection allows Spring to provide
     * the Soldier business service.
     *
     * @param soldierService Soldier business service
     */
    public SoldierController(
            SoldierService soldierService) {

        this.soldierService = soldierService;
    }


    /**
     * Displays the Create Soldier page.
     *
     * @param model Spring MVC model
     * @param session current HTTP session
     * @return Create Soldier page or Login page
     */
    @GetMapping("/create")
    public String displayCreateSoldier(
            Model model,
            HttpSession session) {

        /*
         * Verify that the user logged in before allowing
         * access to Soldier management functionality.
         */
        if (session.getAttribute("loggedIn") == null) {

            return "redirect:/login";
        }

        model.addAttribute(
                "title",
                "Create Soldier");

        model.addAttribute(
                "soldierModel",
                new SoldierModel());

        return "create-soldier";
    }


    /**
     * Processes the Create Soldier form.
     *
     * @param soldierModel submitted Soldier information
     * @param bindingResult validation results
     * @param model Spring MVC model
     * @param session current HTTP session
     * @return Create Soldier page or Login page
     */
    @PostMapping("/create")
    public String createSoldier(
            @Valid SoldierModel soldierModel,
            BindingResult bindingResult,
            Model model,
            HttpSession session) {

        /*
         * Prevent unauthenticated users from submitting
         * Soldier information.
         */
        if (session.getAttribute("loggedIn") == null) {

            return "redirect:/login";
        }

        model.addAttribute(
                "title",
                "Create Soldier");


        /*
         * Return to the form when validation fails.
         */
        if (bindingResult.hasErrors()) {

            return "create-soldier";
        }


        /*
         * Send the validated Soldier to the
         * Soldier business service.
         */
        boolean created =
                soldierService.createSoldier(soldierModel);


        if (created) {

            model.addAttribute(
                    "successMessage",
                    "Soldier created successfully.");

            /*
             * Clear the form after successful creation.
             */
            model.addAttribute(
                    "soldierModel",
                    new SoldierModel());
        }


        return "create-soldier";
    }
}