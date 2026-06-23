package com.hms.repository;

import com.hms.entity.Availability;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AvailabilityRepositoryTest {

    @Test
    public void testFindByDoctorEmail() {
        AvailabilityRepository repo = mock(AvailabilityRepository.class);
        Availability av = new Availability();
        av.setDoctorEmail("doc@hms.com");
        when(repo.findByDoctorEmail("doc@hms.com")).thenReturn(Arrays.asList(av));

        List<Availability> result = repo.findByDoctorEmail("doc@hms.com");
        assertEquals(1, result.size());
        assertEquals("doc@hms.com", result.get(0).getDoctorEmail());
    }

}