package com.hms.controller;

import com.hms.dto.AppointmentBookingDTO;
import com.hms.dto.PatientProfileDTO;
import com.hms.entity.Appointment;
import com.hms.entity.Doctor;
import com.hms.service.AppointmentService;
import com.hms.service.AvailabilityService;
import com.hms.service.DoctorService;
import com.hms.service.PatientService;
import com.hms.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/appointments")
    public ResponseEntity<?> getAppointments(Authentication auth) {
        String email = auth.getName();
        return patientService.getPatientByEmail(email)
                .map(p -> ResponseEntity.ok(appointmentService.getPatientAppointments(p.getPid())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentBookingDTO dto, Authentication auth) {
        java.time.LocalDate today = java.time.LocalDate.now();
        if (dto.getAppdate().isBefore(today)) {
            return ResponseEntity.badRequest().body("Cannot book appointments for past dates.");
        }
        if (dto.getAppdate().equals(today)) {
            java.time.LocalTime now = java.time.LocalTime.now();
            if (dto.getApptime().isBefore(now)) {
                return ResponseEntity.badRequest().body("This time slot has already passed for today.");
            }
        }

        String email = auth.getName();
        return patientService.getPatientByEmail(email)
                .map(p -> {
                    // SECURE MAPPING: Only use verified data from session and limited DTO fields
                    Appointment appointment = new Appointment();
                    appointment.setPid(p.getPid());
                    appointment.setFname(p.getFname());
                    appointment.setLname(p.getLname());
                    appointment.setEmail(p.getEmail());
                    appointment.setGender(p.getGender());
                    appointment.setContact(p.getContact());

                    // Fields from User input (Validated)
                    appointment.setDoctor(dto.getDoctor());
                    appointment.setAppdate(dto.getAppdate());
                    appointment.setApptime(dto.getApptime());
                    appointment.setDisease(dto.getDisease());
                    appointment.setAge(dto.getAge());
                    appointment.setDocFees(dto.getDocFees());

                    return ResponseEntity.ok(appointmentService.bookAppointment(appointment));
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelByPatient(id);
        return ResponseEntity.ok("Cancelled");
    }

    @GetMapping("/prescriptions")
    public ResponseEntity<?> getPrescriptions(Authentication auth) {
        String email = auth.getName();
        return patientService.getPatientByEmail(email)
                .map(p -> ResponseEntity.ok(prescriptionService.getPatientPrescriptions(p.getPid())))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/doctor-availability/{doctorName}/{date}")
    public ResponseEntity<?> getDoctorAvailability(@PathVariable String doctorName, @PathVariable String date) {
        java.time.LocalDate localDate = java.time.LocalDate.parse(date);
        java.time.LocalDate today = java.time.LocalDate.now();
        java.time.LocalTime now = java.time.LocalTime.now();

        Optional<Doctor> doctorOpt = doctorService.getDoctorByUsername(doctorName);
        if (doctorOpt.isPresent()) {
            Doctor d = doctorOpt.get();
            List<com.hms.entity.Availability> allSlots = availabilityService.getDoctorAvailability(d.getEmail());
            List<java.time.LocalTime> occupiedTimes = appointmentService.getOccupiedTimes(doctorName, localDate);

            List<com.hms.entity.Availability> availableSlots = allSlots.stream().filter(s -> {
                if (occupiedTimes.contains(s.getStartTime()))
                    return false;

                if (localDate.equals(today)) {
                    java.time.LocalTime slotTime = s.getStartTime();
                    return slotTime.isAfter(now);
                }
                return true;
            }).toList();
            return ResponseEntity.ok(availableSlots);
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication auth) {
        return patientService.getPatientByEmail(auth.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody PatientProfileDTO dto, Authentication auth) {
        return patientService.getPatientByEmail(auth.getName()).map(existing -> {
            // SECURE MAPPING: Users cannot change their PID or Email
            if (dto.getContact() != null)
                existing.setContact(dto.getContact());
            if (dto.getFname() != null)
                existing.setFname(dto.getFname());
            if (dto.getLname() != null)
                existing.setLname(dto.getLname());
            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                existing.setPassword(passwordEncoder.encode(dto.getPassword()));
            }
            return ResponseEntity.ok(patientService.savePatient(existing));
        }).orElse(ResponseEntity.badRequest().build());
    }
}
