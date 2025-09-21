package com.proyecto_final_ppt3.controller.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String tipoUsuario;
    private Integer dni;
    private String contra;
}
