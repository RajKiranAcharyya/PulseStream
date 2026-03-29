package com.hms.service;

import com.hms.entity.Appointment;
import com.hms.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private EmailService emailService;

    public java.util.Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public boolean isSlotBooked(String doctor, java.time.LocalDate date, java.time.LocalTime time) {
        return appointmentRepository.isSlotOccupied(doctor, date, time);
    }

    public Appointment bookAppointment(Appointment appointment) {
        if (isSlotBooked(appointment.getDoctor(), appointment.getAppdate(), appointment.getApptime())) {
            throw new RuntimeException("This slot is already booked!");
        }
        appointment.setUserStatus(1);
        appointment.setDoctorStatus(1);
        Appointment saved = appointmentRepository.save(appointment);
        emailService.sendEmail(saved.getEmail(), "Appointment Booked", 
            "Dear " + saved.getFname() + ",\n\nYour appointment with Dr. " + saved.getDoctor() + 
            " has been booked for " + saved.getAppdate() + " at " + saved.getApptime() + ".\n\nThank you!");
        return saved;
    }

    public List<Appointment> getPatientAppointments(Long pid) {
        return appointmentRepository.findByPid(pid);
    }

    public List<Appointment> getDoctorAppointments(String doctorName) {
        return appointmentRepository.findByDoctor(doctorName);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void cancelByPatient(Long id) {
        appointmentRepository.findById(id).ifPresent(a -> {
            a.setUserStatus(0);
            appointmentRepository.save(a);
            emailService.sendEmail(a.getEmail(), "Appointment Cancelled", 
                "Your appointment with Dr. " + a.getDoctor() + " on " + a.getAppdate() + " has been cancelled by you.");
        });
    }

    public void cancelByDoctor(Long id) {
        appointmentRepository.findById(id).ifPresent(a -> {
            a.setDoctorStatus(0);
            appointmentRepository.save(a);
            emailService.sendEmail(a.getEmail(), "Appointment Cancelled by Doctor", 
                "Your appointment with Dr. " + a.getDoctor() + " on " + a.getAppdate() + " has been cancelled by the doctor.");
        });
    }

    public void prescribeByDoctor(Long id) {
        appointmentRepository.findById(id).ifPresent(a -> {
            a.setDoctorStatus(2); // 2: Prescribed
            appointmentRepository.save(a);
            emailService.sendEmail(a.getEmail(), "Prescription Issued", 
                "Dr. " + a.getDoctor() + " has issued a prescription for your appointment on " + a.getAppdate() + ". Please check your dashboard.");
        });
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
