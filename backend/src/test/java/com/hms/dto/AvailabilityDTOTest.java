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

    @Test
    public void testToString() {
        AvailabilityDTO dto = new AvailabilityDTO();
        assertNotNull(dto.toString());
    }


    @Test
    public void testSetDayofweek_Variant1() {
        AvailabilityDTO obj = new AvailabilityDTO();
        obj.setDayOfWeek("Monday");
        assertEquals("Monday", obj.getDayOfWeek());
    }

    @Test
    public void testSetDayofweek_Variant2() {
        AvailabilityDTO obj = new AvailabilityDTO();
        obj.setDayOfWeek("Wednesday");
        assertEquals("Wednesday", obj.getDayOfWeek());
    }

    @Test
    public void testSetDayofweek_Variant3() {
        AvailabilityDTO obj = new AvailabilityDTO();
        obj.setDayOfWeek("Friday");
        assertEquals("Friday", obj.getDayOfWeek());
    }

    @Test
    public void testSetDayofweek_Variant4() {
        AvailabilityDTO obj = new AvailabilityDTO();
        obj.setDayOfWeek("Sunday");
        assertEquals("Sunday", obj.getDayOfWeek());
    }
}