package com.proyecto_final_ppt3.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private AplicacionService aplicacion;

    // donde pegarle
    @PostMapping("/guardarDisponibilidad")
    public Object guardarDisponibilidad(@RequestBody Map<String, Object> disponibilidad) {
        return aplicacion.postGuardarDisponibilidad(disponibilidad);
    }
}
