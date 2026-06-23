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

}