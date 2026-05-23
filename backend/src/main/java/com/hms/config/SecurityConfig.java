package com.hms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .securityContext(context -> context.securityContextRepository(securityContextRepository()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index.html", "/index1.html", "/services.html", "/contact.html", "/forgot_password.html", "/reset_password.html", "/*.css", "/css/**", "/js/**", "/images/**", "/assets/**", "/fonts/**", "/vendor/**", "/font-awesome/**").permitAll()
                .requestMatchers("/api/public/**", "/api/auth/**").permitAll()
                .requestMatchers("/admin_dashboard.html", "/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/doctor_dashboard.html", "/api/doctor/**").hasRole("DOCTOR")
                .requestMatchers("/patient_dashboard.html", "/api/patient/**").hasRole("PATIENT")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginProcessingUrl("/login")
                .successHandler(roleBasedSuccessHandler())
                .failureHandler(loginFailureHandler())
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index.html")
                .permitAll()
            );

        return http.build();
    }

    /**
     * Redirects authenticated users to their role-appropriate dashboard.
     */
    @Bean
    public AuthenticationSuccessHandler roleBasedSuccessHandler() {
        return (request, response, authentication) -> {
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            switch (role) {
                case "ROLE_ADMIN":
                    response.sendRedirect("/admin_dashboard.html");
                    break;
                case "ROLE_DOCTOR":
                    response.sendRedirect("/doctor_dashboard.html");
                    break;
                case "ROLE_PATIENT":
                    response.sendRedirect("/patient_dashboard.html");
                    break;
                default:
                    response.sendRedirect("/index.html");
            }
        };
    }

    /**
     * Redirects back to the originating login page with an error parameter.
     * Uses the 'loginRole' form field to determine which page to return to.
     */
    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            String loginRole = request.getParameter("loginRole");
            String redirectUrl;

            if ("PATIENT".equals(loginRole)) {
                redirectUrl = "/index1.html?error=true";
            } else {
                // Doctor and Admin login forms are on index.html
                redirectUrl = "/index.html?error=true";
            }

            response.sendRedirect(redirectUrl);
        };
    }
}

