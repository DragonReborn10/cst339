package com.gcu.service;

import org.springframework.stereotype.Service;

import com.gcu.model.SoldierModel;

/**
 * Business service responsible for soldier operations.
 */
@Service
public class SoldierService {

    /**
     * Processes creation of a soldier.
     *
     * Soldier information is not persisted during
     * Milestone 3.
     *
     * @param soldierModel soldier information
     * @return true when the soldier is accepted
     */
    public boolean createSoldier(
            SoldierModel soldierModel) {

        /*
         * Database persistence will be implemented
         * during Milestone 4.
         */
        return soldierModel != null;
    }
}