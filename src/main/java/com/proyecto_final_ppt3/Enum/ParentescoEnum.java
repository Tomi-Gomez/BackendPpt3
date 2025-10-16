package com.proyecto_final_ppt3.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParentescoEnum {
    CONYUGE(1, "conyuge"),
    CONVIVIENTE(2, "conviviente"),
    HIJO_MENOR(3, "hijo_menor"),
    HIJO_MAYOR(4, "hijo_mayor");

    private final int codigo;
    private final String descripcion;

    public static Integer obtenerCodigoPorNombre(String nombre) {
        if (nombre == null) return null;

        String normalizado = nombre.trim().toLowerCase();

        for (ParentescoEnum p : values()) {
            if (p.descripcion.toLowerCase().equals(normalizado)) {
                return p.codigo;
            }
        }
        return null;
    }
}
