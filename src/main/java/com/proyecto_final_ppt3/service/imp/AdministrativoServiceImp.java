package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.Model.Disponibilidad;
import com.proyecto_final_ppt3.Repository.AdministrativoRepository;
import com.proyecto_final_ppt3.Repository.MedicoRespository;
import com.proyecto_final_ppt3.controller.request.DisponibilidadRequest;
import com.proyecto_final_ppt3.service.AdministrativoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AdministrativoServiceImp implements AdministrativoService {

    private MedicoRespository MedicoRespository;

    private AdministrativoRepository repository;

    @Override
    public Administrativo insertarAdmin(Administrativo usuario) {
        try {
            return repository.save(usuario);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }




}
