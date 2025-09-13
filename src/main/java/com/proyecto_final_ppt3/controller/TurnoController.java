package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.controller.request.TurnoRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TurnoController {

    @PostMapping("/guardarTurno")
    public Object guardarTurno(@RequestBody TurnoRequest turno) {
        return aplicacion.guardarTurno(turno);
    }
}
