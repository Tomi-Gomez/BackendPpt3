package com.proyecto_final_ppt3.Model;

import com.proyecto_final_ppt3.controller.request.TurnoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idPaciente;
    private Integer idMedico;
    private String fecha;
    private String hora;
    private String observaciones;
    private String estado;
    private String calificacion;
    private String especialidad;


    public static Turno FromTurnoRequest(TurnoRequest turnoRequest){
        return Turno.builder()
                .idPaciente(turnoRequest.getIdPaciente())
                .idMedico(turnoRequest.getIdMedico())
                .fecha(turnoRequest.getFecha())
                .hora(turnoRequest.getHora())
                .observaciones(turnoRequest.getObservaciones())
                .estado("PEDIENTE")
                .calificacion("PENDIENTE")
                .especialidad(turnoRequest.getEspecialidad())
                .build();
    }
}
