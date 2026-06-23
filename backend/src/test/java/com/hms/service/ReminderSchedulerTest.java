package com.hms.service;

import com.hms.entity.Appointment;
import com.hms.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class ReminderSchedulerTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ReminderScheduler reminderScheduler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendDailyReminders_NoAppointments() {
        LocalDate today = LocalDate.now();
        when(appointmentRepository.findByAppdateAndUserStatusAndDoctorStatus(today, 1, 1))
                .thenReturn(Collections.emptyList());

        reminderScheduler.sendDailyReminders();

        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    public void testSendDailyReminders_WithAppointments() {
        LocalDate today = LocalDate.now();
        Appointment app = new Appointment();
        app.setEmail("patient@gmail.com");
        app.setFname("John");
        app.setDoctor("Dr. Smith");
        app.setApptime(LocalTime.of(10, 0));

        when(appointmentRepository.findByAppdateAndUserStatusAndDoctorStatus(today, 1, 1))
                .thenReturn(Arrays.asList(app));

        reminderScheduler.sendDailyReminders();

        verify(emailService, times(1)).sendEmail(
                eq("patient@gmail.com"),
                eq("Appointment Reminder"),
                contains("Dr. Smith")
        );
    }

}