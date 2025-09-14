package com.proyecto_final_ppt3.Model;

import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class Administrativo extends Usuario{

    public static Administrativo toAdministrativo(UsuarioRequest usuarioRequest) {
        return Administrativo.builder()
                .id(usuarioRequest.getId())
                .dni(usuarioRequest.getDni())
                .email(usuarioRequest.getEmail())
                .nombre(usuarioRequest.getNombre())
                .apellido(usuarioRequest.getApellido())
                .contrasenia(usuarioRequest.getContra())
                .build();
    }
}
