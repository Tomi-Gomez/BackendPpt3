package com.proyecto_final_ppt3.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto_final_ppt3.Enum.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

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

}
