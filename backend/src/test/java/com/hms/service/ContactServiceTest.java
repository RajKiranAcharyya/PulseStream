package com.hms.service;

import com.hms.entity.ContactMessage;
import com.hms.repository.ContactMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContactServiceTest {

    @Mock
    private ContactMessageRepository contactMessageRepository;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveMessage() {
        ContactMessage msg = new ContactMessage(null, false, "Alice", "alice@gmail.com", "12345", "Hi!");
        ContactMessage savedMsg = new ContactMessage(1L, false, "Alice", "alice@gmail.com", "12345", "Hi!");
        when(contactMessageRepository.save(msg)).thenReturn(savedMsg);

        ContactMessage result = contactService.saveMessage(msg);
        assertNotNull(result.getId());
        assertEquals(1L, result.getId());
        assertEquals("Alice", result.getName());
    }

    @Test
    public void testGetAllMessages() {
        ContactMessage msg1 = new ContactMessage(1L, false, "Alice", "alice@gmail.com", "12345", "Hi!");
        ContactMessage msg2 = new ContactMessage(2L, false, "Bob", "bob@gmail.com", "67890", "Hello!");
        when(contactMessageRepository.findAll()).thenReturn(Arrays.asList(msg1, msg2));

        List<ContactMessage> result = contactService.getAllMessages();
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());
    }

}