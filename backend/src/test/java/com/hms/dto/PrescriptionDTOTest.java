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

    @Test
    public void testAllArgsConstructorAndGetters() {
        PrescriptionDTO dto = new PrescriptionDTO(10L, "Fever", "Aspirin");

        assertEquals(10L, dto.getAppointmentId());
        assertEquals("Fever", dto.getDisease());
        assertEquals("Aspirin", dto.getPrescription());
    }

    @Test
    public void testSetters() {
        PrescriptionDTO dto = new PrescriptionDTO();
        dto.setDisease("Flu");

        assertEquals("Flu", dto.getDisease());
    }

    @Test
    public void testEqualsAndHashCode() {
        PrescriptionDTO dto1 = new PrescriptionDTO(10L, "Fever", "Aspirin");
        PrescriptionDTO dto2 = new PrescriptionDTO(10L, "Fever", "Aspirin");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        PrescriptionDTO dto = new PrescriptionDTO();
        assertNotNull(dto.toString());
    }

}