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

    @Test
    public void testGetAllPatients() {
        Patient p1 = new Patient(1L, false, "John", "Doe", "Male", "john@gmail.com", "12345", "pass");
        Patient p2 = new Patient(2L, false, "Jane", "Doe", "Female", "jane@gmail.com", "67890", "pass");
        when(patientRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Patient> result = patientService.getAllPatients();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetPatientByEmail_Found() {
        Patient p = new Patient(1L, false, "John", "Doe", "Male", "john@gmail.com", "12345", "pass");
        when(patientRepository.findByEmail("john@gmail.com")).thenReturn(Optional.of(p));

        Optional<Patient> result = patientService.getPatientByEmail("john@gmail.com");
        assertTrue(result.isPresent());
        assertEquals("john@gmail.com", result.get().getEmail());
    }

    @Test
    public void testGetPatientByEmail_NotFound() {
        when(patientRepository.findByEmail("none@gmail.com")).thenReturn(Optional.empty());

        Optional<Patient> result = patientService.getPatientByEmail("none@gmail.com");
        assertTrue(result.isEmpty());
    }

}