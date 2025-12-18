package com.proyecto_final_ppt3.handler;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String mensaje){
        super(mensaje);
    }
}
