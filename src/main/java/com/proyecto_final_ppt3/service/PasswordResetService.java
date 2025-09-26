package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.controller.response.AuthResponse;

public interface PasswordResetService {
    AuthResponse createPasswordResetTokenForEmail(String email);
    AuthResponse resetPassword(String token, String newPassword);
}
