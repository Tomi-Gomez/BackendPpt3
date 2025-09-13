package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.controller.request.TurnoRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
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

    // de aca para abajo
    @PutMapping("/updateTurno")
    public Object updateTurno(@RequestParam Map<String, String> updateTurno) {
        return aplicacion.updateTurno(updateTurno);
    }

    @GetMapping("/pacienteId/{id}")
    public Object pacienteById(@PathVariable("id") String id) {
        return aplicacion.pacienteById(Map.of("id", id));
    }

    @PutMapping("/usuarios/{id}")
    public Object updatePaciente(@PathVariable("id") String id,
                                 @RequestBody Map<String, Object> usuario) {
        return aplicacion.updatePaciente(id, usuario);
    }

    @PutMapping("/updateObservaciones/{id}")
    public Object updateObservaciones(@PathVariable("id") String id,
                                      @RequestBody Map<String, Object> turno) {
        return aplicacion.updateObservaciones(id, turno);
    }

}
