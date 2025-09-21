package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Repository.MedicoRespository;
import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;
import com.proyecto_final_ppt3.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MedicoServiceImp implements MedicoService {

    @Autowired
    private MedicoRespository medicoRespository;



    @Override
    public MedicoResponse medicosById(Integer idMedico) {
        return medicoRespository.findById(idMedico)
                .map(MedicoResponse::fromMedico)
                .orElse(null); //Armar el handler
    }

    @Override
    public List<MedicoResponse> getMedicos() {
        List<Medico> medicos = medicoRespository.findAll();
        try {
            return medicos.stream()
                    .map(MedicoResponse::fromMedico)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error en la busqueda de disponibilidades " + e);
        }
    }

    @Override
    public MedicoResponse updatedMedico(MedicoRequest medicoRequest) {
        Medico medico = medicoRespository.findById(medicoRequest.getId())
                .orElseThrow(() -> new RuntimeException(
                        "Médico no encontrado con id " + medicoRequest.getId()));

        medico.setNombre(medicoRequest.getNombre());
        medico.setApellido(medicoRequest.getApellido());
        medico.setDni(medicoRequest.getDni());
        medico.setEmail(medicoRequest.getEmail());
        medico.setContrasenia(medicoRequest.getContra());
        medico.setTelefono(medicoRequest.getTelefono());
        medico.setAvatar(medicoRequest.getAvatar());
        medico.setMatricula(medicoRequest.getMatricula());
        medico.setEspecialidadMedica(medicoRequest.getEspecialidad());

        medicoRespository.save(medico);

        return MedicoResponse.fromMedico(medico);
    }
}
