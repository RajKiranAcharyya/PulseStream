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

}