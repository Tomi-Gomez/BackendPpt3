package com.proyecto_final_ppt3.Model;

import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
