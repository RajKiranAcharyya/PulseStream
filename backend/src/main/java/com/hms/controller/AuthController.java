package com.hms.controller;

import com.hms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String result = authService.createResetToken(email);
        if ("Success".equals(result)) {
            return ResponseEntity.ok("Reset link sent to your email.");
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String password = request.get("password");
        if (authService.resetPassword(token, password)) {
            return ResponseEntity.ok("Password updated successfully.");
        }
        return ResponseEntity.badRequest().body("Invalid or expired token.");
    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        return authService.validateToken(token)
                .map(email -> ResponseEntity.ok().build())
                .orElse(ResponseEntity.badRequest().build());
    }
}
