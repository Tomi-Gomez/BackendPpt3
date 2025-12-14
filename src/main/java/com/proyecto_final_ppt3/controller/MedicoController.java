package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;
import com.proyecto_final_ppt3.service.MedicoService;
import com.proyecto_final_ppt3.service.TecnicoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/")
public class MedicoController {

    private MedicoService aplicacion;
	private TecnicoService tecnicoService;

    @GetMapping("/medicos")
    public ResponseEntity<List<MedicoResponse>> getMedicos() {
        return ResponseEntity.ok(aplicacion.getMedicos());
    }

    @GetMapping("/medicosById")
    public ResponseEntity<List<MedicoResponse>> medicosById(@RequestParam("id_medico") Integer idMedico) {
        return ResponseEntity.ok(aplicacion.medicosById(idMedico));
    }

    @PutMapping("/medicos/{id}")
    public ResponseEntity<MedicoResponse> updateMedico(@PathVariable Integer id,@RequestBody MedicoRequest medicoRequest) {
        medicoRequest.setId(id);
        return ResponseEntity.ok(aplicacion.updatedMedico(medicoRequest));
    }

    @PutMapping("/medicoHabilitacion/{id}")
    public ResponseEntity<MedicoResponse> updateMedicoHabilitacion(@PathVariable Integer id,@RequestBody MedicoRequest medicoRequest) {
        medicoRequest.setId(id);
        return ResponseEntity.ok(aplicacion.updateMedicoHabilitacion(medicoRequest));
    }

	@GetMapping("/tecnicos")
	public ResponseEntity<List<MedicoResponse>> getTecnicos() {
		return ResponseEntity.ok(tecnicoService.getTecnicos());
	}

	@GetMapping("/tecnicoById")
	public ResponseEntity<MedicoResponse> tecnicoById(@RequestParam("id_medico") Integer idTecnico) {
		return ResponseEntity.ok(tecnicoService.getTecnicoById(idTecnico));
	}


}
