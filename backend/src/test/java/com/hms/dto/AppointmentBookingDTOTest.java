package com.hms.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentBookingDTOTest {

    @Test
    public void testNoArgsConstructor() {
        AppointmentBookingDTO dto = new AppointmentBookingDTO();
        assertNull(dto.getDoctor());
        assertNull(dto.getAppdate());
        assertNull(dto.getApptime());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(10, 0);
        AppointmentBookingDTO dto = new AppointmentBookingDTO("Dr. Smith", date, time, "Flu", 30, 500);

        assertEquals("Dr. Smith", dto.getDoctor());
        assertEquals(date, dto.getAppdate());
        assertEquals(time, dto.getApptime());
        assertEquals("Flu", dto.getDisease());
        assertEquals(30, dto.getAge());
        assertEquals(500, dto.getDocFees());
    }

    @Test
    public void testSetters() {
        AppointmentBookingDTO dto = new AppointmentBookingDTO();
        dto.setDoctor("Dr. Smith");
        dto.setAge(25);

        assertEquals("Dr. Smith", dto.getDoctor());
        assertEquals(25, dto.getAge());
    }

    @Test
    public void testEqualsAndHashCode() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(10, 0);
        AppointmentBookingDTO dto1 = new AppointmentBookingDTO("Dr. Smith", date, time, "Flu", 30, 500);
        AppointmentBookingDTO dto2 = new AppointmentBookingDTO("Dr. Smith", date, time, "Flu", 30, 500);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        AppointmentBookingDTO dto = new AppointmentBookingDTO();
        assertNotNull(dto.toString());
    }


    @Test
    public void testSetDoctor_Variant1() {
        AppointmentBookingDTO obj = new AppointmentBookingDTO();
        obj.setDoctor("Dr. Smith");
        assertEquals("Dr. Smith", obj.getDoctor());
    }

    @Test
    public void testSetDoctor_Variant2() {
        AppointmentBookingDTO obj = new AppointmentBookingDTO();
        obj.setDoctor("Dr. Jones");
        assertEquals("Dr. Jones", obj.getDoctor());
    }

    @Test
    public void testSetDoctor_Variant3() {
        AppointmentBookingDTO obj = new AppointmentBookingDTO();
        obj.setDoctor("Dr. Taylor");
        assertEquals("Dr. Taylor", obj.getDoctor());
    }
}