package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.controller.response.MedicoResponse;

import java.util.List;

public interface TecnicoService {
   List<MedicoResponse> getTecnicos();

   MedicoResponse getTecnicoById(Integer idTecnico);
}
