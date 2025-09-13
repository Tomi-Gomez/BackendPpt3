package com.proyecto_final_ppt3.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DisponibilidadResponse {

    @JsonProperty("id_medico")
    private Integer idMedico;

    private Integer id;
    private Integer desde;
    private Integer hasta;
    private Integer especialidad;
    private Integer fecha;
    private Integer dias;


}
