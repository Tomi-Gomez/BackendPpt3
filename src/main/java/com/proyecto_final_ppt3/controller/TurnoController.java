package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.Model.Turno;
import com.proyecto_final_ppt3.controller.request.TurnoRequest;
import com.proyecto_final_ppt3.controller.response.TurnoDetalleResponse;
import com.proyecto_final_ppt3.controller.response.TurnoResponse;
import com.proyecto_final_ppt3.dto.TurnoDetalleDTO;
import com.proyecto_final_ppt3.service.TurnoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class TurnoController {

    private TurnoService turnoService;


    @PostMapping("/guardarTurno")
    public TurnoResponse guardarTurno(@RequestBody TurnoRequest turno) {
        return turnoService.guardarTurno(turno);
    }

    @PutMapping("/updateTurno")
    public TurnoResponse updateTurno(@RequestParam("id") Integer id, @RequestParam("option") String option) {
        return turnoService.updateTurno(id, option);
    }

    @PutMapping("/updateObservaciones/{id}")
    public TurnoResponse updateObservaciones(@PathVariable("id") Integer idTurno,
                                      @RequestBody TurnoRequest turno) {
        return turnoService.updateObservaciones(idTurno, turno);
    }

    @GetMapping("/historialTurnos/{id_paciente}")
    public List<TurnoResponse> historialTurnos(@PathVariable("id_paciente") Integer idPaciente,
                                               @RequestParam(name = "opcion", required = false) Integer opcion) {
        return turnoService.historialTurnos(idPaciente, opcion);
    }

    @GetMapping("/turnosTomados")
    public List<TurnoResponse> turnosTomados(@RequestParam("medicoId") Integer medicoId, @RequestParam("dia") String dia) {
        return turnoService.getTurnosMedicos(medicoId, dia);
    }

    @GetMapping("/turnosTomadosCSV")
    public List<TurnoDetalleResponse> turnosTomadosCSV(@RequestParam("dni_medico") Integer dniMedico) {
        return turnoService.getTurnosTomadosCSV(dniMedico);
    }

    @GetMapping("/historialTurnosMed/{id_medico}")
    public List<TurnoResponse> historialTurnosMed(@PathVariable("id_medico") Integer idMedico) {
        return turnoService.historialTurnosMed(idMedico);
    }
}
