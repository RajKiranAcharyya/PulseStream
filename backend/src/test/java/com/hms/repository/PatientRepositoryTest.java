package com.hms.repository;

import com.hms.entity.Patient;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatientRepositoryTest {

    @Test
    public void testFindByEmail() {
        PatientRepository repo = mock(PatientRepository.class);
        Patient patient = new Patient();
        patient.setEmail("patient@gmail.com");
        when(repo.findByEmail("patient@gmail.com")).thenReturn(Optional.of(patient));

        Optional<Patient> result = repo.findByEmail("patient@gmail.com");
        assertTrue(result.isPresent());
        assertEquals("patient@gmail.com", result.get().getEmail());
    }

}