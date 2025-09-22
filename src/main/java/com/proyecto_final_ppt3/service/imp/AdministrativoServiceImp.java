package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Repository.AdministrativoRepository;
import com.proyecto_final_ppt3.Repository.MedicoRespository;
import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;
import com.proyecto_final_ppt3.handler.AdministrativoInsertException;
import com.proyecto_final_ppt3.handler.MedicosNotFoundException;
import com.proyecto_final_ppt3.service.AdministrativoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AdministrativoServiceImp implements AdministrativoService {

    private MedicoRespository medicoRespository;

    private AdministrativoRepository repository;

    //Cheuqear si esto esta bien, porque deberia poder
    @Override
    public Administrativo insertarAdmin(Administrativo administrativo) {
        try {
            return repository.save(administrativo);
        }catch (Exception e){
            throw new AdministrativoInsertException("Error al insertar un administrativo" + e.getMessage());
        }
    }

    @Override
    public MedicoResponse insertarMedico(MedicoRequest medicoRequest) {
        try{
            Medico medico = Medico.fromUsuarioRequest(medicoRequest);
            Medico medicoGuardado = medicoRespository.save(medico);
            return MedicoResponse.fromMedico(medicoGuardado);
        }catch (Exception e){
            throw new MedicosNotFoundException("Error al insertar el medico" + e);
        }
    }


}
