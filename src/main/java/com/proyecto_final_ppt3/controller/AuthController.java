package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Model.PasswordResetToken;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.Repository.PasswordResetTokenRepository;
import com.proyecto_final_ppt3.controller.request.ForgotPasswordRequest;
import com.proyecto_final_ppt3.controller.request.ResetPasswordRequest;
import com.proyecto_final_ppt3.controller.response.AuthResponse;
import com.proyecto_final_ppt3.service.PasswordResetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PacienteRepository pacienteRepository;
    private final PasswordResetService passwordResetService;

    @PostMapping("/forgot-password")
    public ResponseEntity<AuthResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        AuthResponse response = passwordResetService.createPasswordResetTokenForEmail(request.getEmail());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<AuthResponse> resetPassword(
            @RequestParam String token,
            @RequestBody ResetPasswordRequest request
    ) {
        AuthResponse response = passwordResetService.resetPassword(token, request.getPassword());

        return ResponseEntity.ok(response);
    }

}

