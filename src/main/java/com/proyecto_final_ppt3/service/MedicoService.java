package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;
import java.util.List;

public interface MedicoService {
    List<MedicoResponse> medicosById(Integer idMedico);
    List<MedicoResponse> getMedicos();
    MedicoResponse updatedMedico(MedicoRequest medicoRequest);
    MedicoResponse updateMedicoHabilitacion(MedicoRequest medicoRequest);
}
