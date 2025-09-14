package com.proyecto_final_ppt3.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsableEstudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_RE;
    private String nombre;
}
