package com.proyecto_final_ppt3.controller.response;

import com.proyecto_final_ppt3.Model.Turno;
import com.proyecto_final_ppt3.dto.TurnoDetalleDTO;
import com.proyecto_final_ppt3.dto.TurnoDetalleProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TurnoDetalleResponse {
    private Long id;
    private LocalDate fecha;
    private String paciente;
    private String dniPaciente;
    private String medico;

    public static TurnoDetalleResponse fromTurnoDetalleProjection(TurnoDetalleProjection turnoDetalleProjection){
        return TurnoDetalleResponse.builder()
                .id(turnoDetalleProjection.getId())
                .fecha(turnoDetalleProjection.getFecha())
                .paciente(turnoDetalleProjection.getPaciente())
                .dniPaciente(turnoDetalleProjection.getDniPaciente())
                .medico(turnoDetalleProjection.getMedico())
                .build();
    }
}
