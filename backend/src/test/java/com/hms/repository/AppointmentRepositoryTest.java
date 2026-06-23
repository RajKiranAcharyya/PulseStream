package com.hms.repository;

import com.hms.entity.Appointment;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentRepositoryTest {

    @Test
    public void testFindOccupiedTimes() {
        AppointmentRepository repo = mock(AppointmentRepository.class);
        LocalDate date = LocalDate.now();
        List<LocalTime> times = Arrays.asList(LocalTime.of(10, 0), LocalTime.of(11, 0));
        when(repo.findOccupiedTimes("Dr. Smith", date)).thenReturn(times);

        List<LocalTime> result = repo.findOccupiedTimes("Dr. Smith", date);
        assertEquals(2, result.size());
        assertTrue(result.contains(LocalTime.of(10, 0)));
    }

}