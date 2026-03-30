package com.hms.controller;

import com.hms.dto.AvailabilityDTO;
import com.hms.dto.DoctorProfileDTO;
import com.hms.dto.PrescriptionDTO;
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
    public ResponseEntity<?> prescribe(@RequestBody PrescriptionDTO dto, Authentication auth) {
        Prescription prescription = new Prescription();
        prescription.setAppointmentId(dto.getAppointmentId());
        prescription.setPrescription(dto.getPrescription());
        prescription.setDisease(dto.getDisease());

        if (dto.getAppointmentId() != null) {
            appointmentService.getAppointmentById(dto.getAppointmentId()).ifPresent(app -> {
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
        if (dto.getAppointmentId() != null) {
            appointmentService.prescribeByDoctor(dto.getAppointmentId());
        }
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/availability")
    public ResponseEntity<?> getAvailability(Authentication auth) {
        return ResponseEntity.ok(availabilityService.getDoctorAvailability(auth.getName()));
    }

    @PostMapping("/availability")
    public ResponseEntity<?> addAvailability(@RequestBody AvailabilityDTO dto, Authentication auth) {
        Availability availability = new Availability();
        availability.setDoctorEmail(auth.getName());
        availability.setDayOfWeek(dto.getDayOfWeek());
        availability.setStartTime(dto.getStartTime());
        availability.setEndTime(dto.getEndTime());
        
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
    public ResponseEntity<?> updateProfile(@RequestBody DoctorProfileDTO dto, Authentication auth) {
        return doctorService.getDoctorByEmail(auth.getName()).map(existing -> {
            if (dto.getUsername() != null) existing.setUsername(dto.getUsername());
            if (dto.getSpec() != null) existing.setSpec(dto.getSpec());
            if (dto.getDocFees() != null) existing.setDocFees(dto.getDocFees());
            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                existing.setPassword(passwordEncoder.encode(dto.getPassword()));
            }
            return ResponseEntity.ok(doctorService.saveDoctor(existing));
        }).orElse(ResponseEntity.badRequest().build());
    }
}
