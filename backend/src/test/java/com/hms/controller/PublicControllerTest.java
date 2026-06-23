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

}