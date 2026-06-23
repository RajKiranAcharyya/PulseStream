package com.hms.controller;

import com.hms.dto.AppointmentBookingDTO;
import com.hms.dto.PatientProfileDTO;
import com.hms.entity.Appointment;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.service.AppointmentService;
import com.hms.service.AvailabilityService;
import com.hms.service.DoctorService;
import com.hms.service.PatientService;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PatientController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private PrescriptionService prescriptionService;

    @MockBean
    private PatientService patientService;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private AvailabilityService availabilityService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testGetAppointments_Found() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("patient@gmail.com");
        Patient patient = new Patient();
        patient.setPid(1L);
        when(patientService.getPatientByEmail("patient@gmail.com")).thenReturn(Optional.of(patient));
        when(appointmentService.getPatientAppointments(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/patient/appointments").principal(auth))
                .andExpect(status().isOk());
    }

}