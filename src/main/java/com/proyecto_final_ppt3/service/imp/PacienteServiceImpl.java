package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.PacienteResponse;
import com.proyecto_final_ppt3.controller.response.RegistroResponse;
import com.proyecto_final_ppt3.handler.PacienteExistenteException;
import com.proyecto_final_ppt3.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private PacienteRepository repository;

    @Override
    public RegistroResponse registrar(UsuarioRequest usuarioRequest) {
        List<Paciente> paciente1 = repository.findByDni(usuarioRequest.getDni());

        if (!paciente1.isEmpty()) {
            throw new PacienteExistenteException("El paciente ya existe");
        }

        String ultimaCredencial = repository.findUltimaCredencial();
        int ultimoNumero = 0;

        if (ultimaCredencial != null && !ultimaCredencial.isBlank()) {

            ultimoNumero = Integer.parseInt(ultimaCredencial.replaceAll("\\s", ""));
        }

        int siguiente = ultimoNumero + 1;

        String padded = String.format("%012d", siguiente);

        String nuevaCredencial = String.format("%s %s %s %s",
                padded.substring(0, 2),
                padded.substring(2, 4),
                padded.substring(4, 8),
                padded.substring(8, 12)
        );

        Paciente paciente = Paciente.fromUsuarioRequest(usuarioRequest, nuevaCredencial);
        try {
            repository.save(paciente);
        } catch (Exception e) {
            return new RegistroResponse("Error en registro");
        }

        return new RegistroResponse("ok");
    }

    @Override
    public List<PacienteResponse> pacienteById(Integer idPaciente) {
        List<Paciente> pacientes = repository.findByDni(idPaciente);
        return pacientes.stream().map(PacienteResponse::fromPaciente).toList();
    }
}
