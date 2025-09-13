package com.proyecto_final_ppt3.Model;

import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Paciente extends Usuario{
    private String credencial;

    public static Paciente fromUsuarioRequest(UsuarioRequest usuarioRequest) {
        return Paciente.builder()
                .dni(usuarioRequest.getDni())
                .tipoUsuario(usuarioRequest.getTipoUsuario())
                .email(usuarioRequest.getEmail())
                .nombre(usuarioRequest.getNombre())
                .apellido(usuarioRequest.getApellido())
                .telefono(usuarioRequest.getTelefono())
                .contrasenia(usuarioRequest.getContra())
                .credencial(usuarioRequest.getCredencial())
                .avatar(usuarioRequest.getAvatar())
                .build();

    }
}
