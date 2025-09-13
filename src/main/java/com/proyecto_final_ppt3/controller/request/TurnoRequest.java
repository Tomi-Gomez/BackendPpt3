package com.proyecto_final_ppt3.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurnoRequest {
    @JsonProperty("id_paciente")
    private Integer idPaciente;

    @JsonProperty("id_medico")
    private Integer idMedico;

    private String especialidad;
    private String fecha;
    private String hora;
}
