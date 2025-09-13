package com.proyecto_final_ppt3.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AdminController {

    @PostMapping("/insertarAdmin")
    public Object insertarAdmin(@RequestBody Object usuario) {
        return aplicacion.insertarAdmin(usuario);
    }

}
