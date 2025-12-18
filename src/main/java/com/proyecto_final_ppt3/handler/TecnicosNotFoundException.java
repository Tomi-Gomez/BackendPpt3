package com.proyecto_final_ppt3.handler;

public class TecnicosNotFoundException extends RuntimeException {
    public TecnicosNotFoundException(String mensaje){
        super(mensaje);
    }
}
