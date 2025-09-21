package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.Model.Turno;
import com.proyecto_final_ppt3.controller.request.TurnoRequest;
import com.proyecto_final_ppt3.controller.response.TurnoDetalleResponse;
import com.proyecto_final_ppt3.controller.response.TurnoResponse;

import java.util.List;

public interface TurnoService {

    TurnoResponse guardarTurno (TurnoRequest turno);

    TurnoResponse updateTurno (Integer id, String option);

    TurnoResponse updateObservaciones(Integer idTurno, TurnoRequest turno);

    List<TurnoResponse> historialTurnos(Integer idPaciente, Integer opcion);

    List<TurnoResponse> getTurnosMedicos(Integer medicoTurnos, String dia);

    List<TurnoDetalleResponse> getTurnosTomadosCSV(Integer dniMedico);

    List<TurnoResponse> historialTurnosMed(Integer idMedico);
}
