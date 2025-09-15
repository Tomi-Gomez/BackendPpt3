package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.Repository.AdministrativoRepository;
import com.proyecto_final_ppt3.controller.request.DisponibilidadRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.service.AdministrativoService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdministrativoServiceImp implements AdministrativoService {

    private AdministrativoRepository repository;

    @Override
    public Administrativo insertarAdmin(Administrativo usuario) {
        try {
            return repository.save(usuario);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object postGuardarDisponibilidad(Map<String, DisponibilidadRequest> disponibilidad) {
        return null;
    }
}
