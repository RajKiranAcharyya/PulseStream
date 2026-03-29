package com.hms.service;

import com.hms.entity.Prescription;
import com.hms.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public List<Prescription> getPatientPrescriptions(Long pid) {
        return prescriptionRepository.findByPid(pid);
    }

    public List<Prescription> getDoctorPrescriptions(String doctorName) {
        return prescriptionRepository.findByDoctor(doctorName);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }
}
