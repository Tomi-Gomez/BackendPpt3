package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.service.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MedicoController {

    private MedicoService aplicacion;

    @GetMapping("/medicosById")
    public Object medicosById(@RequestParam("id_medico") String idMedico) {
        return aplicacion.medicosById(idMedico);
    }

    @GetMapping("/historialTurnosMed/{id_medico}")
    public Object historialTurnosMed(@PathVariable("id_medico") Integer idMedico) {
        return aplicacion.historialTurnosMed(idMedico);
    }

    @GetMapping("/turnosTomados")
    public Object turnosTomados(@RequestParam("medicoId") Integer medicoTurnos, @RequestParam("dia") String dia) {
        return aplicacion.getTurnosMedicos(medicoTurnos, dia);
    }

    @GetMapping("/medicos")
    public Object getMedico() {
        return aplicacion.getMedico();
    }

    @PutMapping("/medicos")
    public Object updateMedico(@RequestBody MedicoRequest medicoRequest) {
        return aplicacion.updatedMedico(medicoRequest);
    }

    @GetMapping("/turnosTomadosCSV")
    public Object turnosTomadosCSV(@RequestParam("dni_medico") String dniMedico) {
        return aplicacion.getTurnosTomadosCSV(dniMedico);
    }
}
