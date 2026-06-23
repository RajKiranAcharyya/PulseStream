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

    @Test
    public void testAllArgsConstructorAndGetters() {
        DoctorRegistrationDTO dto = new DoctorRegistrationDTO("dr_smith", "smith@gmail.com", "Cardiology", 500, "pass123");

        assertEquals("dr_smith", dto.getUsername());
        assertEquals("smith@gmail.com", dto.getEmail());
        assertEquals("Cardiology", dto.getSpec());
        assertEquals(500, dto.getDocFees());
        assertEquals("pass123", dto.getPassword());
    }

    @Test
    public void testSetters() {
        DoctorRegistrationDTO dto = new DoctorRegistrationDTO();
        dto.setUsername("dr_jones");

        assertEquals("dr_jones", dto.getUsername());
    }

    @Test
    public void testEqualsAndHashCode() {
        DoctorRegistrationDTO dto1 = new DoctorRegistrationDTO("dr_smith", "smith@gmail.com", "Cardiology", 500, "pass123");
        DoctorRegistrationDTO dto2 = new DoctorRegistrationDTO("dr_smith", "smith@gmail.com", "Cardiology", 500, "pass123");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        DoctorRegistrationDTO dto = new DoctorRegistrationDTO();
        assertNotNull(dto.toString());
    }


    @Test
    public void testSetUsername_Variant1() {
        DoctorRegistrationDTO obj = new DoctorRegistrationDTO();
        obj.setUsername("dr_smith");
        assertEquals("dr_smith", obj.getUsername());
    }

    @Test
    public void testSetUsername_Variant2() {
        DoctorRegistrationDTO obj = new DoctorRegistrationDTO();
        obj.setUsername("dr_jones");
        assertEquals("dr_jones", obj.getUsername());
    }

    @Test
    public void testSetUsername_Variant3() {
        DoctorRegistrationDTO obj = new DoctorRegistrationDTO();
        obj.setUsername("dr_taylor");
        assertEquals("dr_taylor", obj.getUsername());
    }

    @Test
    public void testSetUsername_Variant4() {
        DoctorRegistrationDTO obj = new DoctorRegistrationDTO();
        obj.setUsername("dr_brown");
        assertEquals("dr_brown", obj.getUsername());
    }

    @Test
    public void testSetEmail_Variant1() {
        DoctorRegistrationDTO obj = new DoctorRegistrationDTO();
        obj.setEmail("smith@gmail.com");
        assertEquals("smith@gmail.com", obj.getEmail());
    }
}