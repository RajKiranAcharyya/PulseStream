package com.hms.controller;

import com.hms.dto.AvailabilityDTO;
import com.hms.dto.DoctorProfileDTO;
import com.hms.dto.PrescriptionDTO;
import com.hms.entity.Availability;
import com.hms.entity.Doctor;
import com.hms.entity.Prescription;
import com.hms.entity.Appointment;
import com.hms.service.AppointmentService;
import com.hms.service.AvailabilityService;
import com.hms.service.DoctorService;
import com.hms.service.PrescriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DoctorController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private PrescriptionService prescriptionService;

    @MockBean
    private AvailabilityService availabilityService;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testGetAppointments() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("doc@gmail.com");
        when(doctorService.getDoctorByEmail("doc@gmail.com")).thenReturn(Optional.empty());
        when(appointmentService.getDoctorAppointments("doc@gmail.com")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/doctor/appointments").principal(auth))
                .andExpect(status().isOk());
    }

    @Test
    public void testCancelAppointment() throws Exception {
        doNothing().when(appointmentService).cancelByDoctor(1L);

        mockMvc.perform(post("/api/doctor/cancel/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cancelled"));
    }

}