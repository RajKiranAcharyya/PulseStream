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

}