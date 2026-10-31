
package com.gcu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.model.SoldierModel;
import com.gcu.service.SoldierService;

import jakarta.validation.Valid;

/**
 * Spring MVC controller for Soldier CRUD and search operations.
 *
 * Spring Security protects all /soldiers endpoints through
 * the application's SecurityFilterChain configuration.
 */
@Controller
@RequestMapping("/soldiers")
public class SoldierController {

    private final SoldierService soldierService;

    /**
     * Injects the Soldier business service.
     *
     * @param soldierService Soldier business service
     */
    public SoldierController(SoldierService soldierService) {
        this.soldierService = soldierService;
    }

    /**
     * Displays all Soldiers or filters the list by search text.
     *
     * Authentication is enforced by Spring Security before
     * this controller method is executed.
     *
     * @param search optional Soldier ID or name search
     * @param model Spring MVC model
     * @return Soldier list template
     */
    @GetMapping
    public String displaySoldiers(
            @RequestParam(name = "search", required = false)
            String search,
            Model model) {

        // Retrieve all Soldiers or matching search results.
        List<SoldierModel> soldiers =
                soldierService.searchSoldiers(search);

        // Populate the Thymeleaf view.
        model.addAttribute("title", "View Soldiers");
        model.addAttribute("soldiers", soldiers);
        model.addAttribute("search", search == null ? "" : search);

        return "soldiers";
    }

    /**
     * Displays the Create Soldier form.
     *
     * @param model Spring MVC model
     * @return Create Soldier template
     */
    @GetMapping("/create")
    public String displayCreateSoldier(Model model) {

        // Initialize an empty Soldier model for the form.
        model.addAttribute("title", "Create Soldier");
        model.addAttribute("soldierModel", new SoldierModel());

        return "create-soldier";
    }

    /**
     * Validates and creates a new Soldier.
     *
     * @param soldierModel submitted Soldier information
     * @param bindingResult Bean Validation results
     * @param model Spring MVC model
     * @return Create Soldier template
     */
    @PostMapping("/create")
    public String createSoldier(
            @Valid @ModelAttribute("soldierModel")
            SoldierModel soldierModel,
            BindingResult bindingResult,
            Model model) {

        model.addAttribute("title", "Create Soldier");

        // Redisplay the form when validation fails.
        if (bindingResult.hasErrors()) {
            return "create-soldier";
        }

        // Save the validated Soldier through the service layer.
        boolean created = soldierService.createSoldier(soldierModel);

        if (created) {
            model.addAttribute(
                    "successMessage",
                    "Soldier created successfully.");

            // Clear the form after successful creation.
            model.addAttribute("soldierModel", new SoldierModel());
        } else {
            model.addAttribute(
                    "errorMessage",
                    "Soldier could not be created. Soldier ID may already exist.");
        }

        return "create-soldier";
    }

    /**
     * Loads an existing Soldier into the Edit form.
     *
     * @param soldierId exact Soldier ID
     * @param model Spring MVC model
     * @param redirectAttributes flash message attributes
     * @return Edit template or Soldier list redirect
     */
    @GetMapping("/edit/{soldierId}")
    public String displayEditSoldier(
            @PathVariable String soldierId,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Retrieve the existing Soldier record.
        SoldierModel soldier =
                soldierService.getSoldierById(soldierId);

        if (soldier == null) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Soldier not found.");

            return "redirect:/soldiers";
        }

        model.addAttribute("title", "Edit Soldier");
        model.addAttribute("soldierModel", soldier);

        return "edit-soldier";
    }

    /**
     * Validates and updates an existing Soldier.
     *
     * The Soldier ID is taken from the URL rather than
     * trusting the submitted form value.
     *
     * @param soldierId exact Soldier ID
     * @param soldierModel submitted Soldier information
     * @param bindingResult Bean Validation results
     * @param model Spring MVC model
     * @param redirectAttributes flash message attributes
     * @return Edit template or Soldier list redirect
     */
    @PostMapping("/edit/{soldierId}")
    public String updateSoldier(
            @PathVariable String soldierId,
            @Valid @ModelAttribute("soldierModel")
            SoldierModel soldierModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Preserve the original database primary key.
        soldierModel.setSoldierId(soldierId);

        model.addAttribute("title", "Edit Soldier");

        // Redisplay the form when validation fails.
        if (bindingResult.hasErrors()) {
            return "edit-soldier";
        }

        // Update the existing Soldier through the service layer.
        boolean updated = soldierService.updateSoldier(soldierModel);

        if (!updated) {
            model.addAttribute(
                    "errorMessage",
                    "Soldier could not be updated. The record may no longer exist.");

            return "edit-soldier";
        }

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Soldier updated successfully.");

        return "redirect:/soldiers";
    }

    /**
     * Deletes a Soldier using an exact Soldier ID.
     *
     * Uses POST rather than GET to prevent deletion
     * through ordinary navigation links.
     *
     * @param soldierId exact Soldier ID
     * @param redirectAttributes flash message attributes
     * @return Soldier list redirect
     */
    @PostMapping("/delete/{soldierId}")
    public String deleteSoldier(
            @PathVariable String soldierId,
            RedirectAttributes redirectAttributes) {

        // Delete the Soldier through the service layer.
        boolean deleted = soldierService.deleteSoldier(soldierId);

        if (deleted) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Soldier deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Soldier could not be deleted. The record may no longer exist.");
        }

        return "redirect:/soldiers";
    }
}