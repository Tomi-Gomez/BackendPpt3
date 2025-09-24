package com.proyecto_final_ppt3.Model;

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
    private String dia;
    private String hora;
    private String observaciones;
    private String estado;
    private String calificacion;
    private String especialidad;

}
