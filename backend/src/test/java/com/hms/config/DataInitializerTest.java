package com.hms.config;

import com.hms.entity.Admin;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.repository.AdminRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class DataInitializerTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DataInitializer dataInitializer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRun_NoAdminExists() throws Exception {
        when(adminRepository.count()).thenReturn(0L);
        when(passwordEncoder.encode("admin123")).thenReturn("encodedAdmin123");
        when(doctorRepository.findAll()).thenReturn(Collections.emptyList());
        when(patientRepository.findAll()).thenReturn(Collections.emptyList());

        dataInitializer.run();

        verify(adminRepository, times(1)).save(any(Admin.class));
    }

    @Test
    public void testRun_AdminExistsPlaintextPassword() throws Exception {
        when(adminRepository.count()).thenReturn(1L);
        Admin admin = new Admin("admin", "plain12", "admin@hms.com");
        when(adminRepository.findAll()).thenReturn(Arrays.asList(admin));
        when(passwordEncoder.encode("plain12")).thenReturn("encodedPassword60Chars");
        when(doctorRepository.findAll()).thenReturn(Collections.emptyList());
        when(patientRepository.findAll()).thenReturn(Collections.emptyList());

        dataInitializer.run();

        verify(adminRepository, times(1)).save(admin);
    }

}