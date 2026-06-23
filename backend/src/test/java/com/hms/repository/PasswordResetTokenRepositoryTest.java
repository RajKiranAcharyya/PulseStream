package com.hms.repository;

import com.hms.entity.PasswordResetToken;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PasswordResetTokenRepositoryTest {

    @Test
    public void testFindByToken() {
        PasswordResetTokenRepository repo = mock(PasswordResetTokenRepository.class);
        PasswordResetToken token = new PasswordResetToken("token-123", "user@gmail.com", 15);
        when(repo.findByToken("token-123")).thenReturn(Optional.of(token));

        Optional<PasswordResetToken> result = repo.findByToken("token-123");
        assertTrue(result.isPresent());
        assertEquals("user@gmail.com", result.get().getEmail());
    }

    @Test
    public void testDeleteByEmail() {
        PasswordResetTokenRepository repo = mock(PasswordResetTokenRepository.class);
        doNothing().when(repo).deleteByEmail("user@gmail.com");

        repo.deleteByEmail("user@gmail.com");
        verify(repo, times(1)).deleteByEmail("user@gmail.com");
    }

}