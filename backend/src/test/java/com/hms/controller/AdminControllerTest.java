package com.hms.controller;

import com.hms.dto.DoctorRegistrationDTO;
import com.hms.entity.Doctor;
import com.hms.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AdminController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private PrescriptionService prescriptionService;

    @MockBean
    private ContactService contactService;

    @Test
    public void testGetPatients() throws Exception {
        when(patientService.getAllPatients()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/admin/patients"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testRemovePatient() throws Exception {
        doNothing().when(patientService).deletePatient(1L);

        mockMvc.perform(delete("/api/admin/remove-patient/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Patient removed"));
    }

    @Test
    public void testGetDoctors() throws Exception {
        when(doctorService.getAllDoctors()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/admin/doctors"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testAddDoctor_Success() throws Exception {
        DoctorRegistrationDTO dto = new DoctorRegistrationDTO("dr_smith", "smith@gmail.com", "Cardiology", 500, "pass123");
        when(doctorService.getDoctorByEmail(dto.getEmail())).thenReturn(Optional.empty());
        when(doctorService.getDoctorByUsername(dto.getUsername())).thenReturn(Optional.empty());
        
        Doctor doctor = new Doctor();
        doctor.setUsername(dto.getUsername());
        doctor.setEmail(dto.getEmail());
        doctor.setSpec(dto.getSpec());
        doctor.setDocFees(dto.getDocFees());
        doctor.setPassword(dto.getPassword());
        when(doctorService.addDoctor(any(Doctor.class))).thenReturn(doctor);

        mockMvc.perform(post("/api/admin/add-doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"dr_smith\",\"email\":\"smith@gmail.com\",\"spec\":\"Cardiology\",\"docFees\":500,\"password\":\"pass123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddDoctor_DuplicateEmail() throws Exception {
        when(doctorService.getDoctorByEmail("smith@gmail.com")).thenReturn(Optional.of(new Doctor()));

        mockMvc.perform(post("/api/admin/add-doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"dr_smith\",\"email\":\"smith@gmail.com\",\"spec\":\"Cardiology\",\"docFees\":500,\"password\":\"pass123\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Doctor with this email already exists"));
    }

    @Test
    public void testAddDoctor_DuplicateUsername() throws Exception {
        when(doctorService.getDoctorByEmail("smith@gmail.com")).thenReturn(Optional.empty());
        when(doctorService.getDoctorByUsername("dr_smith")).thenReturn(Optional.of(new Doctor()));

        mockMvc.perform(post("/api/admin/add-doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"dr_smith\",\"email\":\"smith@gmail.com\",\"spec\":\"Cardiology\",\"docFees\":500,\"password\":\"pass123\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Doctor with this username already exists"));
    }

    @Test
    public void testRemoveDoctor() throws Exception {
        doNothing().when(doctorService).removeDoctor("smith@gmail.com");

        mockMvc.perform(delete("/api/admin/remove-doctor/smith@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("Doctor removed"));
    }

    @Test
    public void testGetAppointments() throws Exception {
        when(appointmentService.getAllAppointments()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/admin/appointments"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

}