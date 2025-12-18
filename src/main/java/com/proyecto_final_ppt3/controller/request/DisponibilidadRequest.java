package com.proyecto_final_ppt3.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto_final_ppt3.Model.Disponibilidad;
import com.proyecto_final_ppt3.Model.Medico;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DisponibilidadRequest {
    private int id;
    private String desde;
    private String hasta;
    @JsonProperty("id_medico")
    private Integer idMedico;
    private String especialidad;
    private String fecha;
    private List<String> dias;

    public static Disponibilidad toDisponibilidad(DisponibilidadRequest disponibilidadRequest) {
        Medico medico = new Medico();
        medico.setId(disponibilidadRequest.getIdMedico());

        return Disponibilidad.builder()
                .id(disponibilidadRequest.getId())
                .desde(disponibilidadRequest.getDesde())
                .hasta(disponibilidadRequest.getHasta())
                .especialidad(disponibilidadRequest.getEspecialidad())
                .fecha(disponibilidadRequest.getFecha())
                .dias(String.join(",", disponibilidadRequest.getDias()))
                .medico(medico)
                .build();
    }
}
