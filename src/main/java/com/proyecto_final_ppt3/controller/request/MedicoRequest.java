package com.proyecto_final_ppt3.controller.request;

import com.proyecto_final_ppt3.Model.Medico;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoRequest {
    private Integer id;
    private String email;
    private Integer dni;
    private String nombre;
    private String apellido;
    private Integer telefono;
    private String contra;
    private String especialidad;
    private String matricula;
    private String habilitacion;
    private String avatar;

    public static Medico toMedico(MedicoRequest medicoRequest){
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
                .build();
    }
}
