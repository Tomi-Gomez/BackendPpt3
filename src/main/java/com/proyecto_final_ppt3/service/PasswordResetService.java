package com.proyecto_final_ppt3.service;

public interface PasswordResetService {
    void createPasswordResetTokenForEmail(String email);
    void resetPassword(String token, String newPassword);
}
