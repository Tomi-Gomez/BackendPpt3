package com.proyecto_final_ppt3.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Usuario {
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
