package com.hms.controller;

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
    public ResponseEntity<?> register(@RequestBody Patient patient) {
        if (patientService.getPatientByEmail(patient.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        return ResponseEntity.ok(patientService.registerPatient(patient));
    }

    @PostMapping("/contact")
    public ResponseEntity<?> contact(@RequestBody ContactMessage message) {
        return ResponseEntity.ok(contactService.saveMessage(message));
    }
}
