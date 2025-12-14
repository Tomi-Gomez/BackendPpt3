package com.proyecto_final_ppt3.handler;


public class TecnicoNotFoundException extends RuntimeException{
    public TecnicoNotFoundException(Integer id){
        super("Tecnico no encontrado " + id);
    }
}
