package com.hms.repository;

import com.hms.entity.Doctor;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DoctorRepositoryTest {

    @Test
    public void testFindByUsername() {
        DoctorRepository repo = mock(DoctorRepository.class);
        Doctor doctor = new Doctor();
        doctor.setUsername("dr_smith");
        when(repo.findByUsername("dr_smith")).thenReturn(Optional.of(doctor));

        Optional<Doctor> result = repo.findByUsername("dr_smith");
        assertTrue(result.isPresent());
        assertEquals("dr_smith", result.get().getUsername());
    }

    @Test
    public void testExistsByEmail() {
        DoctorRepository repo = mock(DoctorRepository.class);
        when(repo.existsByEmail("smith@gmail.com")).thenReturn(true);

        assertTrue(repo.existsByEmail("smith@gmail.com"));
    }

}