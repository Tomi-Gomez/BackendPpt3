package com.proyecto_final_ppt3.Model.DTO;

import com.proyecto_final_ppt3.Model.Turno;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TurnoDTO {
    private Integer id;
    private Integer idPaciente;
    private Integer idMedico;
    private String dia;
    private String hora;
    private String observaciones;
    private String estado;
    private String calificacion;
    private String especialidad;

    public TurnoDTO turnoDTO(Turno turno){
        return TurnoDTO.builder()
                .id(turno.getId())
                .idPaciente(turno.getIdPaciente())
                .idMedico(turno.getIdMedico())
                .dia(turno.getFecha())
                .hora(turno.getHora())
                .observaciones(turno.getObservaciones())
                .estado(turno.getEstado())
                .calificacion(turno.getCalificacion())
                .especialidad(turno.getEspecialidad())
                .build();
    }

    
}
