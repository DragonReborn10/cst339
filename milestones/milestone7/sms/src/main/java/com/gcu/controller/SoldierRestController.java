package com.gcu.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.SoldierModel;
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

    /** @param soldierService existing Soldier business service */
    public SoldierRestController(SoldierService soldierService) {
        this.soldierService = soldierService;
    }

    /** @return HTTP 200 and all Soldiers as JSON; an empty list if none exist */
    @GetMapping
    public ResponseEntity<List<SoldierModel>> getAllSoldiers() {
        return ResponseEntity.ok(soldierService.getAllSoldiers());
    }

    /**
     * @param soldierId exact String Soldier ID (for example TEST001)
     * @return HTTP 200 with JSON Soldier, or HTTP 404 if not found
     */
    @GetMapping("/{soldierId}")
    public ResponseEntity<SoldierModel> getSoldierById(@PathVariable String soldierId) {
        SoldierModel soldier = soldierService.getSoldierById(soldierId);
        return soldier == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(soldier);
    }
}
