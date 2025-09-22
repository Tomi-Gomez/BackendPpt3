package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.response.DisponibilidadResponse;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;
import com.proyecto_final_ppt3.service.MedicoService;
import com.proyecto_final_ppt3.service.imp.MedicoServiceImp;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/")
public class MedicoController {

    private MedicoService aplicacion;

    @GetMapping("/medicos")
    public ResponseEntity<List<MedicoResponse>> getMedicos() {
        return ResponseEntity.ok(aplicacion.getMedicos());
    }

    @GetMapping("/medicosById")
    public ResponseEntity<List<MedicoResponse>> medicosById(@RequestParam("id_medico") Integer idMedico) {
        return ResponseEntity.ok(aplicacion.medicosById(idMedico));
    }

    @PutMapping("/medicos")
    public ResponseEntity<MedicoResponse> updateMedico(@RequestBody MedicoRequest medicoRequest) {
        return ResponseEntity.ok(aplicacion.updatedMedico(medicoRequest));
    }
}
