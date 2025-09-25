package com.proyecto_final_ppt3.handler;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ControllerAdvice;


public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String mensaje){
        super(mensaje);
    }
}
