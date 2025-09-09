package com.proyecto_final_ppt3.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {
    private Long id;
    private LocalDate fecha;
    private List<Turno> listaTurnos;
}
