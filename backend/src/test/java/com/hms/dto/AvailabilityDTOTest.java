package com.hms.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class AvailabilityDTOTest {

    @Test
    public void testNoArgsConstructor() {
        AvailabilityDTO dto = new AvailabilityDTO();
        assertNull(dto.getDayOfWeek());
        assertNull(dto.getStartTime());
    }

}