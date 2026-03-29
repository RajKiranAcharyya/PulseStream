package com.hms.service;

import com.hms.entity.Availability;
import com.hms.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AvailabilityService {
    @Autowired
    private AvailabilityRepository availabilityRepository;

    public List<Availability> getDoctorAvailability(String email) {
        return availabilityRepository.findByDoctorEmail(email);
    }

    public Availability addAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    public void removeAvailability(Long id) {
        availabilityRepository.deleteById(id);
    }
}
