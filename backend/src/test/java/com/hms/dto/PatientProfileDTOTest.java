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

}