package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Disponibilidad;
import com.proyecto_final_ppt3.Repository.DisponibilidadRepository;
import com.proyecto_final_ppt3.controller.request.DisponibilidadRequest;
import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;
import com.proyecto_final_ppt3.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisponibilidadServiceImp implements DisponibilidadService {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @Override
    public List<DisponibilidadResponse> buscarPorEspecialidad(String specialty) {
        try {
            List<Disponibilidad> disponibilidades = disponibilidadRepository.findByEspecialidad(specialty);
            return disponibilidades.stream()
                    .map(DisponibilidadResponse::fromDisponibilidad)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error en la busqueda de disponibilidades" + specialty, e);
        }

    }

    @Override
    public DisponibilidadResponse postGuardarDisponibilidad(DisponibilidadRequest disponibilidadRequest) {
        Disponibilidad disponibilidad = DisponibilidadRequest.toDisponibilidad(disponibilidadRequest);
        disponibilidadRepository.save(disponibilidad);

        return DisponibilidadResponse.fromDisponibilidad(disponibilidad);
    }
}
