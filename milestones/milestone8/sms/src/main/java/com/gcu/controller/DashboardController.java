package com.gcu.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.gcu.service.AccessService;
import com.gcu.service.DeleteRequestService;

/** Renders role-appropriate dashboard actions and admin delete notifications. */
@Controller
public class DashboardController {
    private final AccessService access;
    private final DeleteRequestService requests;
    public DashboardController(AccessService access, DeleteRequestService requests) {
        this.access = access;
        this.requests = requests;
    }
    /** Shows only permitted navigation actions for the logged-in user. */
    @GetMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication) {
        model.addAttribute("title", "Dashboard");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("canCreateSoldier", access.canCreate(authentication));
        model.addAttribute("canViewFormation", access.canViewFormation(authentication));
        model.addAttribute("isAdmin", requests.isAdmin(authentication));
        model.addAttribute("pendingDeleteRequests", requests.pendingCount(authentication));
        return "dashboard";
    }
}