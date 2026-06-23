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

}