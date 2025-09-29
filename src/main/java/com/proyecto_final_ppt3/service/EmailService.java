package com.proyecto_final_ppt3.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendPasswordResetEmail(String to, String token);
    void sendTurnoPdfEmail(String to, byte[] pdfBytes, String fileName) throws MessagingException;
}
