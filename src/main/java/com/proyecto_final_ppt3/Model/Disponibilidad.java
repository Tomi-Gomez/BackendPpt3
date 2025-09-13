package com.proyecto_final_ppt3.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Disponibilidad {
    private int id;
    private String desde;
    private String hasta;
    private int idMedico;
    private String especialidad;
    private String fecha;
    private String dias;
}
