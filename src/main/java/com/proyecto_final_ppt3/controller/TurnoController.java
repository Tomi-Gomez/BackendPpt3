package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.controller.request.TurnoRequest;
import org.springframework.web.bind.annotation.*;

public class TurnoController {

    @PostMapping("/guardarTurno")
    public Object guardarTurno(@RequestBody TurnoRequest turno) {
        return aplicacion.guardarTurno(turno);
    }

    @PutMapping("/updateTurno")
    public Object updateTurno(@RequestParam("id") Integer id, @RequestParam("option") String option) {
        return aplicacion.updateTurno(id, option);
    }

    @PutMapping("/updateObservaciones/{id}")
    public Object updateObservaciones(@PathVariable("id") Integer id,
                                      @RequestBody TurnoRequest turno) {
        return aplicacion.updateObservaciones(id, turno);
    }
}
