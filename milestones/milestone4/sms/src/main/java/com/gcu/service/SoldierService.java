package com.gcu.service;

import org.springframework.stereotype.Service;

import com.gcu.data.SoldierDataService;
import com.gcu.model.SoldierModel;

/**
 * Business service responsible for Soldier operations.
 */
@Service
public class SoldierService {

    private final SoldierDataService soldierDataService;

    /**
     * Constructor-based dependency injection.
     *
     * @param soldierDataService data access service for Soldiers
     */
    public SoldierService(SoldierDataService soldierDataService) {
        this.soldierDataService = soldierDataService;
    }

    /**
     * Creates a new Soldier.
     *
     * Duplicate Soldier IDs are rejected before attempting
     * to insert the Soldier into the database.
     *
     * @param soldierModel Soldier information
     * @return true if the Soldier is successfully created
     */
    public boolean createSoldier(SoldierModel soldierModel) {

        if (soldierModel == null) {
            return false;
        }

        if (soldierDataService.soldierExists(
                soldierModel.getSoldierId())) {

            return false;
        }

        return soldierDataService.createSoldier(soldierModel);
    }
}