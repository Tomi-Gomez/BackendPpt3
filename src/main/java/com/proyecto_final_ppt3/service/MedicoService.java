package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MedicoService {
    List<MedicoResponse> medicosById(Integer idMedico);
    List<MedicoResponse> getMedicos();
    MedicoResponse updatedMedico(MedicoRequest medicoRequest);
    MedicoResponse updateMedicoHabilitacion(MedicoRequest medicoRequest);
}
