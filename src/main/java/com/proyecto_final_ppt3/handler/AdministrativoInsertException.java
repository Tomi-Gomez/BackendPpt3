package com.proyecto_final_ppt3.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class AdministrativoInsertException extends RuntimeException{
    public AdministrativoInsertException(String mensaje){
        super(mensaje);
    }
}
