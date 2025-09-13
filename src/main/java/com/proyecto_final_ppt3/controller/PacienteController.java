package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public class PacienteController {

    @PostMapping("/insertar")
    public Object registrar(@RequestBody UsuarioRequest usuarioRequest) {
        return aplicacion.insertar(usuarioRequest);
    }

    @GetMapping("/disponibilidadPorEsp/{specialty}")
    public Object disponibilidadPorEspecialidad(@PathVariable("specialty") String specialty) {
        return aplicacion.DisponibilidadporEspecialidad(Map.of("especialidad", specialty));
    }

    @GetMapping("/historialTurnos/{id_paciente}")
    public Object historialTurnos(@PathVariable("id_paciente") String idPaciente,
                                  @RequestParam(name = "opcion", required = false) String opcion) {
        return aplicacion.historialTurnos(Map.of("id_paciente", idPaciente, "opcion", opcion));
    }
}
