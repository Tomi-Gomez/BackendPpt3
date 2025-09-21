package com.proyecto_final_ppt3.controller.response;

import com.proyecto_final_ppt3.Model.Turno;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TurnoResponse  {
    private Integer idTurno;
    private Integer idPaciente;
    private Integer idMedico;
    private String dia;
    private String hora;
    private String observaciones;
    private String estado;
    private String calificacion;


    public static TurnoResponse fromTurno(Turno turno){
        return TurnoResponse.builder()
                .idTurno(turno.getIdTurno())
                .idPaciente(turno.getIdPaciente())
                .idMedico(turno.getIdMedico())
                .dia(turno.getDia())
                .hora(turno.getHora())
                .observaciones(turno.getObservaciones())
                .estado(turno.getEstado())
                .calificacion(turno.getCalificacion())
                .build();
    }
}
