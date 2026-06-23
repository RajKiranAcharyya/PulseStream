package com.hms.service;

import com.hms.entity.Doctor;
import com.hms.repository.DoctorRepository;
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

public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DoctorService doctorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDoctor() {
        Doctor doctor = new Doctor("doc@gmail.com", false, "dr_smith", "rawPass", "Cardiology", 500);
        when(passwordEncoder.encode("rawPass")).thenReturn("encodedPass");
        
        Doctor savedDoctor = new Doctor("doc@gmail.com", false, "dr_smith", "encodedPass", "Cardiology", 500);
        when(doctorRepository.save(any(Doctor.class))).thenReturn(savedDoctor);

        Doctor result = doctorService.addDoctor(doctor);
        assertNotNull(result);
        assertEquals("encodedPass", result.getPassword());
        verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    public void testGetAllDoctors() {
        Doctor doc1 = new Doctor("doc1@gmail.com", false, "doc1", "pass", "A", 100);
        Doctor doc2 = new Doctor("doc2@gmail.com", false, "doc2", "pass", "B", 200);
        when(doctorRepository.findAll()).thenReturn(Arrays.asList(doc1, doc2));

        List<Doctor> result = doctorService.getAllDoctors();
        assertEquals(2, result.size());
    }

    @Test
    public void testRemoveDoctor() {
        doNothing().when(doctorRepository).deleteById("doc@gmail.com");
        doctorService.removeDoctor("doc@gmail.com");
        verify(doctorRepository, times(1)).deleteById("doc@gmail.com");
    }

    @Test
    public void testGetDoctorByEmail_Found() {
        Doctor doc = new Doctor("doc@gmail.com", false, "doc1", "pass", "A", 100);
        when(doctorRepository.findById("doc@gmail.com")).thenReturn(Optional.of(doc));

        Optional<Doctor> result = doctorService.getDoctorByEmail("doc@gmail.com");
        assertTrue(result.isPresent());
        assertEquals("doc@gmail.com", result.get().getEmail());
    }

    @Test
    public void testGetDoctorByEmail_NotFound() {
        when(doctorRepository.findById("nonexistent@gmail.com")).thenReturn(Optional.empty());

        Optional<Doctor> result = doctorService.getDoctorByEmail("nonexistent@gmail.com");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetDoctorByUsername_Found() {
        Doctor doc = new Doctor("doc@gmail.com", false, "dr_jones", "pass", "A", 100);
        when(doctorRepository.findByUsername("dr_jones")).thenReturn(Optional.of(doc));

        Optional<Doctor> result = doctorService.getDoctorByUsername("dr_jones");
        assertTrue(result.isPresent());
        assertEquals("dr_jones", result.get().getUsername());
    }

}