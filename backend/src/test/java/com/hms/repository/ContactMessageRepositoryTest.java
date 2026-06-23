package com.hms.repository;

import com.hms.entity.ContactMessage;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContactMessageRepositoryTest {

    @Test
    public void testFindAll() {
        ContactMessageRepository repo = mock(ContactMessageRepository.class);
        ContactMessage msg = new ContactMessage();
        msg.setName("Alice");
        when(repo.findAll()).thenReturn(Arrays.asList(msg));

        List<ContactMessage> result = repo.findAll();
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
    }

}