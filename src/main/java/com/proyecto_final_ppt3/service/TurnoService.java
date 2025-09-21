package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.Model.Turno;
import com.proyecto_final_ppt3.controller.request.TurnoRequest;
import com.proyecto_final_ppt3.controller.response.TurnoResponse;

import java.util.List;

public interface TurnoService {

    Object guardarTurno (TurnoRequest turno);

    Object updateTurno (Integer id, String option);

    Object updateObservaciones(Integer id, TurnoRequest turno);

    List<TurnoResponse> historialTurnos(Integer idPaciente, Integer opcion);
}
