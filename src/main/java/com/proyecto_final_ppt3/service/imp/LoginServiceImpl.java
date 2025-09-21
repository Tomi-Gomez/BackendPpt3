package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Repository.AdministrativoRepository;
import com.proyecto_final_ppt3.Repository.MedicoRespository;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.controller.request.LoginRequest;
import com.proyecto_final_ppt3.controller.response.LoginResponse;
import com.proyecto_final_ppt3.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private PacienteRepository pacienteRepository;

    private MedicoRespository medicoRespository;

    private AdministrativoRepository administrativoRepository;

    @Override
    public List<LoginResponse> login(LoginRequest loginRequest) {
        List<LoginResponse> loginResponse;

        switch (loginRequest.getTipoUsuario()) {
            case "Paciente":
                List<Paciente> pacientes = pacienteRepository.findByDniAndContrasenia(loginRequest.getDni(), loginRequest.getContra());
                loginResponse = pacientes.stream().map(LoginResponse::fromPaciente).toList();
                break;
            case "medico":
                List<Medico> medicos = medicoRespository.findByDniAndContrasenia(loginRequest.getDni(), loginRequest.getContra());
                loginResponse = medicos.stream().map(LoginResponse::fromMedico).toList();
                break;
            case "admin":
                List<Administrativo> admins = administrativoRepository.findByDniAndContrasenia(loginRequest.getDni(), loginRequest.getContra());
                loginResponse = admins.stream().map(LoginResponse::fromAdmin).toList();
                break;
            default:
                return null;
        }

        return loginResponse;
    }

}
