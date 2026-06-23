package com.hms.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    @Test
    public void testNoArgsConstructor() {
        Patient patient = new Patient();
        assertNull(patient.getPid());
        assertFalse(patient.isDeleted());
        assertNull(patient.getFname());
        assertNull(patient.getLname());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        Patient patient = new Patient(1L, false, "John", "Doe", "Male", "john@gmail.com", "1234567890", "password123");

        assertEquals(1L, patient.getPid());
        assertFalse(patient.isDeleted());
        assertEquals("John", patient.getFname());
        assertEquals("Doe", patient.getLname());
        assertEquals("Male", patient.getGender());
        assertEquals("john@gmail.com", patient.getEmail());
        assertEquals("1234567890", patient.getContact());
        assertEquals("password123", patient.getPassword());
    }

    @Test
    public void testSetters() {
        Patient patient = new Patient();
        patient.setPid(10L);
        patient.setDeleted(true);
        patient.setFname("Jane");
        patient.setLname("Smith");
        patient.setGender("Female");
        patient.setEmail("jane@gmail.com");
        patient.setContact("0987654321");
        patient.setPassword("newpass");

        assertEquals(10L, patient.getPid());
        assertTrue(patient.isDeleted());
        assertEquals("Jane", patient.getFname());
        assertEquals("Smith", patient.getLname());
        assertEquals("Female", patient.getGender());
        assertEquals("jane@gmail.com", patient.getEmail());
        assertEquals("0987654321", patient.getContact());
        assertEquals("newpass", patient.getPassword());
    }

    @Test
    public void testEqualsAndHashCode() {
        Patient p1 = new Patient(1L, false, "John", "Doe", "Male", "john@gmail.com", "1234567890", "password123");
        Patient p2 = new Patient(1L, false, "John", "Doe", "Male", "john@gmail.com", "1234567890", "password123");

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotNull(p1.toString());
    }
}
