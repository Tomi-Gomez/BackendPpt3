package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Repository.AdministrativoRepository;
import com.proyecto_final_ppt3.Repository.MedicoRespository;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.controller.request.LoginRequest;
import com.proyecto_final_ppt3.controller.response.LoginResponse;
import com.proyecto_final_ppt3.handler.UsuarioNotFoundException;
import com.proyecto_final_ppt3.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private PacienteRepository pacienteRepository;

    private MedicoRespository medicoRespository;

    private AdministrativoRepository administrativoRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<LoginResponse> login(LoginRequest loginRequest) {
        List<LoginResponse> loginResponse;

        switch (loginRequest.getTipoUsuario()) {
            case "Paciente":
                List<Paciente> pacientes = pacienteRepository.findByDni(loginRequest.getDni());
            if (pacientes.isEmpty()) {
                throw new UsuarioNotFoundException("usuario no encontrado");
            }
            Paciente paciente = pacientes.get(0); // tomás el primero
            if (!passwordEncoder.matches(loginRequest.getContra(), paciente.getContrasenia())) {
                throw new IllegalArgumentException("Contraseña incorrecta");
            }
            return List.of(LoginResponse.fromPaciente(paciente));
             
            case "medico":
                List<Medico> medicos = medicoRespository.findByDni(loginRequest.getDni());

                if (medicos.isEmpty()) {
                    throw new UsuarioNotFoundException("usuario no encontrado");
                }

                if (!passwordEncoder.matches(loginRequest.getContra(), medicos.get(0).getContrasenia())) {
                    throw new IllegalArgumentException("Contraseña incorrecta");
                }

                loginResponse = medicos.stream().map(LoginResponse::fromMedico).toList();
                break;
            case "administrativo":
                List<Administrativo> admins = administrativoRepository.findByDni(loginRequest.getDni());
                if (admins.isEmpty()) {
                    throw new UsuarioNotFoundException("usuario no encontrado");
                }
                Administrativo administrativo = admins.get(0);
                if (!passwordEncoder.matches(loginRequest.getContra(), administrativo.getContrasenia())) {
                    throw new IllegalArgumentException("Contraseña incorrecta");
                }
                return List.of(LoginResponse.fromAdministrativo(administrativo));
            
            default:
                return null;
        }

        return loginResponse;
    }

}
