package com.proyecto_final_ppt3.controller.response;
import com.proyecto_final_ppt3.Model.Medico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class MedicoResponse extends UsuarioResponse {
    private String matricula;
    private String especialidad;
    private String habilitacion;
    private String avatar;

    public static MedicoResponse fromMedico(Medico medico){
        return MedicoResponse.builder()
                .id(medico.getId())
                .nombre(medico.getNombre())
                .apellido(medico.getApellido())
                .dni(medico.getDni())
                .email(medico.getEmail())
                .contrasenia(medico.getContrasenia())
                .tipoUsuario(medico.getTipoUsuario())
                .telefono(medico.getTelefono())
                .matricula(medico.getMatricula())
                .especialidad(medico.getEspecialidad())
                .habilitacion(medico.getHabilitacion())
                .avatar(medico.getAvatar())
                .build();

    }
}
