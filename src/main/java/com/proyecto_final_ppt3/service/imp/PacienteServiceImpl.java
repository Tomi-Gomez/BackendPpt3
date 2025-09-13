package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.controller.request.DisponibilidadRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;
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
    public List<DisponibilidadResponse> DisponibilidadporEspecialidad(String specialty) {
        return repository.findByEspecialidadContainingIgnoreCase(specialty)
                .stream()
                .map(d -> new DisponibilidadResponse(
                        d.getId(),
                        d.getDesde(),
                        d.getHasta(),
                        d.getIdMedico(),
                        d.getEspecialidad(),
                        d.getFecha(),
                        d.getDias()
                ))
                .toList();
    }

    @Override
    public Object historialTurnos(String idPaciente, String opcion) {
        return null;
    }

    @Override
    public Object pacienteById(Object idPaciente) {
        return null;
    }
}
