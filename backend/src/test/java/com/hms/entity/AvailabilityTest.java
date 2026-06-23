package com.hms.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class AvailabilityTest {

    @Test
    public void testNoArgsConstructor() {
        Availability availability = new Availability();
        assertNull(availability.getId());
        assertFalse(availability.isDeleted());
        assertNull(availability.getDoctorEmail());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 0);
        Availability availability = new Availability(1L, "doctor@hms.com", "Monday", start, end, false);

        assertEquals(1L, availability.getId());
        assertEquals("doctor@hms.com", availability.getDoctorEmail());
        assertEquals("Monday", availability.getDayOfWeek());
        assertEquals(start, availability.getStartTime());
        assertEquals(end, availability.getEndTime());
        assertFalse(availability.isDeleted());
    }

    @Test
    public void testSettersAndEquality() {
        Availability av1 = new Availability();
        av1.setId(5L);
        av1.setDoctorEmail("doc2@hms.com");
        av1.setDayOfWeek("Tuesday");

        assertEquals(5L, av1.getId());
        assertEquals("doc2@hms.com", av1.getDoctorEmail());
        assertEquals("Tuesday", av1.getDayOfWeek());

        Availability av2 = new Availability();
        av2.setId(5L);
        av2.setDoctorEmail("doc2@hms.com");
        av2.setDayOfWeek("Tuesday");

        assertEquals(av1, av2);
        assertEquals(av1.hashCode(), av2.hashCode());
        assertNotNull(av1.toString());
    }

    @Test
    public void testSetDoctoremail_Variant1() {
        Availability obj = new Availability();
        obj.setDoctorEmail("doc_smith@gmail.com");
        assertEquals("doc_smith@gmail.com", obj.getDoctorEmail());
    }

    @Test
    public void testSetDoctoremail_Variant2() {
        Availability obj = new Availability();
        obj.setDoctorEmail("doc_jones@gmail.com");
        assertEquals("doc_jones@gmail.com", obj.getDoctorEmail());
    }
}
