package com.hms.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordResetTokenTest {

    @Test
    public void testDefaultConstructor() {
        PasswordResetToken token = new PasswordResetToken();
        assertNull(token.getId());
        assertNull(token.getToken());
        assertNull(token.getEmail());
        assertNull(token.getExpiryDate());
    }

    @Test
    public void testCustomConstructorAndExpiry() {
        PasswordResetToken token = new PasswordResetToken("test-token-123", "test@gmail.com", 15);

        assertEquals("test-token-123", token.getToken());
        assertEquals("test@gmail.com", token.getEmail());
        assertNotNull(token.getExpiryDate());
        assertFalse(token.isExpired());
    }

    @Test
    public void testExpiredToken() {
        PasswordResetToken token = new PasswordResetToken("test-token-123", "test@gmail.com", -5); // created 5 minutes ago and expired
        assertTrue(token.isExpired());
    }

    @Test
    public void testSetters() {
        PasswordResetToken token = new PasswordResetToken();
        token.setId(10L);
        token.setToken("token-abc");
        token.setEmail("new@gmail.com");
        LocalDateTime now = LocalDateTime.now();
        token.setExpiryDate(now);

        assertEquals(10L, token.getId());
        assertEquals("token-abc", token.getToken());
        assertEquals("new@gmail.com", token.getEmail());
        assertEquals(now, token.getExpiryDate());
    }
}
