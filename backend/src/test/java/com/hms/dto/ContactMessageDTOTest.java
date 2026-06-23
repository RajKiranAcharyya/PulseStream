package com.hms.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactMessageDTOTest {

    @Test
    public void testNoArgsConstructor() {
        ContactMessageDTO dto = new ContactMessageDTO();
        assertNull(dto.getName());
        assertNull(dto.getEmail());
    }

    @Test
    public void testAllArgsConstructorAndGetters() {
        ContactMessageDTO dto = new ContactMessageDTO("Alice", "alice@gmail.com", "123", "Hello");

        assertEquals("Alice", dto.getName());
        assertEquals("alice@gmail.com", dto.getEmail());
        assertEquals("123", dto.getContact());
        assertEquals("Hello", dto.getMessage());
    }

    @Test
    public void testSetters() {
        ContactMessageDTO dto = new ContactMessageDTO();
        dto.setName("Bob");

        assertEquals("Bob", dto.getName());
    }

    @Test
    public void testEqualsAndHashCode() {
        ContactMessageDTO dto1 = new ContactMessageDTO("Alice", "alice@gmail.com", "123", "Hello");
        ContactMessageDTO dto2 = new ContactMessageDTO("Alice", "alice@gmail.com", "123", "Hello");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        ContactMessageDTO dto = new ContactMessageDTO();
        assertNotNull(dto.toString());
    }


    @Test
    public void testSetName_Variant1() {
        ContactMessageDTO obj = new ContactMessageDTO();
        obj.setName("User_A");
        assertEquals("User_A", obj.getName());
    }

    @Test
    public void testSetName_Variant2() {
        ContactMessageDTO obj = new ContactMessageDTO();
        obj.setName("User_B");
        assertEquals("User_B", obj.getName());
    }

    @Test
    public void testSetName_Variant3() {
        ContactMessageDTO obj = new ContactMessageDTO();
        obj.setName("User_C");
        assertEquals("User_C", obj.getName());
    }

    @Test
    public void testSetName_Variant4() {
        ContactMessageDTO obj = new ContactMessageDTO();
        obj.setName("User_D");
        assertEquals("User_D", obj.getName());
    }

    @Test
    public void testSetEmail_Variant1() {
        ContactMessageDTO obj = new ContactMessageDTO();
        obj.setEmail("a@gmail.com");
        assertEquals("a@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant2() {
        ContactMessageDTO obj = new ContactMessageDTO();
        obj.setEmail("b@gmail.com");
        assertEquals("b@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant3() {
        ContactMessageDTO obj = new ContactMessageDTO();
        obj.setEmail("c@gmail.com");
        assertEquals("c@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetEmail_Variant4() {
        ContactMessageDTO obj = new ContactMessageDTO();
        obj.setEmail("d@gmail.com");
        assertEquals("d@gmail.com", obj.getEmail());
    }

    @Test
    public void testSetContact_Variant1() {
        ContactMessageDTO obj = new ContactMessageDTO();
        obj.setContact("91111");
        assertEquals("91111", obj.getContact());
    }

    @Test
    public void testSetContact_Variant2() {
        ContactMessageDTO obj = new ContactMessageDTO();
        obj.setContact("92222");
        assertEquals("92222", obj.getContact());
    }

    @Test
    public void testSetContact_Variant3() {
        ContactMessageDTO obj = new ContactMessageDTO();
        obj.setContact("93333");
        assertEquals("93333", obj.getContact());
    }
}