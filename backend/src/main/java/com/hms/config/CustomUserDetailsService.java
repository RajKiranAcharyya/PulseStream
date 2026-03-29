package com.hms.config;

import com.hms.entity.Admin;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.repository.AdminRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check Admin
        Optional<Admin> admin = adminRepository.findById(username);
        if (admin.isPresent()) {
            return new CustomUserDetails(admin.get().getUsername(), admin.get().getPassword(), "ROLE_ADMIN");
        }

        // Check Doctor (by email or username)
        Optional<Doctor> doctor = doctorRepository.findById(username); // Try email first
        if (!doctor.isPresent()) {
            doctor = doctorRepository.findByUsername(username);
        }
        if (doctor.isPresent()) {
            return new CustomUserDetails(doctor.get().getEmail(), doctor.get().getPassword(), "ROLE_DOCTOR");
        }
        
        // Check Patient (by email)
        Optional<Patient> patient = patientRepository.findByEmail(username);
        if (patient.isPresent()) {
            return new CustomUserDetails(patient.get().getEmail(), patient.get().getPassword(), "ROLE_PATIENT");
        }

        throw new UsernameNotFoundException("User not found with username/email: " + username);
    }
}
