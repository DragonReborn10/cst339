
package com.gcu.sms;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for Spring Security access rules.
 *
 * These tests verify public routes, authentication
 * requirements, and CSRF protection.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Verifies that the Login page is public.
     */
    @Test
    void loginPageIsPublic() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    /**
     * Verifies that the Registration page is public.
     */
    @Test
    void registrationPageIsPublic() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk());
    }

    /**
     * Verifies that unauthenticated users are redirected
     * to the Login page when requesting the Dashboard.
     */
    @Test
    void dashboardRequiresAuthentication() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    /**
     * Verifies that unauthenticated users cannot access
     * the Soldier list.
     */
    @Test
    void soldiersRequireAuthentication() throws Exception {
        mockMvc.perform(get("/soldiers"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    /**
     * Verifies that authenticated users can access
     * the Dashboard.
     */
    @Test
    void authenticatedUserCanAccessDashboard() throws Exception {
        mockMvc.perform(get("/dashboard")
                .with(user("testuser").roles("USER")))
                .andExpect(status().isOk());
    }

    /**
     * Verifies that Spring Security rejects POST requests
     * without a CSRF token.
     */
    @Test
    void postWithoutCsrfIsForbidden() throws Exception {
        mockMvc.perform(post("/logout")
                .with(user("testuser").roles("USER")))
                .andExpect(status().isForbidden());
    }

    /**
     * Verifies that an authenticated user can log out
     * when a valid CSRF token is provided.
     */
    @Test
    void logoutWithCsrfSucceeds() throws Exception {
        mockMvc.perform(post("/logout")
                .with(user("testuser").roles("USER"))
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login?logout"));
    }
}
