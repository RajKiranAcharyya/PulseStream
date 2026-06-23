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

    @Test
    public void testGetAppointments_NotFound() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("patient@gmail.com");
        when(patientService.getPatientByEmail("patient@gmail.com")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/patient/appointments").principal(auth))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetDoctors() throws Exception {
        when(doctorService.getAllDoctors()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/patient/doctors"))
                .andExpect(status().isOk());
    }

    @Test
    public void testBookAppointment_PastDate() throws Exception {
        Authentication auth = mock(Authentication.class);
        LocalDate pastDate = LocalDate.now().minusDays(1);

        mockMvc.perform(post("/api/patient/book")
                .principal(auth)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"doctor\":\"Dr. Smith\",\"appdate\":\"" + pastDate + "\",\"apptime\":\"10:00:00\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Cannot book appointments for past dates."));
    }

    @Test
    public void testBookAppointment_Success() throws Exception {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("patient@gmail.com");
        LocalDate futureDate = LocalDate.now().plusDays(5);
        LocalTime time = LocalTime.of(10, 0);

        Patient patient = new Patient();
        patient.setPid(1L);
        when(patientService.getPatientByEmail("patient@gmail.com")).thenReturn(Optional.of(patient));

        Appointment app = new Appointment();
        when(appointmentService.bookAppointment(any(Appointment.class))).thenReturn(app);

        mockMvc.perform(post("/api/patient/book")
                .principal(auth)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"doctor\":\"Dr. Smith\",\"appdate\":\"" + futureDate + "\",\"apptime\":\"" + time + "\",\"disease\":\"Flu\",\"age\":25,\"docFees\":500}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCancelAppointment() throws Exception {
        doNothing().when(appointmentService).cancelByPatient(1L);

        mockMvc.perform(post("/api/patient/cancel/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cancelled"));
    }

}