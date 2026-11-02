package com.gcu.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.SoldierModel;
import com.gcu.service.AccessService;
import com.gcu.service.SoldierService;

/**
 * Milestone 7 read-only REST APIs. Soldier records are the products
 * for this course project. All /api/** requests require HTTP Basic
 * authentication against the existing MySQL users table.
 */
@RestController
@RequestMapping("/api/soldiers")
public class SoldierRestController {
    private final SoldierService soldierService;
    private final AccessService accessService;

    /** @param soldierService existing Soldier business service */
    public SoldierRestController(SoldierService soldierService, AccessService accessService) {
        this.soldierService = soldierService;
        this.accessService = accessService;
    }

    /** @return HTTP 200 and all Soldiers as JSON; an empty list if none exist */
    @GetMapping
    public ResponseEntity<List<SoldierModel>> getAllSoldiers(Authentication authentication) {
        if (!accessService.canViewFormation(authentication)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(accessService.visible(authentication, soldierService.getAllSoldiers()));
    }

    /**
     * @param soldierId exact String Soldier ID (for example 0000000001)
     * @return HTTP 200 with JSON Soldier, or HTTP 404 if not found
     */
    @GetMapping("/{soldierId}")
    public ResponseEntity<SoldierModel> getSoldierById(@PathVariable String soldierId, Authentication authentication) {
        SoldierModel soldier = soldierService.getSoldierById(soldierId);
        if (soldier == null) return ResponseEntity.notFound().build();
        if (!accessService.canView(authentication, soldier)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(soldier);
    }
}
