package com.hms.controller;

import com.hms.entity.Availability;
import com.hms.entity.Prescription;
import com.hms.service.AppointmentService;
import com.hms.service.AvailabilityService;
import com.hms.service.DoctorService;
import com.hms.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private PrescriptionService prescriptionService;
    
    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/appointments")
    public ResponseEntity<?> getAppointments(Authentication auth) {
        String email = auth.getName();
        return doctorService.getDoctorByEmail(email)
                .map(d -> ResponseEntity.ok(appointmentService.getDoctorAppointments(d.getUsername())))
                .orElse(ResponseEntity.ok(appointmentService.getDoctorAppointments(email)));
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelByDoctor(id);
        return ResponseEntity.ok("Cancelled");
    }

    @PostMapping("/prescribe")
    public ResponseEntity<?> prescribe(@RequestBody Prescription prescription, Authentication auth) {
        if (prescription.getAppointmentId() != null) {
            appointmentService.getAppointmentById(prescription.getAppointmentId()).ifPresent(app -> {
                prescription.setFname(app.getFname());
                prescription.setLname(app.getLname());
                prescription.setPid(app.getPid());
                prescription.setAppdate(app.getAppdate());
                prescription.setApptime(app.getApptime());
            });
        }
        String email = auth.getName();
        doctorService.getDoctorByEmail(email).ifPresent(d -> prescription.setDoctor(d.getUsername()));
        if (prescription.getDoctor() == null) prescription.setDoctor(email);
        
        Prescription saved = prescriptionService.savePrescription(prescription);
        if (prescription.getAppointmentId() != null) {
            appointmentService.prescribeByDoctor(prescription.getAppointmentId());
        }
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/availability")
    public ResponseEntity<?> getAvailability(Authentication auth) {
        return ResponseEntity.ok(availabilityService.getDoctorAvailability(auth.getName()));
    }

    @PostMapping("/availability")
    public ResponseEntity<?> addAvailability(@RequestBody Availability availability, Authentication auth) {
        availability.setDoctorEmail(auth.getName());
        return ResponseEntity.ok(availabilityService.addAvailability(availability));
    }

    @DeleteMapping("/availability/{id}")
    public ResponseEntity<?> removeAvailability(@PathVariable Long id) {
        availabilityService.removeAvailability(id);
        return ResponseEntity.ok("Removed");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication auth) {
        return doctorService.getDoctorByEmail(auth.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody com.hms.entity.Doctor doctor, Authentication auth) {
        return doctorService.getDoctorByEmail(auth.getName()).map(existing -> {
            if (doctor.getUsername() != null) existing.setUsername(doctor.getUsername());
            if (doctor.getSpec() != null) existing.setSpec(doctor.getSpec());
            if (doctor.getDocFees() != null) existing.setDocFees(doctor.getDocFees());
            if (doctor.getPassword() != null && !doctor.getPassword().isEmpty()) {
                existing.setPassword(passwordEncoder.encode(doctor.getPassword()));
            }
            return ResponseEntity.ok(doctorService.saveDoctor(existing));
        }).orElse(ResponseEntity.badRequest().build());
    }
}
