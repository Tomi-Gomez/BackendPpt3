package com.proyecto_final_ppt3.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto_final_ppt3.Enum.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTurno;
    private Integer idPaciente;
    private Integer idMedico;
    private String dia;
    private String hora;
    private String observaciones;
    private String estado;
    private String calificacion;

}
