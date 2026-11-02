package com.gcu.service;

import java.util.List;
import java.util.Objects;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.gcu.data.UserAccessDataService;
import com.gcu.model.SoldierModel;

/** Enforces administrator, formation, and individual Soldier permissions. */
@Service
public class AccessService {
    private final UserAccessDataService accessData;
    public AccessService(UserAccessDataService accessData) { this.accessData = accessData; }

    private UserAccessDataService.Access assigned(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) return null;
        return accessData.find(auth.getName());
    }
    private boolean staff(String role) {
        return "FULL_TIME_STAFF".equals(role) || "FIRST_SERGEANT".equals(role)
            || "COMMANDER".equals(role);
    }
    /** Whether the user may open the formation list. */
    public boolean canViewFormation(Authentication auth) {
        var a = assigned(auth);
        return a != null && ("ADMIN".equals(a.role()) ||
            (staff(a.role()) && a.formationId() != null));
    }
    /** Whether the user may create Soldier records. */
    public boolean canCreate(Authentication auth) { return canViewFormation(auth); }

    /** Filters a list by the authenticated user's permissions. */
    public List<SoldierModel> visible(Authentication auth, List<SoldierModel> soldiers) {
        return soldiers.stream().filter(s -> canView(auth, s)).toList();
    }
    /** Individual users see only their linked Soldier; staff see their formation. */
    public boolean canView(Authentication auth, SoldierModel soldier) {
        if (soldier == null) return false;
        var a = assigned(auth);
        if (a == null) return false;
        return switch (a.role()) {
            case "ADMIN" -> true;
            case "BASIC" -> a.soldierId() != null &&
                Objects.equals(a.soldierId(), soldier.getSoldierId());
            case "FULL_TIME_STAFF", "FIRST_SERGEANT", "COMMANDER" ->
                a.formationId() != null &&
                Objects.equals(a.formationId(), soldier.getFormationId());
            default -> false;
        };
    }
    /** Returns whether staff may edit an existing Soldier. */
    public boolean canEdit(Authentication auth, SoldierModel soldier) {
        var a = assigned(auth);
        return a != null && ("ADMIN".equals(a.role()) ||
            (staff(a.role()) && canView(auth, soldier)));
    }
    /** Checks new Soldier formation and unit. */
    public void requireCreate(Authentication auth, SoldierModel submitted) {
        var a = requireAccess(auth);
        if ("ADMIN".equals(a.role())) return;
        if (staff(a.role()) && a.formationId() != null &&
            Objects.equals(a.formationId(), submitted.getFormationId()) &&
            a.unit() != null && Objects.equals(a.unit(), submitted.getUnit())) return;
        throw new AccessDeniedException("Cannot create Soldier outside your formation/unit");
    }
    /** Prevents moving an existing record outside a staff member's scope. */
    public void requireUpdate(Authentication auth, SoldierModel existing, SoldierModel submitted) {
        var a = requireAccess(auth);
        if (existing == null) throw new AccessDeniedException("Soldier not found");
        if ("ADMIN".equals(a.role())) return;
        if (staff(a.role()) && canView(auth, existing) &&
            Objects.equals(existing.getFormationId(), submitted.getFormationId()) &&
            Objects.equals(existing.getUnit(), submitted.getUnit())) return;
        throw new AccessDeniedException("Cannot edit or move Soldier outside your scope");
    }
    /** Direct deletion is administrator-only; others submit requests. */
    public void requireDelete(Authentication auth, SoldierModel existing) {
        var a = requireAccess(auth);
        if (!"ADMIN".equals(a.role()) || existing == null)
            throw new AccessDeniedException("Administrator permission required");
    }
    private UserAccessDataService.Access requireAccess(Authentication auth) {
        var a = assigned(auth);
        if (a == null) throw new AccessDeniedException("No access assignment");
        return a;
    }
}