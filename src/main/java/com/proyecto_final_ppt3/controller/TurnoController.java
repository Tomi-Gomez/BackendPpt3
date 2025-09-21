package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.Model.Turno;
import com.proyecto_final_ppt3.controller.request.TurnoRequest;
import com.proyecto_final_ppt3.service.TurnoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class TurnoController {

    private TurnoService turnoService;


    @PostMapping("/guardarTurno")
    public Object guardarTurno(@RequestBody TurnoRequest turno) {
        return turnoService.guardarTurno(turno);
    }

    @PutMapping("/updateTurno")
    public Object updateTurno(@RequestParam("id") Integer id, @RequestParam("option") String option) {
        return turnoService.updateTurno(id, option);
    }

    @PutMapping("/updateObservaciones/{id}")
    public Object updateObservaciones(@PathVariable("id") Integer id,
                                      @RequestBody TurnoRequest turno) {
        return turnoService.updateObservaciones(id, turno);
    }

    @GetMapping("/historialTurnos/{id_paciente}")
    public List<Turno> historialTurnos(@PathVariable("id_paciente") Integer idPaciente,
                                       @RequestParam(name = "opcion", required = false) Integer opcion) {
        return turnoService.historialTurnos(idPaciente, opcion);
    }
}
