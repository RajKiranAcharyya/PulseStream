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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAppointmentById() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        Optional<Appointment> result = appointmentService.getAppointmentById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    public void testGetOccupiedTimes() {
        LocalDate date = LocalDate.now();
        List<LocalTime> times = Arrays.asList(LocalTime.of(10, 0), LocalTime.of(11, 0));
        when(appointmentRepository.findOccupiedTimes("Dr. Smith", date)).thenReturn(times);

        List<LocalTime> result = appointmentService.getOccupiedTimes("Dr. Smith", date);
        assertEquals(2, result.size());
        assertTrue(result.contains(LocalTime.of(10, 0)));
    }

    @Test
    public void testGetPatientAppointments() {
        Appointment app1 = new Appointment();
        app1.setPid(5L);
        when(appointmentRepository.findByPid(5L)).thenReturn(Arrays.asList(app1));

        List<Appointment> result = appointmentService.getPatientAppointments(5L);
        assertEquals(1, result.size());
        assertEquals(5L, result.get(0).getPid());
    }

    @Test
    public void testGetDoctorAppointments() {
        Appointment app1 = new Appointment();
        app1.setDoctor("Dr. Smith");
        when(appointmentRepository.findByDoctor("Dr. Smith")).thenReturn(Arrays.asList(app1));

        List<Appointment> result = appointmentService.getDoctorAppointments("Dr. Smith");
        assertEquals(1, result.size());
        assertEquals("Dr. Smith", result.get(0).getDoctor());
    }

    @Test
    public void testGetAllAppointments() {
        Appointment app1 = new Appointment();
        Appointment app2 = new Appointment();
        when(appointmentRepository.findAll()).thenReturn(Arrays.asList(app1, app2));

        List<Appointment> result = appointmentService.getAllAppointments();
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteAppointment() {
        doNothing().when(appointmentRepository).deleteById(1L);
        appointmentService.deleteAppointment(1L);
        verify(appointmentRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testBookAppointment_Success() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(10, 0);
        Appointment app = new Appointment();
        app.setDoctor("Dr. Smith");
        app.setAppdate(date);
        app.setApptime(time);
        app.setEmail("patient@gmail.com");
        app.setFname("John");

        when(appointmentRepository.findOccupiedTimes("Dr. Smith", date)).thenReturn(Arrays.asList());
        when(appointmentRepository.save(app)).thenReturn(app);

        Appointment result = appointmentService.bookAppointment(app);
        assertNotNull(result);
        assertEquals(1, result.getUserStatus());
        assertEquals(1, result.getDoctorStatus());
        verify(emailService, times(1)).sendEmail(eq("patient@gmail.com"), anyString(), anyString());
    }

    @Test
    public void testBookAppointment_ThrowsConflictException() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(10, 0);
        Appointment app = new Appointment();
        app.setDoctor("Dr. Smith");
        app.setAppdate(date);
        app.setApptime(time);

        when(appointmentRepository.findOccupiedTimes("Dr. Smith", date)).thenReturn(Arrays.asList(time));

        assertThrows(RuntimeException.class, () -> {
            appointmentService.bookAppointment(app);
        });
    }

    @Test
    public void testCancelByPatient_Success() {
        Appointment app = new Appointment();
        app.setId(1L);
        app.setEmail("patient@gmail.com");
        app.setDoctor("Dr. Smith");
        app.setAppdate(LocalDate.now());

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(app));
        when(appointmentRepository.save(app)).thenReturn(app);

        appointmentService.cancelByPatient(1L);
        assertEquals(0, app.getUserStatus());
        verify(emailService, times(1)).sendEmail(eq("patient@gmail.com"), anyString(), anyString());
    }

    @Test
    public void testCancelByDoctor_Success() {
        Appointment app = new Appointment();
        app.setId(1L);
        app.setEmail("patient@gmail.com");
        app.setDoctor("Dr. Smith");
        app.setAppdate(LocalDate.now());

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(app));
        when(appointmentRepository.save(app)).thenReturn(app);

        appointmentService.cancelByDoctor(1L);
        assertEquals(0, app.getDoctorStatus());
        verify(emailService, times(1)).sendEmail(eq("patient@gmail.com"), anyString(), anyString());
    }

    @Test
    public void testPrescribeByDoctor_Success() {
        Appointment app = new Appointment();
        app.setId(1L);
        app.setEmail("patient@gmail.com");
        app.setDoctor("Dr. Smith");
        app.setAppdate(LocalDate.now());

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(app));
        when(appointmentRepository.save(app)).thenReturn(app);

        appointmentService.prescribeByDoctor(1L);
        assertEquals(2, app.getDoctorStatus());
        verify(emailService, times(1)).sendEmail(eq("patient@gmail.com"), anyString(), anyString());
    }
}
