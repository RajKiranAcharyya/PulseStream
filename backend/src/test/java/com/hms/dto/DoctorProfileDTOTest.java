package com.hms.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoctorProfileDTOTest {

    @Test
    public void testNoArgsConstructor() {
        DoctorProfileDTO dto = new DoctorProfileDTO();
        assertNull(dto.getUsername());
        assertNull(dto.getSpec());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        DoctorProfileDTO dto = new DoctorProfileDTO("dr_smith", "Cardiology", 500, "pass123");

        assertEquals("dr_smith", dto.getUsername());
        assertEquals("Cardiology", dto.getSpec());
        assertEquals(500, dto.getDocFees());
        assertEquals("pass123", dto.getPassword());
    }

}