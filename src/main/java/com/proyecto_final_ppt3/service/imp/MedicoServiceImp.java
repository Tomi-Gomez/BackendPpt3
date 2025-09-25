package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Repository.MedicoRespository;
import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;
import com.proyecto_final_ppt3.handler.MedicoNotFoundException;
import com.proyecto_final_ppt3.handler.MedicosNotFoundException;
import com.proyecto_final_ppt3.service.MedicoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MedicoServiceImp implements MedicoService {

    @Autowired
    private MedicoRespository medicoRespository;


    //lo cambie porque buscaba el dni
    @Override
    public List<MedicoResponse> medicosById(Integer idMedico) {
        List<Medico> medicos = medicoRespository.findByDni(idMedico);

        if (medicos.isEmpty()) {
            throw new MedicoNotFoundException(idMedico);
        }
        return medicos.stream().map(MedicoResponse::fromMedico).toList();
    }

    @Override
    public List<MedicoResponse> getMedicos() {
        List<Medico> medicos = medicoRespository.findAll();
        try {
            return medicos.stream()
                    .map(MedicoResponse::fromMedico)
                    .toList();
        } catch (Exception e) {
            throw new MedicosNotFoundException("Error en la busqueda de disponibilidades " + e); // (Chequear que este bien)
        }
    }

    @Override
    public MedicoResponse updatedMedico(MedicoRequest medicoRequest) {
        Medico medico = medicoRespository.findById(medicoRequest.getId())
                .orElseThrow(() ->
                        new MedicoNotFoundException(medicoRequest.getId()));

        medico.setNombre(medicoRequest.getNombre());
        medico.setApellido(medicoRequest.getApellido());
        medico.setEmail(medicoRequest.getEmail());
        medico.setTelefono(medicoRequest.getTelefono());
        medico.setAvatar(medicoRequest.getAvatar());
        medico.setEspecialidad(medicoRequest.getEspecialidad());

        medicoRespository.save(medico);

        return MedicoResponse.fromMedico(medico);
    }
}
