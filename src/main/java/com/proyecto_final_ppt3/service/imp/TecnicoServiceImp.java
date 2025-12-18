package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Repository.MedicoRespository;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;
import com.proyecto_final_ppt3.handler.TecnicoNotFoundException;
import com.proyecto_final_ppt3.handler.TecnicosNotFoundException;
import com.proyecto_final_ppt3.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class TecnicoServiceImp implements TecnicoService {
	@Autowired
	private MedicoRespository medicoRespository;

	@Override
	public List<MedicoResponse> getTecnicos() {
		try {
			List<Medico> listTecnicos = medicoRespository.findAll()
					                            .stream()
					                            .filter(t->"Tecnico".equalsIgnoreCase(t.getTipoUsuario()))
					                            .toList();
			return listTecnicos.stream().map(MedicoResponse::fromMedico).toList();
		} catch (TecnicosNotFoundException e) {
			log.error(e.getMessage());
			throw new TecnicosNotFoundException("No se encontraron tecnicos");
		}
	}

	@Override
	public MedicoResponse getTecnicoById(Integer idTecnico) {
		try {
			Medico tecnico = medicoRespository.getReferenceById(idTecnico);
			return MedicoResponse.fromMedico(tecnico);
		}catch (TecnicoNotFoundException e) {
			log.error(e.getMessage());
			throw new TecnicoNotFoundException(idTecnico);
		}
	}
}
