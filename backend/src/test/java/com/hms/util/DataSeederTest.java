package com.hms.util;

import com.hms.entity.Availability;
import com.hms.entity.Doctor;
import com.hms.repository.AvailabilityRepository;
import com.hms.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class DataSeederTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private AvailabilityRepository availabilityRepository;

    @InjectMocks
    private DataSeeder dataSeeder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}