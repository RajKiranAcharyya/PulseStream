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

}