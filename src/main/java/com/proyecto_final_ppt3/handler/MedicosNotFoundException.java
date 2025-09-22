package com.proyecto_final_ppt3.handler;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ControllerAdvice;


public class MedicosNotFoundException extends RuntimeException {
    public MedicosNotFoundException(String mensaje){
        super(mensaje);
    }
}
