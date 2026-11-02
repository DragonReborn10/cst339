package com.gcu.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.data.UserAccessDataService;
import com.gcu.model.SoldierModel;
import com.gcu.service.AccessService;
import com.gcu.service.DeleteRequestService;
import com.gcu.service.SoldierService;

import jakarta.validation.Valid;

/**
 * Handles Soldier creation, viewing, editing, and deletion.
 * Enforces role-based and formation-based permissions.
 */
@Controller
@RequestMapping("/soldiers")
public class SoldierController {

    private final SoldierService soldierService;
    private final AccessService accessService;
    private final UserAccessDataService userAccessDataService;
    private final DeleteRequestService deleteRequestService;

    /**
     * Creates the Soldier controller.
     *
     * @param soldierService Soldier business service
     * @param accessService authorization service
     * @param deleteRequestService deletion approval service
     * @param userAccessDataService user permission data service
     */
    public SoldierController(
            SoldierService soldierService,
            AccessService accessService,
            DeleteRequestService deleteRequestService,
            UserAccessDataService userAccessDataService) {

        this.soldierService = soldierService;
        this.accessService = accessService;
        this.deleteRequestService = deleteRequestService;
        this.userAccessDataService = userAccessDataService;
    }

    /**
     * Displays the Soldier assigned to the logged-in account.
     */
    @GetMapping("/my-data")
    public String myData(Model model, Authentication authentication) {

        var assignment = userAccessDataService.find(
                authentication.getName());

        if (assignment == null
                || assignment.soldierId() == null
                || assignment.soldierId().isBlank()) {

            throw new AccessDeniedException(
                    "No Soldier record linked to your account");
        }

        SoldierModel soldier = soldierService.getSoldierById(
                assignment.soldierId());

        if (soldier == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Assigned Soldier not found");
        }

        model.addAttribute("title", "My Soldier Data");
        model.addAttribute("soldier", soldier);

        return "my-soldier-data";
    }

    /**
     * Displays Soldiers within the user's authorized formation.
     */
    @GetMapping
    public String displaySoldiers(
            @RequestParam(name = "search", required = false)
            String search,
            Model model,
            Authentication authentication) {

        if (!accessService.canViewFormation(authentication)) {
            throw new AccessDeniedException(
                    "Formation access required");
        }

        List<SoldierModel> soldiers = accessService.visible(
                authentication,
                soldierService.searchSoldiers(search));

        model.addAttribute("title", "View Soldiers");
        model.addAttribute("soldiers", soldiers);
        model.addAttribute("search",
                search == null ? "" : search);
        model.addAttribute("canCreate",
                accessService.canCreate(authentication));
        model.addAttribute("isAdmin",
                deleteRequestService.isAdmin(authentication));
        model.addAttribute("canEditSoldier", accessService);

        return "soldiers";
    }

    /**
     * Displays the Create Soldier form.
     * Staff and commanders receive their assigned formation.
     */
    @GetMapping("/create")
    public String displayCreateSoldier(
            Model model,
            Authentication authentication) {

        if (!accessService.canCreate(authentication)) {
            throw new AccessDeniedException(
                    "Create permission required");
        }

        SoldierModel soldier = new SoldierModel();

        applyAssignedScope(authentication, soldier);

        model.addAttribute("title", "Create Soldier");
        model.addAttribute("soldierModel", soldier);

        addCreateFormAttributes(model, authentication);

        return "create-soldier";
    }

    /**
     * Validates and creates a Soldier.
     *
     * Non-admin users cannot choose a different formation
     * or unit. Their assignments are set on the server.
     */
    @PostMapping("/create")
    public String createSoldier(
            @Valid @ModelAttribute("soldierModel")
            SoldierModel soldierModel,
            BindingResult bindingResult,
            Model model,
            Authentication authentication) {

        if (!accessService.canCreate(authentication)) {
            throw new AccessDeniedException(
                    "Create permission required");
        }

        model.addAttribute("title", "Create Soldier");

        // IMPORTANT:
        // Assign the authenticated user's formation and unit
        // before validating or checking creation permissions.
        applyAssignedScope(authentication, soldierModel);

        addCreateFormAttributes(model, authentication);

        if (bindingResult.hasErrors()) {
            return "create-soldier";
        }

        // Keep the authorization check.
        accessService.requireCreate(
                authentication, soldierModel);

        boolean created = soldierService.createSoldier(
                soldierModel);

        if (created) {

            model.addAttribute(
                    "successMessage",
                    "Soldier created successfully.");

            SoldierModel newSoldier = new SoldierModel();

            applyAssignedScope(authentication, newSoldier);

            model.addAttribute("soldierModel", newSoldier);

        } else {

            model.addAttribute(
                    "errorMessage",
                    "Soldier could not be created. "
                    + "Soldier ID may already exist.");
        }

        return "create-soldier";
    }

    /**
     * Applies the logged-in user's assigned formation
     * and unit when they are not an administrator.
     *
     * Admins may select any formation and unit.
     */
    private void applyAssignedScope(
            Authentication authentication,
            SoldierModel soldier) {

        var assignment = userAccessDataService.find(
                authentication.getName());

        if (assignment == null) {
            throw new AccessDeniedException(
                    "No access assignment found");
        }

        if ("ADMIN".equals(assignment.role())) {
            return;
        }

        if (assignment.formationId() == null
                || assignment.unit() == null
                || assignment.unit().isBlank()) {

            throw new AccessDeniedException(
                    "Your account must have an assigned "
                    + "formation and unit");
        }

        soldier.setFormationId(
                assignment.formationId());

        soldier.setUnit(
                assignment.unit());
    }

    /**
     * Adds information needed by the Create Soldier form.
     */
    private void addCreateFormAttributes(
            Model model,
            Authentication authentication) {

        var assignment = userAccessDataService.find(
                authentication.getName());

        boolean isAdmin = assignment != null
                && "ADMIN".equals(assignment.role());

        model.addAttribute("isAdmin", isAdmin);
    }

    /**
     * Displays the Edit Soldier form.
     */
    @GetMapping("/edit/{soldierId}")
    public String displayEditSoldier(
            @PathVariable String soldierId,
            Model model,
            RedirectAttributes redirectAttributes,
            Authentication authentication) {

        SoldierModel soldier = soldierService.getSoldierById(
                soldierId);

        if (soldier == null) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Soldier not found.");

            return "redirect:/soldiers";
        }

        if (!accessService.canEdit(
                authentication, soldier)) {

            throw new AccessDeniedException(
                    "Soldier not in permitted scope");
        }

        model.addAttribute("title", "Edit Soldier");
        model.addAttribute("soldierModel", soldier);

        return "edit-soldier";
    }

    /**
     * Updates an existing Soldier without allowing
     * non-admin users to move it to another formation.
     */
    @PostMapping("/edit/{soldierId}")
    public String updateSoldier(
            @PathVariable String soldierId,
            @Valid @ModelAttribute("soldierModel")
            SoldierModel soldierModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            Authentication authentication) {

        soldierModel.setSoldierId(soldierId);

        model.addAttribute("title", "Edit Soldier");

        SoldierModel stored = soldierService.getSoldierById(
                soldierId);

        if (stored != null) {
            soldierModel.setFormationId(
                    stored.getFormationId());
        }

        accessService.requireUpdate(
                authentication, stored, soldierModel);

        if (bindingResult.hasErrors()) {
            return "edit-soldier";
        }

        boolean updated = soldierService.updateSoldier(
                soldierModel);

        if (!updated) {

            model.addAttribute(
                    "errorMessage",
                    "Soldier could not be updated. "
                    + "The record may no longer exist.");

            return "edit-soldier";
        }

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Soldier updated successfully.");

        return "redirect:/soldiers";
    }

    /**
     * Deletes a Soldier.
     * Direct deletion is restricted to administrators.
     */
    @PostMapping("/delete/{soldierId}")
    public String deleteSoldier(
            @PathVariable String soldierId,
            RedirectAttributes redirectAttributes,
            Authentication authentication) {

        SoldierModel soldier = soldierService.getSoldierById(
                soldierId);

        accessService.requireDelete(
                authentication, soldier);

        boolean deleted = soldierService.deleteSoldier(
                soldierId);

        if (deleted) {

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Soldier deleted successfully.");

        } else {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Soldier could not be deleted. "
                    + "The record may no longer exist.");
        }

        return "redirect:/soldiers";
    }
}