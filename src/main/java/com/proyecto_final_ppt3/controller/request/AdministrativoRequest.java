package com.proyecto_final_ppt3.controller.request;

import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.Model.Turno;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministrativoRequest {
    private String nombre;
    private String apellido;
    private Integer dni;
    private String email;
    private String contrasenia;
    private String tipoUsuario;
    private Integer telefono;

    public static Administrativo administrativo(AdministrativoRequest administrativoRequest) {
        return Administrativo.builder()
                .nombre(administrativoRequest.getNombre())
                .apellido(administrativoRequest.getApellido())
                .dni(administrativoRequest.getDni())
                .email(administrativoRequest.getEmail())
                .contrasenia(administrativoRequest.getContrasenia())
                .tipoUsuario(administrativoRequest.getTipoUsuario())
                .telefono(administrativoRequest.getTelefono())
                .build();
    }
}
