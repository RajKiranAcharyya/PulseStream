package com.hms.repository;

import com.hms.entity.Prescription;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrescriptionRepositoryTest {

    @Test
    public void testFindByPid() {
        PrescriptionRepository repo = mock(PrescriptionRepository.class);
        Prescription p = new Prescription();
        p.setPid(1L);
        when(repo.findByPid(1L)).thenReturn(Arrays.asList(p));

        List<Prescription> result = repo.findByPid(1L);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getPid());
    }

    @Test
    public void testFindByDoctor() {
        PrescriptionRepository repo = mock(PrescriptionRepository.class);
        Prescription p = new Prescription();
        p.setDoctor("Dr. Smith");
        when(repo.findByDoctor("Dr. Smith")).thenReturn(Arrays.asList(p));

        List<Prescription> result = repo.findByDoctor("Dr. Smith");
        assertEquals(1, result.size());
        assertEquals("Dr. Smith", result.get(0).getDoctor());
    }

}