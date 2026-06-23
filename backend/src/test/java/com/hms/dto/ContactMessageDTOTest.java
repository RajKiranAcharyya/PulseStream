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

}