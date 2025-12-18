package com.proyecto_final_ppt3.handler;

public class MedicoNotFoundException extends RuntimeException{
    public MedicoNotFoundException(Integer id){
        super("Medico no encontrado " + id);
    }
}
