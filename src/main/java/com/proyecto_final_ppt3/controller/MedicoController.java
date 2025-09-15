package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;
import com.proyecto_final_ppt3.service.MedicoService;
import com.proyecto_final_ppt3.service.imp.MedicoServiceImp;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MedicoController {

    private MedicoServiceImp aplicacion;

    @GetMapping("/medicos")
    public ResponseEntity<List<MedicoResponse>> getMedicos() {
        return ResponseEntity.ok(aplicacion.getMedicos());
    }
    @GetMapping("/medicosById")
    public ResponseEntity<MedicoResponse> medicosById(@RequestParam("id_medico") Integer idMedico) {
        return ResponseEntity.ok(aplicacion.medicosById(idMedico));
    }

    @PutMapping("/medicos")
    public ResponseEntity<MedicoResponse> updateMedico(@RequestBody MedicoRequest medicoRequest) {
        return ResponseEntity.ok(aplicacion.updatedMedico(medicoRequest));
    }

    //Hay que conectarlo con turno
    @GetMapping("/historialTurnosMed/{id_medico}")
    public Object historialTurnosMed(@PathVariable("id_medico") Integer idMedico) {
        return aplicacion.historialTurnosMed(idMedico);
    }

    @GetMapping("/turnosTomados")
    public Object turnosTomados(@RequestParam("medicoId") Integer medicoTurnos, @RequestParam("dia") String dia) {
        return aplicacion.getTurnosMedicos(medicoTurnos, dia);
    }

    @GetMapping("/turnosTomadosCSV")
    public Object turnosTomadosCSV(@RequestParam("dni_medico") String dniMedico) {
        return aplicacion.getTurnosTomadosCSV(dniMedico);
    }
}
