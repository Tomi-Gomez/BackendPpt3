package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.controller.request.DisponibilidadRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;
import com.proyecto_final_ppt3.controller.response.PacienteResponse;
import com.proyecto_final_ppt3.controller.response.RegistroResponse;

import java.util.List;

public interface PacienteService {
    RegistroResponse registrar(UsuarioRequest usuarioRequest);
    PacienteResponse updatedPaciente(UsuarioRequest usuarioRequest);

    List<PacienteResponse> pacienteById (Integer idPaciente);
}
