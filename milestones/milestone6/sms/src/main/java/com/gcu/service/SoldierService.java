package com.gcu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.data.SoldierDataService;
import com.gcu.model.SoldierModel;

/**
 * Business service for Soldier CRUD and search operations.
 */
@Service
public class SoldierService {

    private final SoldierDataService soldierDataService;

    /**
     * Injects the Soldier data access service.
     *
     * @param soldierDataService Soldier DAO
     */
    public SoldierService(SoldierDataService soldierDataService) {
        this.soldierDataService = soldierDataService;
    }

    /**
     * Creates a Soldier when the ID does not already exist.
     *
     * @param soldierModel Soldier to create
     * @return true when created
     */
    public boolean createSoldier(SoldierModel soldierModel) {
        if (soldierModel == null
                || soldierModel.getSoldierId() == null
                || soldierModel.getSoldierId().isBlank()) {
            return false;
        }

        if (soldierDataService.soldierExists(soldierModel.getSoldierId())) {
            return false;
        }

        return soldierDataService.createSoldier(soldierModel);
    }

    /**
     * Retrieves every Soldier.
     *
     * @return all Soldier records
     */
    public List<SoldierModel> getAllSoldiers() {
        return soldierDataService.getAllSoldiers();
    }

    /**
     * Searches by full or partial ID, first name, last name,
     * or first and last name together.
     *
     * @param searchTerm user-entered search text
     * @return matching Soldier records
     */
    public List<SoldierModel> searchSoldiers(String searchTerm) {
        return soldierDataService.searchSoldiers(searchTerm);
    }

    /**
     * Finds one Soldier using an exact ID.
     *
     * @param soldierId exact Soldier ID
     * @return Soldier or null if not found
     */
    public SoldierModel getSoldierById(String soldierId) {
        if (soldierId == null || soldierId.isBlank()) {
            return null;
        }

        return soldierDataService.getSoldierById(soldierId);
    }

    /**
     * Updates an existing Soldier.
     *
     * @param soldierModel validated Soldier data
     * @return true when updated
     */
    public boolean updateSoldier(SoldierModel soldierModel) {
        if (soldierModel == null
                || soldierModel.getSoldierId() == null
                || soldierModel.getSoldierId().isBlank()) {
            return false;
        }

        return soldierDataService.updateSoldier(soldierModel);
    }

    /**
     * Deletes a Soldier using the exact ID.
     *
     * @param soldierId exact Soldier ID
     * @return true when deleted
     */
    public boolean deleteSoldier(String soldierId) {
        if (soldierId == null || soldierId.isBlank()) {
            return false;
        }

        return soldierDataService.deleteSoldier(soldierId);
    }
}