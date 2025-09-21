package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;
import com.proyecto_final_ppt3.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class DisponibilidadController {
    
    @Autowired
    private DisponibilidadService disponibilidadService;

    @GetMapping("/disponibilidadPorEsp/{specialty}")
    public ResponseEntity<List<DisponibilidadResponse>> disponibilidadPorEspecialidad(@PathVariable("specialty") String specialty) {
        return ResponseEntity.ok(
                disponibilidadService.buscarPorEspecialidad(specialty)
        );
    }
}
