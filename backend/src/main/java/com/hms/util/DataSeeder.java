package com.hms.util;

import com.hms.entity.Availability;
import com.hms.entity.Doctor;
import com.hms.repository.AvailabilityRepository;
import com.hms.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class DataSeeder {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void seedData() {
        List<Doctor> doctors = doctorRepository.findAll();
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        
        for (Doctor d : doctors) {
            List<Availability> existing = availabilityRepository.findByDoctorEmail(d.getEmail());
            if (existing.isEmpty()) {
                for (String day : days) {
                    for (int h = 9; h <= 11; h++) {
                        Availability a = new Availability();
                        a.setDoctorEmail(d.getEmail());
                        a.setDayOfWeek(day);
                        a.setStartTime(LocalTime.of(h, 0));
                        a.setEndTime(LocalTime.of(h, 45));
                        availabilityRepository.save(a);
                    }
                }
                System.out.println("PulseStream: Seeded 3 slots per day for doctor: " + d.getUsername());
            }
        }
    }
}