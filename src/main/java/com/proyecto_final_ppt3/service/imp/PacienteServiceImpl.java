package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Enum.ParentescoEnum;
import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.PacienteResponse;
import com.proyecto_final_ppt3.controller.response.RegistroResponse;
import com.proyecto_final_ppt3.handler.PacienteExistenteException;
import com.proyecto_final_ppt3.handler.PacienteNotFoundException;
import com.proyecto_final_ppt3.handler.UsuarioNotFoundException;
import com.proyecto_final_ppt3.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegistroResponse registrar(UsuarioRequest usuarioRequest) {
        List<Paciente> pacientesExistentes = repository.findByDni(usuarioRequest.getDni());
        if (!pacientesExistentes.isEmpty()) {
            throw new PacienteExistenteException("El paciente ya existe");
        }

        String credencial = "";

        // Caso titular
        if (usuarioRequest.getDniTitular() == 0) {
            String ultimaCredencial = repository.findUltimaCredencial();
            int ultimoNumero;

            if (ultimaCredencial != null && !ultimaCredencial.isBlank()) {
                String[] parts = ultimaCredencial.split("-");
                ultimoNumero = Integer.parseInt(parts[0]);

                if (parts.length == 4) {
                    ultimoNumero = ultimoNumero + 1;
                    parts[0] = String.format("%011d", ultimoNumero);
                    parts[2] = String.format("%02d", 0);
                    credencial = String.join("-", parts);
                }
            } else {
                String padded = String.format("%018d", 0);

                credencial = String.format("%s-%s-%s-%s",
                        padded.substring(0, 11),
                        padded.substring(11, 14),
                        padded.substring(14, 16),
                        padded.substring(16, 18)
                );

                String[] parts = credencial.split("-");
                parts[0] = String.format("%011d", 1);
                credencial = String.join("-", parts);
            }
        }
        // Caso familiar / dependiente
        else {
            List<Paciente> pacienteTitular = repository.findByDni(usuarioRequest.getDniTitular());
            credencial = pacienteTitular.get(0).getCredencial();

            String[] parts = credencial.split("-");
            if (parts.length == 4) {
                Integer parentesco = ParentescoEnum.obtenerCodigoPorNombre(usuarioRequest.getParentesco());
                parts[2] = String.format("%02d", parentesco);
                credencial = String.join("-", parts);
            }
        }

        Paciente paciente = Paciente.fromUsuarioRequest(usuarioRequest, credencial);
        paciente.setContrasenia(passwordEncoder.encode(usuarioRequest.getContra()));

        try {
            repository.save(paciente);
            return new RegistroResponse("ok");
        } catch (Exception e) {
            return new RegistroResponse("Error en registro");
        }
    }

    @Override
    public List<PacienteResponse> pacienteById(Integer idPaciente) {
        List<Paciente> pacientes = repository.findByDni(idPaciente);
        return pacientes.stream()
                .map(PacienteResponse::fromPaciente)
                .toList();
    }

    @Override
    public PacienteResponse buscarPorDni(Integer dni) {
        List<Paciente> pacientes = repository.findByDni(dni);
        if (pacientes.isEmpty()) {
            throw new PacienteNotFoundException("El paciente no existe");
        }
        return PacienteResponse.fromPaciente(pacientes.get(0));
    }

    @Override
    public PacienteResponse updatedPaciente(UsuarioRequest usuarioRequest) {
        Paciente paciente = repository.findById(usuarioRequest.getId())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        paciente.setNombre(usuarioRequest.getNombre());
        paciente.setApellido(usuarioRequest.getApellido());
        paciente.setEmail(usuarioRequest.getEmail());
        paciente.setTelefono(usuarioRequest.getTelefono());
        paciente.setAvatar(usuarioRequest.getAvatar());
        paciente.setDni(usuarioRequest.getDni());

        repository.save(paciente);
        return PacienteResponse.fromPaciente(paciente);
    }
}
