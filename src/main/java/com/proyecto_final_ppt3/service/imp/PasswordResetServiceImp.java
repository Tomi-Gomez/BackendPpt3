package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Model.PasswordResetToken;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.Repository.PasswordResetTokenRepository;
import com.proyecto_final_ppt3.controller.response.AuthResponse;
import com.proyecto_final_ppt3.handler.PacienteNotFoundException;
import com.proyecto_final_ppt3.service.EmailService;
import com.proyecto_final_ppt3.service.PasswordResetService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PasswordResetServiceImp implements PasswordResetService {

    private final PacienteRepository pacienteRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse createPasswordResetTokenForEmail(String email) {
        Optional<Paciente> pacienteOpt = pacienteRepository.findByEmail(email);

        if (pacienteOpt.isEmpty()) {
            throw new PacienteNotFoundException("No se encontro el paciente con el email.");
        }

        Paciente paciente = pacienteOpt.get();
        // Generar token único
        String token = UUID.randomUUID().toString();
        // Construir y guardar token
        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(token)
                .expiryDate(LocalDateTime.now().plusMinutes(30))
                .paciente(paciente)
                .build();
        tokenRepository.save(resetToken);
        emailService.sendPasswordResetEmail(paciente.getEmail(), token);

        return new AuthResponse("Si el email está registrado, se enviaron las instrucciones");
    }

    @Override
    public AuthResponse resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("El token no existe o ya fue usado."));
        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
             throw new IllegalArgumentException("El token ha expirado.");
        }
        Paciente paciente = resetToken.getPaciente();
        paciente.setContrasenia(passwordEncoder.encode(newPassword));
        pacienteRepository.save(paciente);
        // Borrar token después de usarlo
        tokenRepository.delete(resetToken);

        return new AuthResponse("Contraseña restablecida correctamente");
    }
}
