package com.hms.controller;

import com.hms.dto.ContactMessageDTO;
import com.hms.dto.PatientRegistrationDTO;
import com.hms.entity.ContactMessage;
import com.hms.entity.Patient;
import com.hms.service.ContactService;
import com.hms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private ContactService contactService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PatientRegistrationDTO dto) {
        if (patientService.getPatientByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        
        // MAPPING: DTO -> Entity
        Patient patient = new Patient();
        patient.setFname(dto.getFname());
        patient.setLname(dto.getLname());
        patient.setGender(dto.getGender());
        patient.setEmail(dto.getEmail());
        patient.setContact(dto.getContact());
        patient.setPassword(dto.getPassword());
        
        return ResponseEntity.ok(patientService.registerPatient(patient));
    }

    @PostMapping("/contact")
    public ResponseEntity<?> contact(@RequestBody ContactMessageDTO dto) {
        // MAPPING: DTO -> Entity
        ContactMessage message = new ContactMessage();
        message.setName(dto.getName());
        message.setEmail(dto.getEmail());
        message.setContact(dto.getContact());
        message.setMessage(dto.getMessage());
        
        return ResponseEntity.ok(contactService.saveMessage(message));
    }
}
