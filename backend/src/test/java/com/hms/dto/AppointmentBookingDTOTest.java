package com.hms.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentBookingDTOTest {

    @Test
    public void testNoArgsConstructor() {
        AppointmentBookingDTO dto = new AppointmentBookingDTO();
        assertNull(dto.getDoctor());
        assertNull(dto.getAppdate());
        assertNull(dto.getApptime());
    }

}