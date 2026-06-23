package com.hms.service;

import com.hms.entity.Patient;
import com.hms.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterPatient() {
        Patient patient = new Patient(null, false, "John", "Doe", "Male", "john@gmail.com", "12345", "rawPass");
        when(passwordEncoder.encode("rawPass")).thenReturn("encodedPass");

        Patient savedPatient = new Patient(1L, false, "John", "Doe", "Male", "john@gmail.com", "12345", "encodedPass");
        when(patientRepository.save(any(Patient.class))).thenReturn(savedPatient);

        Patient result = patientService.registerPatient(patient);
        assertNotNull(result);
        assertEquals(1L, result.getPid());
        assertEquals("encodedPass", result.getPassword());
        verify(patientRepository, times(1)).save(patient);
    }

}