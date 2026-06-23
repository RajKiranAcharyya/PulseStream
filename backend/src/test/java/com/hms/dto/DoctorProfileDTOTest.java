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

    @Test
    public void testAllArgsConstructorAndGetters() {
        DoctorProfileDTO dto = new DoctorProfileDTO("dr_smith", "Cardiology", 500, "pass123");

        assertEquals("dr_smith", dto.getUsername());
        assertEquals("Cardiology", dto.getSpec());
        assertEquals(500, dto.getDocFees());
        assertEquals("pass123", dto.getPassword());
    }

    @Test
    public void testSetters() {
        DoctorProfileDTO dto = new DoctorProfileDTO();
        dto.setUsername("dr_jones");

        assertEquals("dr_jones", dto.getUsername());
    }

    @Test
    public void testEqualsAndHashCode() {
        DoctorProfileDTO dto1 = new DoctorProfileDTO("dr_smith", "Cardiology", 500, "pass123");
        DoctorProfileDTO dto2 = new DoctorProfileDTO("dr_smith", "Cardiology", 500, "pass123");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        DoctorProfileDTO dto = new DoctorProfileDTO();
        assertNotNull(dto.toString());
    }


    @Test
    public void testSetUsername_Variant1() {
        DoctorProfileDTO obj = new DoctorProfileDTO();
        obj.setUsername("dr_smith");
        assertEquals("dr_smith", obj.getUsername());
    }

    @Test
    public void testSetUsername_Variant2() {
        DoctorProfileDTO obj = new DoctorProfileDTO();
        obj.setUsername("dr_jones");
        assertEquals("dr_jones", obj.getUsername());
    }

    @Test
    public void testSetUsername_Variant3() {
        DoctorProfileDTO obj = new DoctorProfileDTO();
        obj.setUsername("dr_taylor");
        assertEquals("dr_taylor", obj.getUsername());
    }

    @Test
    public void testSetUsername_Variant4() {
        DoctorProfileDTO obj = new DoctorProfileDTO();
        obj.setUsername("dr_brown");
        assertEquals("dr_brown", obj.getUsername());
    }

    @Test
    public void testSetSpec_Variant1() {
        DoctorProfileDTO obj = new DoctorProfileDTO();
        obj.setSpec("Cardiology");
        assertEquals("Cardiology", obj.getSpec());
    }

    @Test
    public void testSetSpec_Variant2() {
        DoctorProfileDTO obj = new DoctorProfileDTO();
        obj.setSpec("Neurology");
        assertEquals("Neurology", obj.getSpec());
    }
}