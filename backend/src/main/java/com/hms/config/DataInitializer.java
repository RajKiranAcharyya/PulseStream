package com.hms.config;

import com.hms.entity.Admin;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.repository.AdminRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize Admin if not exists
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            adminRepository.save(admin);
        } else {
            // Encode admin password if it's plain text (admin123 is 8 chars, BCrypt is 60)
            adminRepository.findAll().forEach(a -> {
                if (a.getPassword().length() < 30) {
                    a.setPassword(passwordEncoder.encode(a.getPassword()));
                    adminRepository.save(a);
                }
            });
        }

        // Encode Doctor passwords if plain text
        doctorRepository.findAll().forEach(d -> {
            if (d.getPassword().length() < 30) {
                d.setPassword(passwordEncoder.encode(d.getPassword()));
                doctorRepository.save(d);
            }
        });

        // Encode Patient passwords if plain text
        patientRepository.findAll().forEach(p -> {
            if (p.getPassword().length() < 30) {
                p.setPassword(passwordEncoder.encode(p.getPassword()));
                patientRepository.save(p);
            }
        });
    }
}
