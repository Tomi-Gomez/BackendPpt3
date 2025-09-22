package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.controller.request.LoginRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.LoginResponse;
import com.proyecto_final_ppt3.controller.response.PacienteResponse;
import com.proyecto_final_ppt3.service.PacienteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class PacienteController {

    private PacienteService paciente;


    @PostMapping("/insertar")
    public ResponseEntity<String> registrar(@RequestBody UsuarioRequest usuarioRequest) {
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(paciente.registrar(usuarioRequest));
    }

    @GetMapping("/pacienteId/{id}")
    public List<PacienteResponse> pacienteById(@PathVariable("id") Integer id) {
        return paciente.pacienteById(id);
    }


}
