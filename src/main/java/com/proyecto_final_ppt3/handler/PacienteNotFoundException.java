package com.proyecto_final_ppt3.handler;

public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException(String message) {
        super(message);
    }
}
