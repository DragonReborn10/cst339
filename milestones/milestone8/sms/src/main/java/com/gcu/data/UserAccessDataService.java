package com.gcu.data;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/** Reads administrator-assigned roles and Soldier/formation scopes. */
@Repository
public class UserAccessDataService {
    private final JdbcTemplate jdbc;
    public UserAccessDataService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    /** Access assignment; null scopes grant no additional permissions. */
    public record Access(String role, String unit, String soldierId, Long formationId) {}

    /** Returns null when the username has no assigned permissions. */
    public Access find(String username) {
        List<Access> rows = jdbc.query("""
            SELECT a.role_name, a.unit, a.soldier_id, a.formation_id
            FROM user_access a JOIN users u ON u.username = a.username
            WHERE u.username = ?
            """, (rs, i) -> new Access(rs.getString("role_name"),
                rs.getString("unit"), rs.getString("soldier_id"),
                rs.getObject("formation_id", Long.class)), username);
        return rows.isEmpty() ? null : rows.get(0);
    }
}
