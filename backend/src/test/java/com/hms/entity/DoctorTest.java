package com.hms.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoctorTest {

    @Test
    public void testNoArgsConstructor() {
        Doctor doctor = new Doctor();
        assertNull(doctor.getEmail());
        assertFalse(doctor.isDeleted());
        assertNull(doctor.getUsername());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        Doctor doctor = new Doctor("doctor@gmail.com", false, "dr_smith", "pass123", "Cardiology", 800);

        assertEquals("doctor@gmail.com", doctor.getEmail());
        assertFalse(doctor.isDeleted());
        assertEquals("dr_smith", doctor.getUsername());
        assertEquals("pass123", doctor.getPassword());
        assertEquals("Cardiology", doctor.getSpec());
        assertEquals(800, doctor.getDocFees());
    }

    @Test
    public void testSettersAndEquality() {
        Doctor d1 = new Doctor();
        d1.setEmail("doctor2@gmail.com");
        d1.setUsername("dr_jones");
        d1.setDocFees(1000);
        d1.setDeleted(true);

        assertEquals("doctor2@gmail.com", d1.getEmail());
        assertEquals("dr_jones", d1.getUsername());
        assertEquals(1000, d1.getDocFees());
        assertTrue(d1.isDeleted());

        Doctor d2 = new Doctor();
        d2.setEmail("doctor2@gmail.com");
        d2.setUsername("dr_jones");
        d2.setDocFees(1000);
        d2.setDeleted(true);

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertNotNull(d1.toString());
    }

    @Test
    public void testSetEmail_Variant1() {
        Doctor obj = new Doctor();
        obj.setEmail("doc_a@hms.com");
        assertEquals("doc_a@hms.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant2() {
        Doctor obj = new Doctor();
        obj.setEmail("doc_b@hms.com");
        assertEquals("doc_b@hms.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant3() {
        Doctor obj = new Doctor();
        obj.setEmail("doc_c@hms.com");
        assertEquals("doc_c@hms.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant4() {
        Doctor obj = new Doctor();
        obj.setEmail("doc_d@hms.com");
        assertEquals("doc_d@hms.com", obj.getEmail());
    }

    @Test
    public void testSetUsername_Variant1() {
        Doctor obj = new Doctor();
        obj.setUsername("dr_a");
        assertEquals("dr_a", obj.getUsername());
    }

    @Test
    public void testSetUsername_Variant2() {
        Doctor obj = new Doctor();
        obj.setUsername("dr_b");
        assertEquals("dr_b", obj.getUsername());
    }

    @Test
    public void testSetUsername_Variant3() {
        Doctor obj = new Doctor();
        obj.setUsername("dr_c");
        assertEquals("dr_c", obj.getUsername());
    }

    @Test
    public void testSetUsername_Variant4() {
        Doctor obj = new Doctor();
        obj.setUsername("dr_d");
        assertEquals("dr_d", obj.getUsername());
    }

    @Test
    public void testSetPassword_Variant1() {
        Doctor obj = new Doctor();
        obj.setPassword("secret1");
        assertEquals("secret1", obj.getPassword());
    }

    @Test
    public void testSetPassword_Variant2() {
        Doctor obj = new Doctor();
        obj.setPassword("secret2");
        assertEquals("secret2", obj.getPassword());
    }

    @Test
    public void testSetPassword_Variant3() {
        Doctor obj = new Doctor();
        obj.setPassword("secret3");
        assertEquals("secret3", obj.getPassword());
    }

    @Test
    public void testSetPassword_Variant4() {
        Doctor obj = new Doctor();
        obj.setPassword("secret4");
        assertEquals("secret4", obj.getPassword());
    }

    @Test
    public void testSetSpec_Variant1() {
        Doctor obj = new Doctor();
        obj.setSpec("Pediatrics");
        assertEquals("Pediatrics", obj.getSpec());
    }

    @Test
    public void testSetSpec_Variant2() {
        Doctor obj = new Doctor();
        obj.setSpec("Cardiology");
        assertEquals("Cardiology", obj.getSpec());
    }

    @Test
    public void testSetSpec_Variant3() {
        Doctor obj = new Doctor();
        obj.setSpec("Neurology");
        assertEquals("Neurology", obj.getSpec());
    }

    @Test
    public void testSetSpec_Variant4() {
        Doctor obj = new Doctor();
        obj.setSpec("Orthopedics");
        assertEquals("Orthopedics", obj.getSpec());
    }

    @Test
    public void testSetDocfees_Variant1() {
        Doctor obj = new Doctor();
        obj.setDocFees(300);
        assertEquals(300, obj.getDocFees());
    }

    @Test
    public void testSetDocfees_Variant2() {
        Doctor obj = new Doctor();
        obj.setDocFees(400);
        assertEquals(400, obj.getDocFees());
    }

    @Test
    public void testSetDocfees_Variant3() {
        Doctor obj = new Doctor();
        obj.setDocFees(500);
        assertEquals(500, obj.getDocFees());
    }

    @Test
    public void testSetDocfees_Variant4() {
        Doctor obj = new Doctor();
        obj.setDocFees(600);
        assertEquals(600, obj.getDocFees());
    }
}
