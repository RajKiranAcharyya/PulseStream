package com.hms.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    @Test
    public void testNoArgsConstructor() {
        Appointment appointment = new Appointment();
        assertNull(appointment.getId());
        assertFalse(appointment.isDeleted());
        assertNull(appointment.getPid());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(10, 0);
        Appointment appointment = new Appointment(
                1L, false, 2L, "John", "Doe", "Male", "john@gmail.com",
                "1234567890", "Dr. Smith", 500, 30, "Flu", date, time, 1, 1
        );

        assertEquals(1L, appointment.getId());
        assertFalse(appointment.isDeleted());
        assertEquals(2L, appointment.getPid());
        assertEquals("John", appointment.getFname());
        assertEquals("Doe", appointment.getLname());
        assertEquals("Male", appointment.getGender());
        assertEquals("john@gmail.com", appointment.getEmail());
        assertEquals("1234567890", appointment.getContact());
        assertEquals("Dr. Smith", appointment.getDoctor());
        assertEquals(500, appointment.getDocFees());
        assertEquals(30, appointment.getAge());
        assertEquals("Flu", appointment.getDisease());
        assertEquals(date, appointment.getAppdate());
        assertEquals(time, appointment.getApptime());
        assertEquals(1, appointment.getUserStatus());
        assertEquals(1, appointment.getDoctorStatus());
    }

    @Test
    public void testSettersAndToStringAndEquals() {
        Appointment app1 = new Appointment();
        app1.setId(10L);
        app1.setDeleted(true);
        app1.setFname("Jane");

        assertEquals(10L, app1.getId());
        assertTrue(app1.isDeleted());
        assertEquals("Jane", app1.getFname());

        Appointment app2 = new Appointment();
        app2.setId(10L);
        app2.setDeleted(true);
        app2.setFname("Jane");

        assertEquals(app1, app2);
        assertEquals(app1.hashCode(), app2.hashCode());
        assertNotNull(app1.toString());
    }

    @Test
    public void testSetFname_Variant1() {
        Appointment obj = new Appointment();
        obj.setFname("David");
        assertEquals("David", obj.getFname());
    }

    @Test
    public void testSetFname_Variant2() {
        Appointment obj = new Appointment();
        obj.setFname("James");
        assertEquals("James", obj.getFname());
    }

    @Test
    public void testSetFname_Variant3() {
        Appointment obj = new Appointment();
        obj.setFname("Robert");
        assertEquals("Robert", obj.getFname());
    }

    @Test
    public void testSetFname_Variant4() {
        Appointment obj = new Appointment();
        obj.setFname("William");
        assertEquals("William", obj.getFname());
    }

    @Test
    public void testSetLname_Variant1() {
        Appointment obj = new Appointment();
        obj.setLname("Taylor");
        assertEquals("Taylor", obj.getLname());
    }

    @Test
    public void testSetLname_Variant2() {
        Appointment obj = new Appointment();
        obj.setLname("Brown");
        assertEquals("Brown", obj.getLname());
    }

    @Test
    public void testSetLname_Variant3() {
        Appointment obj = new Appointment();
        obj.setLname("Wilson");
        assertEquals("Wilson", obj.getLname());
    }

    @Test
    public void testSetLname_Variant4() {
        Appointment obj = new Appointment();
        obj.setLname("Thomas");
        assertEquals("Thomas", obj.getLname());
    }

    @Test
    public void testSetGender_Variant1() {
        Appointment obj = new Appointment();
        obj.setGender("Male");
        assertEquals("Male", obj.getGender());
    }

    @Test
    public void testSetGender_Variant2() {
        Appointment obj = new Appointment();
        obj.setGender("Female");
        assertEquals("Female", obj.getGender());
    }

    @Test
    public void testSetGender_Variant3() {
        Appointment obj = new Appointment();
        obj.setGender("Other");
        assertEquals("Other", obj.getGender());
    }

    @Test
    public void testSetGender_Variant4() {
        Appointment obj = new Appointment();
        obj.setGender("Unknown");
        assertEquals("Unknown", obj.getGender());
    }

    @Test
    public void testSetEmail_Variant1() {
        Appointment obj = new Appointment();
        obj.setEmail("p_david@gmail.com");
        assertEquals("p_david@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant2() {
        Appointment obj = new Appointment();
        obj.setEmail("p_james@gmail.com");
        assertEquals("p_james@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant3() {
        Appointment obj = new Appointment();
        obj.setEmail("p_robert@gmail.com");
        assertEquals("p_robert@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant4() {
        Appointment obj = new Appointment();
        obj.setEmail("p_william@gmail.com");
        assertEquals("p_william@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetContact_Variant1() {
        Appointment obj = new Appointment();
        obj.setContact("98765");
        assertEquals("98765", obj.getContact());
    }

    @Test
    public void testSetContact_Variant2() {
        Appointment obj = new Appointment();
        obj.setContact("87654");
        assertEquals("87654", obj.getContact());
    }

    @Test
    public void testSetContact_Variant3() {
        Appointment obj = new Appointment();
        obj.setContact("76543");
        assertEquals("76543", obj.getContact());
    }

    @Test
    public void testSetContact_Variant4() {
        Appointment obj = new Appointment();
        obj.setContact("65432");
        assertEquals("65432", obj.getContact());
    }

    @Test
    public void testSetDoctor_Variant1() {
        Appointment obj = new Appointment();
        obj.setDoctor("Dr. Smith");
        assertEquals("Dr. Smith", obj.getDoctor());
    }

    @Test
    public void testSetDoctor_Variant2() {
        Appointment obj = new Appointment();
        obj.setDoctor("Dr. Jones");
        assertEquals("Dr. Jones", obj.getDoctor());
    }

    @Test
    public void testSetDoctor_Variant3() {
        Appointment obj = new Appointment();
        obj.setDoctor("Dr. Taylor");
        assertEquals("Dr. Taylor", obj.getDoctor());
    }

    @Test
    public void testSetDoctor_Variant4() {
        Appointment obj = new Appointment();
        obj.setDoctor("Dr. Brown");
        assertEquals("Dr. Brown", obj.getDoctor());
    }
}
