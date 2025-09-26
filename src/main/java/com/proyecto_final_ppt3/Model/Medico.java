package com.proyecto_final_ppt3.Model;

import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Medico {
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
    private String matricula;
    private String especialidad;
    private Boolean habilitacion;

    //Chequear que en el back de nico no inserta el avatar a la hora de registrar..... pero si llega del front

    public static Medico fromUsuarioRequest(MedicoRequest medicoRequest) {
        return Medico.builder()
                .email(medicoRequest.getEmail())
                .dni(medicoRequest.getDni())
                .nombre(medicoRequest.getNombre())
                .apellido(medicoRequest.getApellido())
                .telefono(medicoRequest.getTelefono())
                .contrasenia(medicoRequest.getContra())
                .especialidad(medicoRequest.getEspecialidad())
                .matricula(medicoRequest.getMatricula())
                .habilitacion(medicoRequest.getHabilitacion())
                .avatar(medicoRequest.getAvatar()) 
                .build();
    }
}
