package com.hms.controller;

import com.hms.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    public void testForgotPassword_Success() throws Exception {
        when(authService.createResetToken("user@hms.com")).thenReturn("Success");

        mockMvc.perform(post("/api/auth/forgot-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"user@hms.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Reset link sent to your email."));
    }

    @Test
    public void testForgotPassword_NotFound() throws Exception {
        when(authService.createResetToken("user@hms.com")).thenReturn("Email not found");

        mockMvc.perform(post("/api/auth/forgot-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"user@hms.com\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email not found"));
    }

    @Test
    public void testResetPassword_Success() throws Exception {
        when(authService.resetPassword("token-123", "newPass")).thenReturn(true);

        mockMvc.perform(post("/api/auth/reset-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"token\":\"token-123\",\"password\":\"newPass\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Password updated successfully."));
    }

    @Test
    public void testResetPassword_Failure() throws Exception {
        when(authService.resetPassword("token-123", "newPass")).thenReturn(false);

        mockMvc.perform(post("/api/auth/reset-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"token\":\"token-123\",\"password\":\"newPass\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid or expired token."));
    }

    @Test
    public void testValidateToken_Success() throws Exception {
        when(authService.validateToken("token-123")).thenReturn(Optional.of("user@hms.com"));

        mockMvc.perform(get("/api/auth/validate-token")
                .param("token", "token-123"))
                .andExpect(status().isOk());
    }

}