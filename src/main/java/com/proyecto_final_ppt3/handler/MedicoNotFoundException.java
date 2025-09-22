package com.proyecto_final_ppt3.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class MedicoNotFoundException extends RuntimeException{
    public MedicoNotFoundException(Integer id){
        super("Medico no encontrado " + id);
    }
}
