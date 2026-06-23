package com.hms.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoctorRegistrationDTOTest {

    @Test
    public void testNoArgsConstructor() {
        DoctorRegistrationDTO dto = new DoctorRegistrationDTO();
        assertNull(dto.getUsername());
        assertNull(dto.getEmail());
    }

}