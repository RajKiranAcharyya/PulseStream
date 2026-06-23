package com.hms.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionTest {

    @Test
    public void testNoArgsConstructor() {
        Prescription prescription = new Prescription();
        assertNull(prescription.getId());
        assertFalse(prescription.isDeleted());
        assertNull(prescription.getDoctor());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(14, 30);
        Prescription prescription = new Prescription(
                1L, false, 100L, "Dr. House", 5L, "Jane", "Doe", date, time, "Fever", "Paracetamol 500mg"
        );

        assertEquals(1L, prescription.getId());
        assertFalse(prescription.isDeleted());
        assertEquals(100L, prescription.getAppointmentId());
        assertEquals("Dr. House", prescription.getDoctor());
        assertEquals(5L, prescription.getPid());
        assertEquals("Jane", prescription.getFname());
        assertEquals("Doe", prescription.getLname());
        assertEquals(date, prescription.getAppdate());
        assertEquals(time, prescription.getApptime());
        assertEquals("Fever", prescription.getDisease());
        assertEquals("Paracetamol 500mg", prescription.getPrescription());
    }

    @Test
    public void testSettersAndEquality() {
        Prescription p1 = new Prescription();
        p1.setId(10L);
        p1.setDoctor("Dr. Wilson");
        p1.setDeleted(true);

        assertEquals(10L, p1.getId());
        assertEquals("Dr. Wilson", p1.getDoctor());
        assertTrue(p1.isDeleted());

        Prescription p2 = new Prescription();
        p2.setId(10L);
        p2.setDoctor("Dr. Wilson");
        p2.setDeleted(true);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotNull(p1.toString());
    }

    @Test
    public void testSetDoctor_Variant1() {
        Prescription obj = new Prescription();
        obj.setDoctor("Dr. Smith");
        assertEquals("Dr. Smith", obj.getDoctor());
    }

    @Test
    public void testSetDoctor_Variant2() {
        Prescription obj = new Prescription();
        obj.setDoctor("Dr. Jones");
        assertEquals("Dr. Jones", obj.getDoctor());
    }
}
