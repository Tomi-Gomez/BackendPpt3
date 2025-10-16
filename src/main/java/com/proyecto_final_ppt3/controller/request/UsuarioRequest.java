package com.proyecto_final_ppt3.controller.request;

import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.service.AdministrativoService;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioRequest {
    private Integer id;
    private String tipoUsuario;
    private String email;
    private Integer dni;
    private String nombre;
    private String apellido;
    private Integer telefono;
    private String contra;
    private String credencial;
    private String matricula;
    private String avatar;
    private Integer dniTitular;
    private String parentesco;
}
