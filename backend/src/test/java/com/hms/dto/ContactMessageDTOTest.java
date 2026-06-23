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

}