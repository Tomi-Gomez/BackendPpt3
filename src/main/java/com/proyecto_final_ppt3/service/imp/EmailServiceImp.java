package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
        message.setFrom("notificaciones.uom.clinica@gmail.com");
        message.setTo(to);
        message.setSubject("Recuperación de contraseña");
        message.setText("Haz click en este link para restablecer tu contraseña: " + resetLink);
        mailSender.send(message);
    }

    @Override
    public void sendTurnoPdfEmail(String to, byte[] pdfBytes, String fileName) throws MessagingException {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        helper.setFrom("notificaciones.uom.clinica@gmail.com");
        helper.setTo(to);
        helper.setSubject("Confirmación de Turno Médico");
        helper.setText("Adjunto encontrarás el comprobante de tu turno.");
        helper.addAttachment(fileName, new ByteArrayResource(pdfBytes));

        mailSender.send(mensaje);
    }
}
