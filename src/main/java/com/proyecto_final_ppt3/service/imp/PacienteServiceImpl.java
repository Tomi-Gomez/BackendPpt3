package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.PacienteResponse;
import com.proyecto_final_ppt3.service.PacienteService;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public List<PacienteResponse> pacienteById(Integer idPaciente) {
        List<Paciente> pacientes = repository.findByDni(idPaciente);
        return pacientes.stream().map(PacienteResponse::fromPaciente).toList();
    }
}
