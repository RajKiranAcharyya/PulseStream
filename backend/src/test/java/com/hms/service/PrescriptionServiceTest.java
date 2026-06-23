package com.hms.service;

import com.hms.entity.Prescription;
import com.hms.repository.PrescriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrescriptionServiceTest {

    @Mock
    private PrescriptionRepository prescriptionRepository;

    @InjectMocks
    private PrescriptionService prescriptionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePrescription() {
        Prescription prescription = new Prescription(null, false, 1L, "Dr. Smith", 2L, "Jane", "Doe", null, null, "Flu", "Meds");
        Prescription savedPrescription = new Prescription(10L, false, 1L, "Dr. Smith", 2L, "Jane", "Doe", null, null, "Flu", "Meds");
        when(prescriptionRepository.save(prescription)).thenReturn(savedPrescription);

        Prescription result = prescriptionService.savePrescription(prescription);
        assertNotNull(result);
        assertEquals(10L, result.getId());
        verify(prescriptionRepository, times(1)).save(prescription);
    }

    @Test
    public void testGetPatientPrescriptions() {
        Prescription p = new Prescription(10L, false, 1L, "Dr. Smith", 2L, "Jane", "Doe", null, null, "Flu", "Meds");
        when(prescriptionRepository.findByPid(2L)).thenReturn(Arrays.asList(p));

        List<Prescription> result = prescriptionService.getPatientPrescriptions(2L);
        assertEquals(1, result.size());
        assertEquals(2L, result.get(0).getPid());
    }

}