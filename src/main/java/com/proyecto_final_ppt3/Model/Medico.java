package com.proyecto_final_ppt3.Model;

import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Medico extends Usuario{
    private String matricula;
    private String especialidad;
    private String habilitacion;

    //Chequear que en el back de nico no inserta el avatar a la hora de registrar..... pero si llega del front

    public static Medico fromUsuarioRequest(MedicoRequest medicoRequest) {
        return Medico.builder()
                .id(medicoRequest.getId())
                .email(medicoRequest.getEmail())
                .dni(medicoRequest.getDni())
                .nombre(medicoRequest.getNombre())
                .apellido(medicoRequest.getApellido())
                .telefono(medicoRequest.getTelefono())
                .contrasenia(medicoRequest.getContra())
                .especialidad(medicoRequest.getEspecialidad())
                .matricula(medicoRequest.getMatricula())
                .habilitacion(medicoRequest.getHabilitacion())
                .build();
    }
}
