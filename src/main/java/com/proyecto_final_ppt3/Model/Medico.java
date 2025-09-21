package com.proyecto_final_ppt3.Model;

import com.proyecto_final_ppt3.Enum.EspecialidadMedica;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico extends Usuario{
    private String matricula;
    private String especialidadMedica;
    private String habilitacion;
}
