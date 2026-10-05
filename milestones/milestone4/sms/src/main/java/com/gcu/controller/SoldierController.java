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
 * Controller responsible for Soldier-related operations.
 */
@Controller
@RequestMapping("/soldiers")
public class SoldierController {

    private final SoldierService soldierService;

    /**
     * Constructor-based dependency injection allows Spring
     * to provide the Soldier business service.
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
     * The user must be logged in before accessing the page.
     *
     * @param model Spring MVC model
     * @param session HTTP session
     * @return Create Soldier page or redirect to login
     */
    @GetMapping("/create")
    public String displayCreateSoldier(
            Model model,
            HttpSession session) {

        /*
         * Temporary session-based authorization check.
         * Spring Security will replace this in a later milestone.
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
     * Valid Soldier information is passed to the business
     * service and persisted to MySQL through the data layer.
     *
     * @param soldierModel submitted Soldier information
     * @param bindingResult validation results
     * @param model Spring MVC model
     * @param session HTTP session
     * @return Create Soldier page or redirect to login
     */
    @PostMapping("/create")
    public String createSoldier(
            @Valid SoldierModel soldierModel,
            BindingResult bindingResult,
            Model model,
            HttpSession session) {

        /*
         * Prevent users without a valid application session
         * from submitting Soldier records.
         */
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "title",
                "Create Soldier");

        /*
         * Return the form when Jakarta Bean Validation
         * identifies invalid fields.
         */
        if (bindingResult.hasErrors()) {
            return "create-soldier";
        }

        /*
         * Attempt to persist the Soldier through the
         * SoldierService and SoldierDataService.
         */
        boolean created =
                soldierService.createSoldier(soldierModel);

        if (created) {

            model.addAttribute(
                    "successMessage",
                    "Soldier created successfully.");

            /*
             * Clear the form following successful creation.
             */
            model.addAttribute(
                    "soldierModel",
                    new SoldierModel());

        } else {

            /*
             * The most common failure at this point is an
             * existing Soldier ID.
             */
            model.addAttribute(
                    "errorMessage",
                    "Soldier could not be created. Soldier ID may already exist.");
        }

        return "create-soldier";
    }
}