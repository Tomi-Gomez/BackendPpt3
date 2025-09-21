package com.proyecto_final_ppt3.controller.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UsuarioResponse {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private String email;
    private String contrasenia;
    private String tipoUsuario;
    private Integer telefono;
    private String avatar;
}
