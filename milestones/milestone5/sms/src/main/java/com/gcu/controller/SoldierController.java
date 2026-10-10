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

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Spring MVC controller for Soldier CRUD and search operations.
 */
@Controller
@RequestMapping("/soldiers")
public class SoldierController {

    private final SoldierService soldierService;

    /**
     * Injects the Soldier business service.
     *
     * @param soldierService Soldier service
     */
    public SoldierController(SoldierService soldierService) {
        this.soldierService = soldierService;
    }

    /**
     * Checks the temporary Milestone 4 login session.
     *
     * @param session HTTP session
     * @return true when logged in
     */
    private boolean isLoggedIn(HttpSession session) {
        return Boolean.TRUE.equals(session.getAttribute("loggedIn"));
    }

    /**
     * Displays Soldiers and optionally filters by search text.
     *
     * @param search optional ID or name search
     * @param model MVC model
     * @param session HTTP session
     * @return Soldier list or login redirect
     */
    @GetMapping
    public String displaySoldiers(
            @RequestParam(name = "search", required = false)
            String search,
            Model model,
            HttpSession session) {

        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        List<SoldierModel> soldiers =
                soldierService.searchSoldiers(search);

        model.addAttribute("title", "View Soldiers");
        model.addAttribute("soldiers", soldiers);
        model.addAttribute("search", search == null ? "" : search);

        return "soldiers";
    }

    /**
     * Displays the Create Soldier form.
     *
     * @param model MVC model
     * @param session HTTP session
     * @return Create Soldier view
     */
    @GetMapping("/create")
    public String displayCreateSoldier(
            Model model,
            HttpSession session) {

        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("title", "Create Soldier");
        model.addAttribute("soldierModel", new SoldierModel());

        return "create-soldier";
    }

    /**
     * Validates and creates a Soldier.
     *
     * @param soldierModel submitted Soldier
     * @param bindingResult validation errors
     * @param model MVC model
     * @param session HTTP session
     * @return Create Soldier view or login redirect
     */
    @PostMapping("/create")
    public String createSoldier(
            @Valid @ModelAttribute("soldierModel")
            SoldierModel soldierModel,
            BindingResult bindingResult,
            Model model,
            HttpSession session) {

        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("title", "Create Soldier");

        if (bindingResult.hasErrors()) {
            return "create-soldier";
        }

        boolean created = soldierService.createSoldier(soldierModel);

        if (created) {
            model.addAttribute(
                    "successMessage",
                    "Soldier created successfully.");

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
     * @param model MVC model
     * @param session HTTP session
     * @param redirectAttributes redirect messages
     * @return Edit view or redirect
     */
    @GetMapping("/edit/{soldierId}")
    public String displayEditSoldier(
            @PathVariable String soldierId,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        SoldierModel soldier = soldierService.getSoldierById(soldierId);

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
     * The Soldier ID is taken from the URL, not trusted
     * from submitted form data.
     *
     * @param soldierId exact Soldier ID
     * @param soldierModel submitted changes
     * @param bindingResult validation errors
     * @param model MVC model
     * @param session HTTP session
     * @param redirectAttributes redirect messages
     * @return Edit form or Soldier list
     */
    @PostMapping("/edit/{soldierId}")
    public String updateSoldier(
            @PathVariable String soldierId,
            @Valid @ModelAttribute("soldierModel")
            SoldierModel soldierModel,
            BindingResult bindingResult,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        // Keep the database primary key fixed.
        soldierModel.setSoldierId(soldierId);

        model.addAttribute("title", "Edit Soldier");

        if (bindingResult.hasErrors()) {
            return "edit-soldier";
        }

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
     * Deletes a Soldier using an exact ID.
     *
     * Delete uses POST to avoid changing data through a GET link.
     *
     * @param soldierId exact Soldier ID
     * @param session HTTP session
     * @param redirectAttributes redirect messages
     * @return Soldier list or login redirect
     */
    @PostMapping("/delete/{soldierId}")
    public String deleteSoldier(
            @PathVariable String soldierId,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

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