package com.proyecto_final_ppt3.service;

public interface EmailService {
    void sendPasswordResetEmail(String to, String token);
}
