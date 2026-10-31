package com.gcu.sms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gcu.service.SoldierService;

/** HTTP endpoint tests; the authenticated database integration is tested manually. */
@SpringBootTest
@AutoConfigureMockMvc
class SoldierRestControllerTest {
    @Autowired MockMvc mockMvc;
    @MockitoBean SoldierService soldierService;

    @Test void anonymousListIsUnauthorized() throws Exception {
        mockMvc.perform(get("/api/soldiers")).andExpect(status().isUnauthorized());
    }

    @Test void anonymousDetailIsUnauthorized() throws Exception {
        mockMvc.perform(get("/api/soldiers/TEST001")).andExpect(status().isUnauthorized());
    }

    @Test void invalidBasicCredentialsAreUnauthorized() throws Exception {
        mockMvc.perform(get("/api/soldiers").with(httpBasic("invalid", "invalid")))
            .andExpect(status().isUnauthorized());
    }
}
