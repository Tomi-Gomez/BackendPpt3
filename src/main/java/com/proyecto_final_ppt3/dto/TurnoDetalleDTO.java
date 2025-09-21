package com.proyecto_final_ppt3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoDetalleDTO {
    private Long id;
    private LocalDate fecha;
    private String paciente;
    private String dniPaciente;
    private String medico;
}
