package com.proyecto_final_ppt3.controller.response;

import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Model.Paciente;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private Integer id;
    private Integer dni;
    private String contra;
    private String tipoUsuario;
    private Boolean habilitacion;
    private String nombre;

    public static LoginResponse fromPaciente(Paciente paciente){
        return LoginResponse.builder()
                .id(paciente.getId())
                .dni(paciente.getDni())
                .contra(paciente.getContrasenia())
                .tipoUsuario(paciente.getTipoUsuario())
                .nombre(paciente.getNombre())
                .build();
    }

    public static LoginResponse fromMedico(Medico medico){
        return LoginResponse.builder()
                .id(medico.getId())
                .dni(medico.getDni())
                .contra(medico.getContrasenia())
                .tipoUsuario(medico.getTipoUsuario())
                .nombre(medico.getNombre())
                .habilitacion(medico.getHabilitacion())
                .build();
    }

    public static LoginResponse fromAdministrativo(Administrativo administrativo){
        return LoginResponse.builder()
                .id(administrativo.getId())
                .dni(administrativo.getDni())
                .contra(administrativo.getContrasenia())
                .tipoUsuario(administrativo.getTipoUsuario())
                .nombre(administrativo.getNombre())
                .build();
    }
}
