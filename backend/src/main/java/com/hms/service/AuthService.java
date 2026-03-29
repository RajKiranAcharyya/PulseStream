package com.hms.service;

import com.hms.entity.PasswordResetToken;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import com.hms.repository.PasswordResetTokenRepository;
import com.hms.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public String createResetToken(String email) {
        // Check if user exists in any table
        boolean exists = adminRepository.existsByEmail(email) ||
                doctorRepository.existsByEmail(email) ||
                patientRepository.existsByEmail(email);

        if (!exists)
            return "Email not found";

        tokenRepository.deleteByEmail(email); // Remove old tokens
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(token, email, 15);
        tokenRepository.save(resetToken);

        String resetLink = "http://localhost:8080/reset_password.html?token=" + token;
        emailService.sendEmail(email, "PulseStream - Password Reset Request",
                "Hello! Click the link below to reset your password. This link expires in 15 minutes.\n\n" + resetLink);

        return "Success";
    }

    public Optional<String> validateToken(String token) {
        return tokenRepository.findByToken(token)
                .filter(t -> !t.isExpired())
                .map(PasswordResetToken::getEmail);
    }

    @Transactional
    public boolean resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isEmpty() || tokenOpt.get().isExpired())
            return false;

        String email = tokenOpt.get().getEmail();
        String encodedPassword = passwordEncoder.encode(newPassword);

        // Update in all possible tables (since roles share emails)
        adminRepository.findByEmail(email).ifPresent(u -> {
            u.setPassword(encodedPassword);
            adminRepository.save(u);
        });
        doctorRepository.findByEmail(email).ifPresent(d -> {
            d.setPassword(encodedPassword);
            doctorRepository.save(d);
        });
        patientRepository.findByEmail(email).ifPresent(p -> {
            p.setPassword(encodedPassword);
            patientRepository.save(p);
        });

        tokenRepository.deleteByEmail(email);
        return true;
    }
}
