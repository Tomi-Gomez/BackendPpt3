package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.controller.request.LoginRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.LoginResponse;
import com.proyecto_final_ppt3.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PacienteController {

    private PacienteService paciente;

    @PostMapping("/insertar")
    public String registrar(@RequestBody UsuarioRequest usuarioRequest) {
        return paciente.registrar(usuarioRequest);
    }

    @GetMapping("/pacienteId/{id}")
    public Object pacienteById(@PathVariable("id") String id) {
        return paciente.pacienteById(id);
    }


}
