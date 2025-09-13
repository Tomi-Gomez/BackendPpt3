package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.controller.request.TurnoRequest;

public interface TurnoService {

    Object guardarTurno (TurnoRequest turno);

    Object updateTurno (Integer id, String option);

    Object updateObservaciones(Integer id, TurnoRequest turno);
}
