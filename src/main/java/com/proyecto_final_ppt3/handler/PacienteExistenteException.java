package com.proyecto_final_ppt3.handler;

public class PacienteExistenteException extends RuntimeException{
    public PacienteExistenteException (String mensaje){
        super(mensaje);
    }
}
