package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Disponibilidad;
import com.proyecto_final_ppt3.Repository.DisponibilidadRepository;
import com.proyecto_final_ppt3.controller.request.DisponibilidadRequest;
import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;
import com.proyecto_final_ppt3.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        // Guardar y obtener la entidad persistida con su ID generado
        Disponibilidad guardada = disponibilidadRepository.save(disponibilidad);

        // Retornar la versión persistida convertida a Response
        return DisponibilidadResponse.fromDisponibilidad(guardada);
    }
    
    @Override
    public Optional<DisponibilidadResponse> buscarPorMedicoId(Integer idMedico) {
    return disponibilidadRepository.findByMedico_Id(idMedico) 
            .map(DisponibilidadResponse::fromDisponibilidad);
}
}
