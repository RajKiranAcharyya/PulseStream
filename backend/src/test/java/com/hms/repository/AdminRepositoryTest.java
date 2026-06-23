package com.hms.repository;

import com.hms.entity.Admin;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminRepositoryTest {

    @Test
    public void testFindByEmail() {
        AdminRepository repo = mock(AdminRepository.class);
        Admin admin = new Admin("admin", "pass", "admin@hms.com");
        when(repo.findByEmail("admin@hms.com")).thenReturn(Optional.of(admin));

        Optional<Admin> result = repo.findByEmail("admin@hms.com");
        assertTrue(result.isPresent());
        assertEquals("admin", result.get().getUsername());
    }

}