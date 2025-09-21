package com.proyecto_final_ppt3.controller.response;
import com.proyecto_final_ppt3.Enum.EspecialidadMedica;
import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class MedicoResponse extends UsuarioResponse {
    private String matricula;
    private String especialidadMedica;
    private String habilitacion;

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
                .avatar(medico.getAvatar())
                .matricula(medico.getMatricula())
                .especialidadMedica(medico.getEspecialidadMedica())
                .habilitacion(medico.getHabilitacion())
                .build();

    }
}
