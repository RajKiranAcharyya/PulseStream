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

    @Test
    public void testSetFname_Variant1() {
        Patient obj = new Patient();
        obj.setFname("Alice");
        assertEquals("Alice", obj.getFname());
    }

    @Test
    public void testSetFname_Variant2() {
        Patient obj = new Patient();
        obj.setFname("Bob");
        assertEquals("Bob", obj.getFname());
    }

    @Test
    public void testSetFname_Variant3() {
        Patient obj = new Patient();
        obj.setFname("Charlie");
        assertEquals("Charlie", obj.getFname());
    }

    @Test
    public void testSetFname_Variant4() {
        Patient obj = new Patient();
        obj.setFname("Diana");
        assertEquals("Diana", obj.getFname());
    }

    @Test
    public void testSetLname_Variant1() {
        Patient obj = new Patient();
        obj.setLname("Smith");
        assertEquals("Smith", obj.getLname());
    }

    @Test
    public void testSetLname_Variant2() {
        Patient obj = new Patient();
        obj.setLname("Jones");
        assertEquals("Jones", obj.getLname());
    }

    @Test
    public void testSetLname_Variant3() {
        Patient obj = new Patient();
        obj.setLname("Miller");
        assertEquals("Miller", obj.getLname());
    }

    @Test
    public void testSetLname_Variant4() {
        Patient obj = new Patient();
        obj.setLname("Davis");
        assertEquals("Davis", obj.getLname());
    }

    @Test
    public void testSetGender_Variant1() {
        Patient obj = new Patient();
        obj.setGender("Female");
        assertEquals("Female", obj.getGender());
    }

    @Test
    public void testSetGender_Variant2() {
        Patient obj = new Patient();
        obj.setGender("Male");
        assertEquals("Male", obj.getGender());
    }

    @Test
    public void testSetGender_Variant3() {
        Patient obj = new Patient();
        obj.setGender("Other");
        assertEquals("Other", obj.getGender());
    }

    @Test
    public void testSetGender_Variant4() {
        Patient obj = new Patient();
        obj.setGender("Unknown");
        assertEquals("Unknown", obj.getGender());
    }

    @Test
    public void testSetEmail_Variant1() {
        Patient obj = new Patient();
        obj.setEmail("alice@gmail.com");
        assertEquals("alice@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant2() {
        Patient obj = new Patient();
        obj.setEmail("bob@gmail.com");
        assertEquals("bob@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant3() {
        Patient obj = new Patient();
        obj.setEmail("charlie@gmail.com");
        assertEquals("charlie@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant4() {
        Patient obj = new Patient();
        obj.setEmail("diana@gmail.com");
        assertEquals("diana@gmail.com", obj.getEmail());
    }
}
