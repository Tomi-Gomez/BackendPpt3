package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Disponibilidad;
import com.proyecto_final_ppt3.Repository.DisponibilidadRepository;
import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;
import com.proyecto_final_ppt3.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisponibilidadServiceImpl implements DisponibilidadService {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @Override
    public List<DisponibilidadResponse> buscarPorEspecialidad(String specialty) {
        List<Disponibilidad> disponibilidades = disponibilidadRepository.findByEspecialidad(specialty);

        return disponibilidades.stream()
                .map(DisponibilidadResponse::fromDisponibilidad)
                .toList();
    }
}
