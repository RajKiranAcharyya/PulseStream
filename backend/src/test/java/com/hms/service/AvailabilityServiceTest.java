package com.hms.service;

import com.hms.entity.Availability;
import com.hms.repository.AvailabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AvailabilityServiceTest {

    @Mock
    private AvailabilityRepository availabilityRepository;

    @InjectMocks
    private AvailabilityService availabilityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDoctorAvailability() {
        Availability av1 = new Availability(1L, "doc@gmail.com", "Monday", LocalTime.of(9, 0), LocalTime.of(12, 0), false);
        when(availabilityRepository.findByDoctorEmail("doc@gmail.com")).thenReturn(Arrays.asList(av1));

        List<Availability> result = availabilityService.getDoctorAvailability("doc@gmail.com");
        assertEquals(1, result.size());
        assertEquals("doc@gmail.com", result.get(0).getDoctorEmail());
    }

}