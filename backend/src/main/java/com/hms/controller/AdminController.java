package com.hms.controller;

import com.hms.dto.DoctorRegistrationDTO;
import com.hms.entity.Doctor;
import com.hms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private PatientService patientService;
    
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private PrescriptionService prescriptionService;
    
    @Autowired
    private ContactService contactService;

    @GetMapping("/patients")
    public ResponseEntity<?> getPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @DeleteMapping("/remove-patient/{id}")
    public ResponseEntity<?> removePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient removed");
    }

    @GetMapping("/doctors")
    public ResponseEntity<?> getDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PostMapping("/add-doctor")
    public ResponseEntity<?> addDoctor(@RequestBody DoctorRegistrationDTO dto) {
        if (doctorService.getDoctorByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Doctor with this email already exists");
        }
        if (doctorService.getDoctorByUsername(dto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Doctor with this username already exists");
        }
        
        // MAPPING: DTO -> Entity
        Doctor doctor = new Doctor();
        doctor.setUsername(dto.getUsername());
        doctor.setEmail(dto.getEmail());
        doctor.setSpec(dto.getSpec());
        doctor.setDocFees(dto.getDocFees());
        doctor.setPassword(dto.getPassword());
        
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    @DeleteMapping("/remove-doctor/{email}")
    public ResponseEntity<?> removeDoctor(@PathVariable String email) {
        doctorService.removeDoctor(email);
        return ResponseEntity.ok("Doctor removed");
    }

    @GetMapping("/appointments")
    public ResponseEntity<?> getAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @DeleteMapping("/remove-appointment/{id}")
    public ResponseEntity<?> removeAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Appointment removed");
    }

    @GetMapping("/prescriptions")
    public ResponseEntity<?> getPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getMessages() {
        return ResponseEntity.ok(contactService.getAllMessages());
    }
}
