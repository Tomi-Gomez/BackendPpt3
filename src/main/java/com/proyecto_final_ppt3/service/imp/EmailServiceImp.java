package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImp(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendPasswordResetEmail(String to, String token) {
        String resetLink = "http://localhost:4200/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("clinicabeltranavellaneda@outlook.com.ar"); 
        message.setTo(to);
        message.setSubject("Recuperación de contraseña");
        message.setText("Haz click en este link para restablecer tu contraseña: " + resetLink);
        mailSender.send(message);
    }
}
