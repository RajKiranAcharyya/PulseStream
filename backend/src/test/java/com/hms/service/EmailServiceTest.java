package com.hms.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendEmail_MailSenderNull() {
        // Set mailSender to null via reflection or manual instantiation
        EmailService serviceNoMail = new EmailService();
        serviceNoMail.sendEmail("to@gmail.com", "Subject", "Body");
        // Should print to console, no exceptions thrown
    }

    @Test
    public void testSendEmail_Success() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("to@gmail.com");
        message.setSubject("Subject");
        message.setText("Body");
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        emailService.sendEmail("to@gmail.com", "Subject", "Body");
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testSendEmail_Failure() {
        doThrow(new RuntimeException("Mail server down")).when(mailSender).send(any(SimpleMailMessage.class));
        emailService.sendEmail("to@gmail.com", "Subject", "Body");
        // Should catch the exception and print error, no propagation
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

}