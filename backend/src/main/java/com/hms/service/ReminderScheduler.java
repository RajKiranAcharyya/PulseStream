package com.hms.service;

import com.hms.entity.Appointment;
import com.hms.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReminderScheduler {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private EmailService emailService;

    // Runs every day at 8:00 AM
    @Scheduled(cron = "0 0 8 * * *")
    public void sendDailyReminders() {
        LocalDate today = LocalDate.now();
        List<Appointment> todayApps = appointmentRepository.findByAppdateAndUserStatusAndDoctorStatus(today, 1, 1);
        
        for (Appointment a : todayApps) {
            emailService.sendEmail(a.getEmail(), "Appointment Reminder", 
                "Dear " + a.getFname() + ",\n\nThis is a reminder for your appointment with Dr. " + a.getDoctor() + 
                " today at " + a.getApptime() + ".\n\nThank you!");
        }
        System.out.println("Daily reminders sent for: " + today);
    }
}
