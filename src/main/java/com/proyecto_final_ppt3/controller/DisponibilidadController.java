package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.controller.request.DisponibilidadRequest;
import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;
import com.proyecto_final_ppt3.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/guardarDisponibilidad")
    public ResponseEntity<DisponibilidadResponse> guardarDisponibilidad(@RequestBody DisponibilidadRequest request) {
        DisponibilidadResponse response = disponibilidadService.postGuardarDisponibilidad(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/disponibilidad/{idMedico}")
    public ResponseEntity<DisponibilidadResponse> getDisponibilidadPorMedico(@PathVariable Integer idMedico) {
        List<DisponibilidadResponse> disponibilidades = disponibilidadService.buscarPorMedicoId(idMedico);

        if (disponibilidades.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(disponibilidades.get(0));
    }
}
