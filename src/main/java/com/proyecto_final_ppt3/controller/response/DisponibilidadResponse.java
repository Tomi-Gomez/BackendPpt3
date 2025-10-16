package com.proyecto_final_ppt3.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto_final_ppt3.Model.Disponibilidad;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DisponibilidadResponse {

    @JsonProperty("id_medico")
    private Integer idMedico;

    private Integer id;
    private String desde;
    private String hasta;
    private String especialidad;
    private String fecha;
    private String dias;

    public static DisponibilidadResponse fromDisponibilidad(Disponibilidad disponibilidad) {
        return DisponibilidadResponse.builder()
                .id(disponibilidad.getId())
                .desde(disponibilidad.getDesde())
                .hasta(disponibilidad.getHasta())
                .idMedico(disponibilidad.getMedico().getId())
                .especialidad(disponibilidad.getEspecialidad())
                .fecha(disponibilidad.getFecha())
                .dias(disponibilidad.getDias())
                .build();

    }
}
