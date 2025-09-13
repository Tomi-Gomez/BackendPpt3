package com.proyecto_final_ppt3.service;


import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.controller.request.DisponibilidadRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;

import java.util.Map;

public interface AdministrativoService {

    Administrativo insertarAdmin(Administrativo usuario);


    Object postGuardarDisponibilidad(Map<String, DisponibilidadRequest> disponibilidad);

}
