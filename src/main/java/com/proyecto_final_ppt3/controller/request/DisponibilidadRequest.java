package com.proyecto_final_ppt3.controller.request;

import com.proyecto_final_ppt3.Model.Disponibilidad;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DisponibilidadRequest {
    private int id;
    private String desde;
    private String hasta;
    private int idMedico;
    private String especialidad;
    private String fecha;
    private List<String> dias;

    public static Disponibilidad toDisponibilidad(DisponibilidadRequest disponibilidadRequest) {
        return Disponibilidad.builder()
                .idMedico(disponibilidadRequest.getIdMedico())
                .especialidad(disponibilidadRequest.getEspecialidad())
                .desde(disponibilidadRequest.getDesde())
                .hasta(disponibilidadRequest.getHasta())
                .dias(String.join(",", disponibilidadRequest.getDias()))
                .build();
    }
}
