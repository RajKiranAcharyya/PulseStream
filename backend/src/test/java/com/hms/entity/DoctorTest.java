package com.hms.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoctorTest {

    @Test
    public void testNoArgsConstructor() {
        Doctor doctor = new Doctor();
        assertNull(doctor.getEmail());
        assertFalse(doctor.isDeleted());
        assertNull(doctor.getUsername());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        Doctor doctor = new Doctor("doctor@gmail.com", false, "dr_smith", "pass123", "Cardiology", 800);

        assertEquals("doctor@gmail.com", doctor.getEmail());
        assertFalse(doctor.isDeleted());
        assertEquals("dr_smith", doctor.getUsername());
        assertEquals("pass123", doctor.getPassword());
        assertEquals("Cardiology", doctor.getSpec());
        assertEquals(800, doctor.getDocFees());
    }

    @Test
    public void testSettersAndEquality() {
        Doctor d1 = new Doctor();
        d1.setEmail("doctor2@gmail.com");
        d1.setUsername("dr_jones");
        d1.setDocFees(1000);
        d1.setDeleted(true);

        assertEquals("doctor2@gmail.com", d1.getEmail());
        assertEquals("dr_jones", d1.getUsername());
        assertEquals(1000, d1.getDocFees());
        assertTrue(d1.isDeleted());

        Doctor d2 = new Doctor();
        d2.setEmail("doctor2@gmail.com");
        d2.setUsername("dr_jones");
        d2.setDocFees(1000);
        d2.setDeleted(true);

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertNotNull(d1.toString());
    }

    @Test
    public void testSetEmail_Variant1() {
        Doctor obj = new Doctor();
        obj.setEmail("doc_a@hms.com");
        assertEquals("doc_a@hms.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant2() {
        Doctor obj = new Doctor();
        obj.setEmail("doc_b@hms.com");
        assertEquals("doc_b@hms.com", obj.getEmail());
    }
}
