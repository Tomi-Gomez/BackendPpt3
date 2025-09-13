package com.proyecto_final_ppt3.Model;

import com.proyecto_final_ppt3.Enum.Estado;
import jakarta.persistence.Entity;
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
    private Long id_Turno;
    private LocalDate fecha;
    private LocalTime hora;
    private Estado estado;
    private boolean notificacion;
}
