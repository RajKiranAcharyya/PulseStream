package com.hms.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientRegistrationDTOTest {

    @Test
    public void testNoArgsConstructor() {
        PatientRegistrationDTO dto = new PatientRegistrationDTO();
        assertNull(dto.getFname());
        assertNull(dto.getEmail());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        PatientRegistrationDTO dto = new PatientRegistrationDTO("John", "Doe", "Male", "john@gmail.com", "1234567890", "pass123");

        assertEquals("John", dto.getFname());
        assertEquals("Doe", dto.getLname());
        assertEquals("Male", dto.getGender());
        assertEquals("john@gmail.com", dto.getEmail());
        assertEquals("1234567890", dto.getContact());
        assertEquals("pass123", dto.getPassword());
    }

    @Test
    public void testSetters() {
        PatientRegistrationDTO dto = new PatientRegistrationDTO();
        dto.setFname("Jane");

        assertEquals("Jane", dto.getFname());
    }

    @Test
    public void testEqualsAndHashCode() {
        PatientRegistrationDTO dto1 = new PatientRegistrationDTO("John", "Doe", "Male", "john@gmail.com", "1234567890", "pass123");
        PatientRegistrationDTO dto2 = new PatientRegistrationDTO("John", "Doe", "Male", "john@gmail.com", "1234567890", "pass123");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        PatientRegistrationDTO dto = new PatientRegistrationDTO();
        assertNotNull(dto.toString());
    }

}