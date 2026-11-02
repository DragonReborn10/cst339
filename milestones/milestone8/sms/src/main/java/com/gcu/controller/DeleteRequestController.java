package com.gcu.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.gcu.service.DeleteRequestService;

/** Web workflow for justified delete requests and administrator decisions. */
@Controller
@RequestMapping("/delete-requests")
public class DeleteRequestController {
    private final DeleteRequestService service;
    public DeleteRequestController(DeleteRequestService service) { this.service = service; }

    /** Submits a justification for a Soldier visible to the requester. */
    @PostMapping("/soldier/{soldierId}")
    public String submit(@PathVariable String soldierId, @RequestParam String reason,
                         Authentication auth, RedirectAttributes flash) {
        service.submit(auth, soldierId, reason);
        flash.addFlashAttribute("successMessage", "Delete request submitted for administrator review.");
        return "redirect:/soldiers";
    }

    /** Displays the authenticated user's request history. */
    @GetMapping("/mine")
    public String mine(Authentication auth, Model model) {
        model.addAttribute("requests", service.mine(auth));
        return "delete-requests-mine";
    }

    /** Displays the administrator inbox and pending notification count. */
    @GetMapping("/admin")
    public String admin(Authentication auth, Model model) {
        model.addAttribute("requests", service.pending(auth));
        model.addAttribute("pendingCount", service.pendingCount(auth));
        return "delete-requests-admin";
    }

    /** Records an administrator's approval and deletes the Soldier in one transaction. */
    @PostMapping("/admin/{id}/approve")
    public String approve(@PathVariable long id, @RequestParam(required = false) String comment,
                          Authentication auth, RedirectAttributes flash) {
        service.decide(auth, id, true, comment);
        flash.addFlashAttribute("successMessage", "Request approved; Soldier deleted.");
        return "redirect:/delete-requests/admin";
    }

    /** Rejects a request without changing the Soldier record. */
    @PostMapping("/admin/{id}/reject")
    public String reject(@PathVariable long id, @RequestParam(required = false) String comment,
                         Authentication auth, RedirectAttributes flash) {
        service.decide(auth, id, false, comment);
        flash.addFlashAttribute("successMessage", "Delete request rejected; Soldier retained.");
        return "redirect:/delete-requests/admin";
    }
}
