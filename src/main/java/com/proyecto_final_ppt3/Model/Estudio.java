package com.proyecto_final_ppt3.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Estudio {
    private Long id_estudio;
    private String nombre;
    private String condiciones;
}
