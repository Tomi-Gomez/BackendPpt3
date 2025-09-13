package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

public interface MedicoService {
    Object getTurnosTomadosCSV(String dniMedico);

    ResponseEntity<Medico> insertarMed(UsuarioRequest usuario);

    Optional<Medico> getMedicoEspecialidad(Object medico);

    Object medicosById(String med);

    Object updateMedico(String id, Map<String, Object> medico);

    Object historialTurnosMed(Integer idMedico);

    Object getTurnosMedicos(Object medicoTurnos, String dia);

    Object getMedico();

    Object updateMedico(Integer id);

    Object updatedMedico(MedicoRequest medicoRequest);
}
