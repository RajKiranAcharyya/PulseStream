package com.hms.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactMessageTest {

    @Test
    public void testNoArgsConstructor() {
        ContactMessage message = new ContactMessage();
        assertNull(message.getId());
        assertFalse(message.isDeleted());
        assertNull(message.getName());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        ContactMessage message = new ContactMessage(1L, false, "Alice", "alice@gmail.com", "0987654321", "Hello there!");

        assertEquals(1L, message.getId());
        assertFalse(message.isDeleted());
        assertEquals("Alice", message.getName());
        assertEquals("alice@gmail.com", message.getEmail());
        assertEquals("0987654321", message.getContact());
        assertEquals("Hello there!", message.getMessage());
    }

    @Test
    public void testSettersAndEquality() {
        ContactMessage m1 = new ContactMessage();
        m1.setId(10L);
        m1.setName("Bob");
        m1.setDeleted(true);

        assertEquals(10L, m1.getId());
        assertEquals("Bob", m1.getName());
        assertTrue(m1.isDeleted());

        ContactMessage m2 = new ContactMessage();
        m2.setId(10L);
        m2.setName("Bob");
        m2.setDeleted(true);

        assertEquals(m1, m2);
        assertEquals(m1.hashCode(), m2.hashCode());
        assertNotNull(m1.toString());
    }

    @Test
    public void testSetName_Variant1() {
        ContactMessage obj = new ContactMessage();
        obj.setName("User_A");
        assertEquals("User_A", obj.getName());
    }

    @Test
    public void testSetName_Variant2() {
        ContactMessage obj = new ContactMessage();
        obj.setName("User_B");
        assertEquals("User_B", obj.getName());
    }

    @Test
    public void testSetName_Variant3() {
        ContactMessage obj = new ContactMessage();
        obj.setName("User_C");
        assertEquals("User_C", obj.getName());
    }

    @Test
    public void testSetName_Variant4() {
        ContactMessage obj = new ContactMessage();
        obj.setName("User_D");
        assertEquals("User_D", obj.getName());
    }

    @Test
    public void testSetEmail_Variant1() {
        ContactMessage obj = new ContactMessage();
        obj.setEmail("u_a@gmail.com");
        assertEquals("u_a@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant2() {
        ContactMessage obj = new ContactMessage();
        obj.setEmail("u_b@gmail.com");
        assertEquals("u_b@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant3() {
        ContactMessage obj = new ContactMessage();
        obj.setEmail("u_c@gmail.com");
        assertEquals("u_c@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant4() {
        ContactMessage obj = new ContactMessage();
        obj.setEmail("u_d@gmail.com");
        assertEquals("u_d@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetContact_Variant1() {
        ContactMessage obj = new ContactMessage();
        obj.setContact("90001");
        assertEquals("90001", obj.getContact());
    }

    @Test
    public void testSetContact_Variant2() {
        ContactMessage obj = new ContactMessage();
        obj.setContact("90002");
        assertEquals("90002", obj.getContact());
    }

    @Test
    public void testSetContact_Variant3() {
        ContactMessage obj = new ContactMessage();
        obj.setContact("90003");
        assertEquals("90003", obj.getContact());
    }

    @Test
    public void testSetContact_Variant4() {
        ContactMessage obj = new ContactMessage();
        obj.setContact("90004");
        assertEquals("90004", obj.getContact());
    }

    @Test
    public void testSetMessage_Variant1() {
        ContactMessage obj = new ContactMessage();
        obj.setMessage("Inquiry 1");
        assertEquals("Inquiry 1", obj.getMessage());
    }

    @Test
    public void testSetMessage_Variant2() {
        ContactMessage obj = new ContactMessage();
        obj.setMessage("Inquiry 2");
        assertEquals("Inquiry 2", obj.getMessage());
    }

    @Test
    public void testSetMessage_Variant3() {
        ContactMessage obj = new ContactMessage();
        obj.setMessage("Inquiry 3");
        assertEquals("Inquiry 3", obj.getMessage());
    }

    @Test
    public void testSetMessage_Variant4() {
        ContactMessage obj = new ContactMessage();
        obj.setMessage("Inquiry 4");
        assertEquals("Inquiry 4", obj.getMessage());
    }
}
