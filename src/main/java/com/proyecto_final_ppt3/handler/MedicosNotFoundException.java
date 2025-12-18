package com.proyecto_final_ppt3.handler;

public class MedicosNotFoundException extends RuntimeException {
    public MedicosNotFoundException(String mensaje){
        super(mensaje);
    }
}
