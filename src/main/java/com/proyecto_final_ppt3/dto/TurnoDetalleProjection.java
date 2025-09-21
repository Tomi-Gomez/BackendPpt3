package com.proyecto_final_ppt3.dto;

import java.time.LocalDate;

public interface TurnoDetalleProjection {
    Long getId();
    LocalDate getFecha();
    String getPaciente();
    String getDniPaciente();
    String getMedico();
}