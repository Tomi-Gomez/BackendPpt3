package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.service.PacienteService;
import org.springframework.stereotype.Service;


@Service
public class PacienteServiceImpl implements PacienteService {

    private PacienteRepository repository;

    @Override
    public String registrar(UsuarioRequest usuarioRequest) {
        Paciente paciente = Paciente.fromUsuarioRequest(usuarioRequest);

        try {
            repository.save(paciente);
        } catch (Exception e) {
            return "Error en registro";
        }

        return "ok";
    }

    @Override
    public Object pacienteById(Object idPaciente) {
        return null;
    }
}
