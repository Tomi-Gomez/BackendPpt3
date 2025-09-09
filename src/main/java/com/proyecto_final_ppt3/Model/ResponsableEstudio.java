package com.proyecto_final_ppt3.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsableEstudio {
    private Long id_RE;
    private String nombre;
}
