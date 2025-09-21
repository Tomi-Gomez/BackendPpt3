package com.proyecto_final_ppt3.handler;

import org.springframework.data.crossstore.ChangeSetPersister;

public class MedicosNotFoundException extends RuntimeException {
    public MedicosNotFoundException(String mensaje){
        super(mensaje);
    }
}
