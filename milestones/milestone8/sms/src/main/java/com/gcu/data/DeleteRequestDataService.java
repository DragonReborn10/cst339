package com.gcu.data;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/** Persistence for auditable Soldier deletion requests and admin notifications. */
@Repository
public class DeleteRequestDataService {
    private final JdbcTemplate jdbc;
    public DeleteRequestDataService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    /** A request retains the reason, submitter, decision and timestamps. */
    public record Request(long id, String soldierId, String requestedBy, String reason,
                          String status, String reviewedBy, String reviewComment,
                          String createdAt, String reviewedAt) {}

    private final org.springframework.jdbc.core.RowMapper<Request> mapper = (rs, i) -> new Request(
        rs.getLong("id"), rs.getString("soldier_id"), rs.getString("requested_by"),
        rs.getString("reason"), rs.getString("status"), rs.getString("reviewed_by"),
        rs.getString("review_comment"), rs.getString("created_at"), rs.getString("reviewed_at"));

    /** Inserts a pending request; the Soldier is not deleted here. */
    public void create(String soldierId, String username, String reason) {
        jdbc.update("INSERT INTO soldier_delete_requests (soldier_id, requested_by, reason, status) VALUES (?, ?, ?, 'PENDING')",
            soldierId, username, reason);
    }

    /** Returns requests requiring admin attention, newest first. */
    public List<Request> pending() {
        return jdbc.query("SELECT * FROM soldier_delete_requests WHERE status = 'PENDING' ORDER BY created_at DESC, id DESC", mapper);
    }

    /** Returns all requests submitted by one user. */
    public List<Request> byRequester(String username) {
        return jdbc.query("SELECT * FROM soldier_delete_requests WHERE requested_by = ? ORDER BY id DESC", mapper, username);
    }

    /** Returns the pending notification count. */
    public int pendingCount() {
        return jdbc.queryForObject("SELECT COUNT(*) FROM soldier_delete_requests WHERE status = 'PENDING'", Integer.class);
    }

    /** Locks the request row during a decision to prevent duplicate processing. */
    public Request lockPending(long id) {
        List<Request> rows = jdbc.query("SELECT * FROM soldier_delete_requests WHERE id = ? AND status = 'PENDING' FOR UPDATE", mapper, id);
        return rows.isEmpty() ? null : rows.get(0);
    }

    /** Records a decision without deleting or altering the audit trail. */
    public void decide(long id, String status, String admin, String comment) {
        jdbc.update("UPDATE soldier_delete_requests SET status = ?, reviewed_by = ?, review_comment = ?, reviewed_at = CURRENT_TIMESTAMP WHERE id = ? AND status = 'PENDING'",
            status, admin, comment, id);
    }
}
