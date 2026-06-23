package com.hms.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DashboardController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDashboard_NoAuthRedirectToIndex() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index.html"));
    }

    @Test
    public void testDashboard_AdminRedirect() throws Exception {
        Authentication auth = mock(Authentication.class);
        doReturn(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")))
                .when(auth).getAuthorities();

        mockMvc.perform(get("/dashboard").principal(auth))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin_dashboard.html"));
    }

    @Test
    public void testDashboard_DoctorRedirect() throws Exception {
        Authentication auth = mock(Authentication.class);
        doReturn(Collections.singletonList(new SimpleGrantedAuthority("ROLE_DOCTOR")))
                .when(auth).getAuthorities();

        mockMvc.perform(get("/dashboard").principal(auth))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/doctor_dashboard.html"));
    }

}