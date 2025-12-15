package com.proyecto_final_ppt3.service;


import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.controller.request.AdministrativoRequest;
import com.proyecto_final_ppt3.controller.request.DisponibilidadRequest;
import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;

public interface AdministrativoService {
    Administrativo insertarAdmin(AdministrativoRequest administrativoRequest);
    MedicoResponse insertarMedico(MedicoRequest medico);
}
