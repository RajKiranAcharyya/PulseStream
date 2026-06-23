package com.hms.service;

import com.hms.entity.Admin;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.entity.PasswordResetToken;
import com.hms.repository.AdminRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import com.hms.repository.PasswordResetTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private PasswordResetTokenRepository tokenRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateResetToken_EmailNotFound() {
        String email = "notfound@hms.com";
        when(adminRepository.existsByEmail(email)).thenReturn(false);
        when(doctorRepository.existsByEmail(email)).thenReturn(false);
        when(patientRepository.existsByEmail(email)).thenReturn(false);

        String result = authService.createResetToken(email);
        assertEquals("Email not found", result);
    }

    @Test
    public void testCreateResetToken_SuccessForPatient() {
        String email = "patient@gmail.com";
        when(adminRepository.existsByEmail(email)).thenReturn(false);
        when(doctorRepository.existsByEmail(email)).thenReturn(false);
        when(patientRepository.existsByEmail(email)).thenReturn(true);
        doNothing().when(tokenRepository).deleteByEmail(email);

        String result = authService.createResetToken(email);
        assertEquals("Success", result);
        verify(tokenRepository, times(1)).deleteByEmail(email);
        verify(tokenRepository, times(1)).save(any(PasswordResetToken.class));
        verify(emailService, times(1)).sendEmail(eq(email), anyString(), anyString());
    }

    @Test
    public void testValidateToken_Success() {
        PasswordResetToken token = new PasswordResetToken("token-123", "user@hms.com", 10);
        when(tokenRepository.findByToken("token-123")).thenReturn(Optional.of(token));

        Optional<String> result = authService.validateToken("token-123");
        assertTrue(result.isPresent());
        assertEquals("user@hms.com", result.get());
    }

    @Test
    public void testValidateToken_Expired() {
        PasswordResetToken token = new PasswordResetToken("token-123", "user@hms.com", -5);
        when(tokenRepository.findByToken("token-123")).thenReturn(Optional.of(token));

        Optional<String> result = authService.validateToken("token-123");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testResetPassword_InvalidToken() {
        when(tokenRepository.findByToken("invalid-token")).thenReturn(Optional.empty());
        boolean result = authService.resetPassword("invalid-token", "newPass");
        assertFalse(result);
    }

}