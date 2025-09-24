package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.DTO.TurnoDTO;
import com.proyecto_final_ppt3.Model.Turno;
import com.proyecto_final_ppt3.Repository.TurnoRepository;
import com.proyecto_final_ppt3.controller.request.TurnoRequest;
import com.proyecto_final_ppt3.controller.response.TurnoDetalleResponse;
import com.proyecto_final_ppt3.controller.response.TurnoResponse;
import com.proyecto_final_ppt3.dto.TurnoDetalleProjection;
import com.proyecto_final_ppt3.service.TurnoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TurnoServiceImpl implements TurnoService {

    private TurnoRepository turnoRepository;

    @Override
    public TurnoResponse guardarTurno(TurnoRequest turnoRequest) {
        Turno turno = TurnoRequest.toTurno(turnoRequest);

        turnoRepository.save(turno);
        return TurnoResponse.fromTurno(turno);
    }

    @Override
    public TurnoResponse updateTurno(Integer idTurno, String option) {
        Turno turno = turnoRepository.findById(idTurno).get();

        //aca se hace con el dto pero como no hay tiempo hay que hacer la vista gorda
        turno.setEstado(option);
        turnoRepository.save(turno);

        return TurnoResponse.fromTurno(turno);
    }

    @Override
    public TurnoResponse updateObservaciones(Integer idTurno, TurnoRequest turnoRequest) {
        Turno turno = turnoRepository.findById(idTurno).get();

        //aca se hace con el dto pero como no hay tiempo hay que hacer la vista gorda
        turno.setObservaciones(turnoRequest.getObservaciones());
        turnoRepository.save(turno);

        return TurnoResponse.fromTurno(turno);
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

    @Override
    public List<TurnoResponse> getTurnosMedicos(Integer idMedico, String fecha) {
        List<Turno> turnos =  turnoRepository.findByIdMedicoAndFecha(idMedico, fecha);
        return turnos.stream().map(TurnoResponse::fromTurno).toList();
    }

    @Override
    public List<TurnoDetalleResponse> getTurnosTomadosCSV(Integer dniMedico) {
        List<TurnoDetalleProjection> turnoDetalleDTO  = turnoRepository.findTurnosDetalleByMedicoId(dniMedico);

        return turnoDetalleDTO.stream().map(TurnoDetalleResponse::fromTurnoDetalleProjection).toList();
    }

    @Override
    public List<TurnoResponse> historialTurnosMed(Integer idMedico) {
        List<Turno> turnos = turnoRepository.findByIdMedico(idMedico);
        return turnos.stream().map(TurnoResponse::fromTurno).toList();
    }

}
