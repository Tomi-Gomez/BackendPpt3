package com.proyecto_final_ppt3.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto_final_ppt3.Model.DTO.TurnoDTO;
import com.proyecto_final_ppt3.Model.Turno;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoRequest {
    @JsonProperty("id_paciente")
    private Integer idPaciente;

    @JsonProperty("id_medico")
    private Integer idMedico;

    private String especialidad;
    private String fecha;
    private String hora;
    private String observaciones;

    public static Turno toTurno(TurnoRequest turnoRequest){
        return Turno.builder()
                .idPaciente(turnoRequest.getIdPaciente())
                .idMedico(turnoRequest.getIdMedico())
                .especialidad(turnoRequest.getEspecialidad())
                .fecha(turnoRequest.getFecha())
                .hora(turnoRequest.getHora())
                .observaciones("no aplica")
                .estado("a confirmar")
                .calificacion("a confirmar")
                .build();
    }
}
