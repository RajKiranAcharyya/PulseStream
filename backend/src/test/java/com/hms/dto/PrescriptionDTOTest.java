package com.hms.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionDTOTest {

    @Test
    public void testNoArgsConstructor() {
        PrescriptionDTO dto = new PrescriptionDTO();
        assertNull(dto.getPrescription());
        assertNull(dto.getDisease());
    }

}