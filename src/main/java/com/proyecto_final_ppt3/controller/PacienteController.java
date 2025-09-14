package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;
import com.proyecto_final_ppt3.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PacienteController {

    private PacienteService paciente;

    @PostMapping("/insertar")
    public String registrar(@RequestBody UsuarioRequest usuarioRequest) {
        return paciente.registrar(usuarioRequest);
    }

    @GetMapping("/disponibilidadPorEsp/{specialty}")
    public ResponseEntity<List<DisponibilidadResponse>> disponibilidadPorEspecialidad(@PathVariable("specialty") String specialty) {
        return ResponseEntity.ok(
                paciente.DisponibilidadporEspecialidad(specialty)
        );
    }

    @GetMapping("/historialTurnos/{id_paciente}")
    public Object historialTurnos(@PathVariable("id_paciente") String idPaciente,
                                  @RequestParam(name = "opcion", required = false) String opcion) {
        return paciente.historialTurnos(idPaciente, opcion);
    }

    @GetMapping("/pacienteId/{id}")
    public Object pacienteById(@PathVariable("id") String id) {
        return paciente.pacienteById(id);
    }
}
