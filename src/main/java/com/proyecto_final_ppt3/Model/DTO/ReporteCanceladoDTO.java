package com.proyecto_final_ppt3.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteCanceladoDTO {
    private String fecha;
    private String hora;
    private String medico;
    private String paciente;
}
