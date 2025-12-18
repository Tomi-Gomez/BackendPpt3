package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;
import com.proyecto_final_ppt3.controller.response.PacienteResponse;
import com.proyecto_final_ppt3.controller.response.RegistroResponse;
import com.proyecto_final_ppt3.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class PacienteController {

    private PacienteService paciente;

    @PostMapping("/insertar")
    public ResponseEntity<RegistroResponse> registrar(@RequestBody UsuarioRequest usuarioRequest) {
        return ResponseEntity.ok(paciente.registrar(usuarioRequest));
    }

    @GetMapping("/pacienteId/{id}")
    public List<PacienteResponse> pacienteById(@PathVariable("id") Integer id) {
        return paciente.pacienteById(id);
    }

    @PutMapping("/pacientes/{id}")
    public ResponseEntity<PacienteResponse> updatePaciente(@PathVariable Integer id,@RequestBody UsuarioRequest usuarioRequest) {
        usuarioRequest.setId(id);
        return ResponseEntity.ok(paciente.updatedPaciente(usuarioRequest));
    }

    @GetMapping("/buscarPorDni/{dni}")
    public ResponseEntity<PacienteResponse> buscarDni(@PathVariable Integer dni) {
        return  ResponseEntity.ok(paciente.buscarPorDni(dni));
    }

}
