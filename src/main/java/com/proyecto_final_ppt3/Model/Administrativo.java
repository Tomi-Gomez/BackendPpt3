package com.proyecto_final_ppt3.Model;

import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class Administrativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
