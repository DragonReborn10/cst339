package com.gcu.service;

import java.util.List;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gcu.data.DeleteRequestDataService;
import com.gcu.data.UserAccessDataService;
import com.gcu.model.SoldierModel;

/** Manages justified delete requests and administrator-only approval decisions. */
@Service
public class DeleteRequestService {
    private final DeleteRequestDataService requests;
    private final UserAccessDataService users;
    private final AccessService access;
    private final SoldierService soldiers;

    public DeleteRequestService(DeleteRequestDataService requests, UserAccessDataService users,
                                AccessService access, SoldierService soldiers) {
        this.requests = requests;
        this.users = users;
        this.access = access;
        this.soldiers = soldiers;
    }

    /** Returns whether the authenticated user is an administrator. */
    public boolean isAdmin(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) return false;
        var assigned = users.find(auth.getName());
        return assigned != null && "ADMIN".equals(assigned.role());
    }

    /** Allows a non-admin to request deletion only for a Soldier they can view. */
    public void submit(Authentication auth, String soldierId, String reason) {
        if (auth == null || !auth.isAuthenticated() || isAdmin(auth))
            throw new AccessDeniedException("Only non-admin users may submit a delete request");
        SoldierModel soldier = soldiers.getSoldierById(soldierId);
        if (soldier == null || !access.canView(auth, soldier))
            throw new AccessDeniedException("Soldier outside your assigned scope");
        if (reason == null || reason.trim().length() < 10 || reason.trim().length() > 2000)
            throw new IllegalArgumentException("A justification of 10 to 2000 characters is required");
        requests.create(soldierId, auth.getName(), reason.trim());
    }

    /** Returns the pending requests visible only to administrators. */
    public List<DeleteRequestDataService.Request> pending(Authentication auth) {
        requireAdmin(auth);
        return requests.pending();
    }

    /** Returns the admin notification badge count. */
    public int pendingCount(Authentication auth) {
        return isAdmin(auth) ? requests.pendingCount() : 0;
    }

    /** Returns a requester's own history. */
    public List<DeleteRequestDataService.Request> mine(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) throw new AccessDeniedException("Login required");
        return requests.byRequester(auth.getName());
    }

    /** Approves and deletes atomically, or rejects without deleting. */
    @Transactional
    public void decide(Authentication auth, long id, boolean approve, String comment) {
        requireAdmin(auth);
        if (comment != null && comment.length() > 2000) throw new IllegalArgumentException("Comment too long");
        var request = requests.lockPending(id);
        if (request == null) throw new IllegalArgumentException("Request not pending or not found");
        if (approve) {
            // If the Soldier was already deleted, do not mark this request approved.
            if (!soldiers.deleteSoldier(request.soldierId()))
                throw new IllegalStateException("Soldier no longer exists; request was not approved");
        }
        requests.decide(id, approve ? "APPROVED" : "REJECTED", auth.getName(),
            comment == null ? "" : comment.trim());
    }

    private void requireAdmin(Authentication auth) {
        if (!isAdmin(auth)) throw new AccessDeniedException("Administrator approval required");
    }
}
