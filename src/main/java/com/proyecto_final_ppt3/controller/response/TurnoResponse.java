package com.proyecto_final_ppt3.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto_final_ppt3.Model.DTO.TurnoDTO;
import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Model.Turno;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private String nombreMedico;
    private String apellidoMedico;
    private String nombrePaciente;
    private String apellidoPaciente;


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

       public static TurnoResponse fromTurnoExtendido(Turno turno, Medico medico, Paciente paciente) {
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
                .nombreMedico(medico != null ? medico.getNombre() : null)
                .apellidoMedico(medico != null ? medico.getApellido() : null)
                .nombrePaciente(paciente != null ? paciente.getNombre() : null)
                .apellidoPaciente(paciente != null ? paciente.getApellido() : null)
                .build();
    }
}
