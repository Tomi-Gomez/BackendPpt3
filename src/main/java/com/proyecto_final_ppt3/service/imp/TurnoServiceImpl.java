package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Model.Turno;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.Repository.TurnoRepository;
import com.proyecto_final_ppt3.controller.request.TurnoRequest;
import com.proyecto_final_ppt3.controller.response.TurnoDetalleResponse;
import com.proyecto_final_ppt3.controller.response.TurnoResponse;
import com.proyecto_final_ppt3.dto.TurnoDetalleProjection;
import com.proyecto_final_ppt3.service.EmailService;
import com.proyecto_final_ppt3.service.TurnoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.proyecto_final_ppt3.Repository.MedicoRespository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class TurnoServiceImpl implements TurnoService {

    private TurnoRepository turnoRepository;

    private ReporteServiceImp reporteServiceImp;

    private EmailService emailService;

    private PacienteRepository pacienteRepository;
    private final MedicoRespository medicoRepository;


    @Override
    public TurnoResponse guardarTurno(TurnoRequest turnoRequest) {
        try {
            Turno turno = Turno.FromTurnoRequest(turnoRequest);

            log.info("Buscando paciente con idPaciente={}", turno.getIdPaciente());

            Turno finalTurno = turno;
            Paciente paciente = pacienteRepository.findById(turno.getIdPaciente())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id " + finalTurno.getIdPaciente()));

            log.info("TurnoRequest recibido: {}", turnoRequest);
            log.info("Turno a guardar: {}", turno);

            // 1. Guardar turno en DB (ya con id asignado)
            turno = turnoRepository.save(turno);

            // 2. Generar PDF
            byte[] pdfBytes = reporteServiceImp.generarReporteTurno(turno);

            // 3. Enviar email al paciente
            emailService.sendTurnoPdfEmail(
                    paciente.getEmail(),
                    pdfBytes,
                    "Turno_" + turno.getId() + ".pdf"
            );

            return TurnoResponse.fromTurno(turno);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar turno: " + e.getMessage(), e);
        }
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
            turnos = turnoRepository.findByIdPacienteAndEstado(idPaciente, "CONFIRMADO");
        } else {
            turnos = turnoRepository.findByIdPacienteAndEstadoNot(idPaciente, "CONFIRMADO");
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

    @Override
     public List<TurnoResponse> getTurnosFecha(String fecha) {
        List<Turno> turnos = turnoRepository.findByFecha(fecha);
        List<TurnoResponse> responses = new ArrayList<>();

        for (Turno t : turnos) {
            Medico medico = medicoRepository.findById(t.getIdMedico()).orElse(null);
            Paciente paciente = pacienteRepository.findById(t.getIdPaciente()).orElse(null);
            TurnoResponse resp = new TurnoResponse();

            resp.setIdTurno(t.getId()); 
            resp.setFecha(t.getFecha());
            resp.setHora(t.getHora());
            resp.setEstado(t.getEstado());
            resp.setEspecialidad(t.getEspecialidad());
            resp.setCalificacion(t.getCalificacion());
            resp.setObservaciones(t.getObservaciones());
            if (medico != null) {
                resp.setNombreMedico(medico.getNombre());
                resp.setApellidoMedico(medico.getApellido());
            }
            if (paciente != null) {
                resp.setNombrePaciente(paciente.getNombre());
                resp.setApellidoPaciente(paciente.getApellido());
            }
            responses.add(resp);
        }
        return responses;
    }

}
