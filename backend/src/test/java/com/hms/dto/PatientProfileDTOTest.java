package com.hms.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientProfileDTOTest {

    @Test
    public void testNoArgsConstructor() {
        PatientProfileDTO dto = new PatientProfileDTO();
        assertNull(dto.getFname());
        assertNull(dto.getLname());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        PatientProfileDTO dto = new PatientProfileDTO("John", "Doe", "1234567890", "pass123");

        assertEquals("John", dto.getFname());
        assertEquals("Doe", dto.getLname());
        assertEquals("1234567890", dto.getContact());
        assertEquals("pass123", dto.getPassword());
    }

    @Test
    public void testSetters() {
        PatientProfileDTO dto = new PatientProfileDTO();
        dto.setFname("Jane");

        assertEquals("Jane", dto.getFname());
    }

    @Test
    public void testEqualsAndHashCode() {
        PatientProfileDTO dto1 = new PatientProfileDTO("John", "Doe", "1234567890", "pass123");
        PatientProfileDTO dto2 = new PatientProfileDTO("John", "Doe", "1234567890", "pass123");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

}