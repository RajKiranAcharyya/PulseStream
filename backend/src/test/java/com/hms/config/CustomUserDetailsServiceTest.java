package com.hms.config;

import com.hms.entity.Admin;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.repository.AdminRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomUserDetailsServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @AfterEach
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void testLoadUserByUsername_AdminSuccess() {
        when(request.getParameter("loginRole")).thenReturn("ADMIN");
        Admin admin = new Admin("admin1", "pass", "admin1@gmail.com");
        when(adminRepository.findById("admin1")).thenReturn(Optional.of(admin));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("admin1");
        assertNotNull(userDetails);
        assertEquals("admin1", userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    public void testLoadUserByUsername_AdminNotFound() {
        when(request.getParameter("loginRole")).thenReturn("ADMIN");
        when(adminRepository.findById("admin1")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("admin1");
        });
    }

    @Test
    public void testLoadUserByUsername_DoctorSuccess() {
        when(request.getParameter("loginRole")).thenReturn("DOCTOR");
        Doctor doctor = new Doctor("doc@gmail.com", false, "dr_smith", "pass", "Cardio", 500);
        when(doctorRepository.findById("doc@gmail.com")).thenReturn(Optional.of(doctor));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("doc@gmail.com");
        assertNotNull(userDetails);
        assertEquals("doc@gmail.com", userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR")));
    }

    @Test
    public void testLoadUserByUsername_PatientSuccess() {
        when(request.getParameter("loginRole")).thenReturn("PATIENT");
        Patient patient = new Patient(1L, false, "John", "Doe", "Male", "patient@gmail.com", "123", "pass");
        when(patientRepository.findByEmail("patient@gmail.com")).thenReturn(Optional.of(patient));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("patient@gmail.com");
        assertNotNull(userDetails);
        assertEquals("patient@gmail.com", userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PATIENT")));
    }

    @Test
    public void testLoadUserByUsername_AllTablesSuccess() {
        when(request.getParameter("loginRole")).thenReturn(null);
        Patient patient = new Patient(1L, false, "John", "Doe", "Male", "patient@gmail.com", "123", "pass");
        when(adminRepository.findById("patient@gmail.com")).thenReturn(Optional.empty());
        when(doctorRepository.findById("patient@gmail.com")).thenReturn(Optional.empty());
        when(doctorRepository.findByUsername("patient@gmail.com")).thenReturn(Optional.empty());
        when(patientRepository.findByEmail("patient@gmail.com")).thenReturn(Optional.of(patient));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("patient@gmail.com");
        assertNotNull(userDetails);
        assertEquals("patient@gmail.com", userDetails.getUsername());
    }

}