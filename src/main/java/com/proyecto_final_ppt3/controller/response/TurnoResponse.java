package com.proyecto_final_ppt3.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto_final_ppt3.Model.DTO.TurnoDTO;
import com.proyecto_final_ppt3.Model.Turno;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TurnoResponse  {

    private Integer idTurno;
    private Integer idPaciente;
    private Integer idMedico;
    private String fecha;
    private String hora;
    private String observaciones;
    private String estado;
    private String calificacion;
    private String especialidad;


    public static TurnoResponse fromTurno(Turno turno){
        return TurnoResponse.builder()
                .idTurno(turno.getId())
                .idPaciente(turno.getIdPaciente())
                .idMedico(turno.getIdMedico())
                .fecha(turno.getFecha())
                .hora(turno.getHora())
                .observaciones(turno.getObservaciones())
                .estado(turno.getEstado())
                .calificacion(turno.getCalificacion())
                .especialidad(turno.getEspecialidad())
                .build();
    }
}
