package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;

import java.util.List;

public interface DisponibilidadService {
    List<DisponibilidadResponse> buscarPorEspecialidad(String specialty);
}
