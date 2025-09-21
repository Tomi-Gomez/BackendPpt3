package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Turno;
import com.proyecto_final_ppt3.Repository.TurnoRepository;
import com.proyecto_final_ppt3.controller.request.TurnoRequest;
import com.proyecto_final_ppt3.controller.response.TurnoResponse;
import com.proyecto_final_ppt3.service.PacienteService;
import com.proyecto_final_ppt3.service.TurnoService;
import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TurnoServiceImpl implements TurnoService {

    private TurnoRepository turnoRepository;

    @Override
    public Object guardarTurno(TurnoRequest turno) {
        return null;
    }

    @Override
    public Object updateTurno(Integer id, String option) {
        return null;
    }

    @Override
    public Object updateObservaciones(Integer id, TurnoRequest turno) {
        return null;
    }

    @Override
    public List<TurnoResponse> historialTurnos(Integer idPaciente, Integer opcion) {
        List<Turno> turnos;

        if (opcion == 1) {
            turnos = turnoRepository.findByIdPaciente(idPaciente);
        } else {
            turnos = turnoRepository.findByIdPacienteAndEstado(idPaciente, "confirmado");
        }

        return turnos.stream().map(TurnoResponse::fromTurno).toList();
    }

}
