package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.controller.request.DisponibilidadRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.service.AdministrativoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class AdminController {

    private AdministrativoService administrativo;

    @PostMapping("/insertarAdmin")
    public Administrativo insertarAdmin(@RequestBody Administrativo usuario) {
        return administrativo.insertarAdmin(usuario);
    }

    @PostMapping("/guardarDisponibilidad")
    public Object guardarDisponibilidad(@RequestBody Map<String, DisponibilidadRequest> disponibilidad) {
        return administrativo.postGuardarDisponibilidad(disponibilidad);
    }

}
