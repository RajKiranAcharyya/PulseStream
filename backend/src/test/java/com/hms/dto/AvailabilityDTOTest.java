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

    @Test
    public void testAllArgsConstructorAndGetters() {
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 0);
        AvailabilityDTO dto = new AvailabilityDTO("Monday", start, end);

        assertEquals("Monday", dto.getDayOfWeek());
        assertEquals(start, dto.getStartTime());
        assertEquals(end, dto.getEndTime());
    }

    @Test
    public void testSetters() {
        AvailabilityDTO dto = new AvailabilityDTO();
        dto.setDayOfWeek("Tuesday");

        assertEquals("Tuesday", dto.getDayOfWeek());
    }

    @Test
    public void testEqualsAndHashCode() {
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 0);
        AvailabilityDTO dto1 = new AvailabilityDTO("Monday", start, end);
        AvailabilityDTO dto2 = new AvailabilityDTO("Monday", start, end);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

}