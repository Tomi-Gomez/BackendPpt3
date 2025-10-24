package com.proyecto_final_ppt3.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiasEnum {
    LUNES("Lunes", "lu"),
    MARTES("Martes", "ma"),
    MIERCOLES("Miercoles", "mi"),
    JUEVES("Jueves", "ju"),
    VIERNES("Viernes", "vi");

    private final String diaEntero;
    private final String diaCortado;

    public static String obtenerDiaEnteroPorDiaCortado(String nombre) {
        if (nombre == null) return null;

        String normalizado = nombre.trim().toLowerCase();

        for (DiasEnum p : values()) {
            if (p.diaCortado.toLowerCase().equals(normalizado)) {
                return p.diaEntero;
            }
        }
        return null;
    }
}