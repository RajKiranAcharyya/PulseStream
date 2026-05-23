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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
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
        // Determine the expected role from the login form's hidden field
        String loginRole = getLoginRoleFromRequest();

        if (loginRole != null) {
            // Role-specific authentication: only search the matching table
            return loadUserByRole(username, loginRole);
        }

        // Fallback for non-form logins (API calls, etc.): search all tables
        return loadUserFromAllTables(username);
    }

    /**
     * Extracts the 'loginRole' parameter from the current HTTP request.
     * Returns null if no request context or parameter is available.
     */
    private String getLoginRoleFromRequest() {
        try {
            ServletRequestAttributes attrs =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                return request.getParameter("loginRole");
            }
        } catch (Exception e) {
            // No request context available (e.g. scheduled tasks) — fall through
        }
        return null;
    }

    /**
     * Role-aware lookup: only searches the database table that matches the
     * portal the user is logging into. This prevents patient credentials
     * from being accepted on the doctor/admin login forms.
     */
    private UserDetails loadUserByRole(String username, String loginRole) {
        switch (loginRole) {
            case "ADMIN": {
                Optional<Admin> admin = adminRepository.findById(username);
                if (admin.isPresent()) {
                    return new CustomUserDetails(
                            admin.get().getUsername(), admin.get().getPassword(), "ROLE_ADMIN");
                }
                throw new UsernameNotFoundException("Invalid admin credentials");
            }
            case "DOCTOR": {
                Optional<Doctor> doctor = doctorRepository.findById(username);
                if (!doctor.isPresent()) {
                    doctor = doctorRepository.findByUsername(username);
                }
                if (doctor.isPresent()) {
                    return new CustomUserDetails(
                            doctor.get().getEmail(), doctor.get().getPassword(), "ROLE_DOCTOR");
                }
                throw new UsernameNotFoundException("Invalid doctor credentials");
            }
            case "PATIENT": {
                Optional<Patient> patient = patientRepository.findByEmail(username);
                if (patient.isPresent()) {
                    return new CustomUserDetails(
                            patient.get().getEmail(), patient.get().getPassword(), "ROLE_PATIENT");
                }
                throw new UsernameNotFoundException("Invalid patient credentials");
            }
            default:
                throw new UsernameNotFoundException("Unknown login role: " + loginRole);
        }
    }

    /**
     * Legacy fallback: searches all tables sequentially.
     * Used only when no loginRole parameter is present (e.g. programmatic auth).
     */
    private UserDetails loadUserFromAllTables(String username) {
        // Check Admin
        Optional<Admin> admin = adminRepository.findById(username);
        if (admin.isPresent()) {
            return new CustomUserDetails(admin.get().getUsername(), admin.get().getPassword(), "ROLE_ADMIN");
        }

        // Check Doctor (by email or username)
        Optional<Doctor> doctor = doctorRepository.findById(username);
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
