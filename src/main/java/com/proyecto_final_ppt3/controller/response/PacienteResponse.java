package com.proyecto_final_ppt3.controller.response;

import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class PacienteResponse extends UsuarioResponse{
    private String credencial;

    public static PacienteResponse fromPaciente(Paciente paciente){
        return PacienteResponse.builder()
                .id(paciente.getId())
                .nombre(paciente.getNombre())
                .apellido(paciente.getApellido())
                .dni(paciente.getDni())
                .email(paciente.getEmail())
                .contrasenia(paciente.getContrasenia())
                .tipoUsuario(paciente.getTipoUsuario())
                .telefono(paciente.getTelefono())
                .avatar(paciente.getAvatar())
                .credencial(paciente.getCredencial())
                .build();

    }
}
