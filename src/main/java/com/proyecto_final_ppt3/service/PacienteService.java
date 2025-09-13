package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.controller.request.DisponibilidadRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;

import java.util.List;

public interface PacienteService {
    String registrar(UsuarioRequest usuarioRequest);
    List<DisponibilidadResponse> DisponibilidadporEspecialidad (String specialty);

    Object historialTurnos (String idPaciente, String opcion);

    Object pacienteById (Object idPaciente);
}
