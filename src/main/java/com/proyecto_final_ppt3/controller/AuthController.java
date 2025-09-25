package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Model.PasswordResetToken;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.Repository.PasswordResetTokenRepository;
import com.proyecto_final_ppt3.service.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PacienteRepository pacienteRepository;
    private final PasswordResetService passwordResetService;

    public AuthController(PacienteRepository pacienteRepository,
                          PasswordResetService passwordResetService) {
        this.pacienteRepository = pacienteRepository;
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, String>> forgotPassword(@RequestBody Map<String, String> request) {
        passwordResetService.createPasswordResetTokenForEmail(request.get("email"));

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Si el email está registrado, se enviaron las instrucciones");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestParam String token,
                                                        @RequestBody Map<String, String> request) {
    passwordResetService.resetPassword(token, request.get("password"));

    Map<String, String> response = new HashMap<>();
    response.put("mensaje", "Contraseña restablecida correctamente");

    return ResponseEntity.ok(response);
}
}

