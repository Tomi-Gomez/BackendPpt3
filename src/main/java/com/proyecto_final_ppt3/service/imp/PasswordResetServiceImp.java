package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Model.PasswordResetToken;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.Repository.PasswordResetTokenRepository;
import com.proyecto_final_ppt3.service.EmailService;
import com.proyecto_final_ppt3.service.PasswordResetService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetServiceImp implements PasswordResetService {

    private final PacienteRepository pacienteRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetServiceImp(PacienteRepository pacienteRepository,
                                   PasswordResetTokenRepository tokenRepository,
                                   EmailService emailService,
                                   PasswordEncoder passwordEncoder) {
        this.pacienteRepository = pacienteRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createPasswordResetTokenForEmail(String email) {
        Optional<Paciente> pacienteOpt = pacienteRepository.findByEmail(email);

        if (pacienteOpt.isEmpty()) {
            // No revelar si existe o no → retornamos igual
            return;
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

        // Enviar email real
        emailService.sendPasswordResetEmail(paciente.getEmail(), token);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
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
    }
}
