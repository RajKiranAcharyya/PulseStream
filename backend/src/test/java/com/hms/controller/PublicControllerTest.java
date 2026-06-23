package com.hms.controller;

import com.hms.dto.ContactMessageDTO;
import com.hms.dto.PatientRegistrationDTO;
import com.hms.entity.ContactMessage;
import com.hms.entity.Patient;
import com.hms.service.ContactService;
import com.hms.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PublicController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class PublicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private ContactService contactService;

    @Test
    public void testRegister_Success() throws Exception {
        PatientRegistrationDTO dto = new PatientRegistrationDTO("John", "Doe", "Male", "john@gmail.com", "123", "pass");
        when(patientService.getPatientByEmail("john@gmail.com")).thenReturn(Optional.empty());

        Patient patient = new Patient();
        patient.setEmail("john@gmail.com");
        when(patientService.registerPatient(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/api/public/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fname\":\"John\",\"lname\":\"Doe\",\"gender\":\"Male\",\"email\":\"john@gmail.com\",\"contact\":\"123\",\"password\":\"pass\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegister_EmailExists() throws Exception {
        when(patientService.getPatientByEmail("john@gmail.com")).thenReturn(Optional.of(new Patient()));

        mockMvc.perform(post("/api/public/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fname\":\"John\",\"lname\":\"Doe\",\"gender\":\"Male\",\"email\":\"john@gmail.com\",\"contact\":\"123\",\"password\":\"pass\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already registered"));
    }

}