package com.hms.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    @Test
    public void testDefaultConstructor() {
        Admin admin = new Admin();
        assertNull(admin.getUsername());
        assertNull(admin.getPassword());
        assertNull(admin.getEmail());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        Admin admin = new Admin("admin1", "pass123", "admin1@hms.com");
        assertEquals("admin1", admin.getUsername());
        assertEquals("pass123", admin.getPassword());
        assertEquals("admin1@hms.com", admin.getEmail());
    }

    @Test
    public void testSetters() {
        Admin admin = new Admin();
        admin.setUsername("newAdmin");
        admin.setPassword("newPass");
        admin.setEmail("new@hms.com");

        assertEquals("newAdmin", admin.getUsername());
        assertEquals("newPass", admin.getPassword());
        assertEquals("new@hms.com", admin.getEmail());
    }

    @Test
    public void testSetUsername_Variant1() {
        Admin obj = new Admin();
        obj.setUsername("admin_user");
        assertEquals("admin_user", obj.getUsername());
    }

    @Test
    public void testSetUsername_Variant2() {
        Admin obj = new Admin();
        obj.setUsername("sysop");
        assertEquals("sysop", obj.getUsername());
    }

    @Test
    public void testSetUsername_Variant3() {
        Admin obj = new Admin();
        obj.setUsername("manager");
        assertEquals("manager", obj.getUsername());
    }

    @Test
    public void testSetUsername_Variant4() {
        Admin obj = new Admin();
        obj.setUsername("staff_admin");
        assertEquals("staff_admin", obj.getUsername());
    }

    @Test
    public void testSetPassword_Variant1() {
        Admin obj = new Admin();
        obj.setPassword("securePass1");
        assertEquals("securePass1", obj.getPassword());
    }

    @Test
    public void testSetPassword_Variant2() {
        Admin obj = new Admin();
        obj.setPassword("securePass2");
        assertEquals("securePass2", obj.getPassword());
    }

    @Test
    public void testSetPassword_Variant3() {
        Admin obj = new Admin();
        obj.setPassword("securePass3");
        assertEquals("securePass3", obj.getPassword());
    }
}
