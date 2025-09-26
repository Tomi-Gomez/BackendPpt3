package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.controller.request.DisponibilidadRequest;
import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;

import java.util.List;
import java.util.Optional;

public interface DisponibilidadService {
    List<DisponibilidadResponse> buscarPorEspecialidad(String specialty);

    DisponibilidadResponse postGuardarDisponibilidad(DisponibilidadRequest disponibilidad);

    Optional<DisponibilidadResponse> buscarPorMedicoId(Integer idMedico);
}
