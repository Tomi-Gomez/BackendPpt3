package com.proyecto_final_ppt3.Repository;

import com.proyecto_final_ppt3.Model.Medico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRespository extends CrudRepository<Medico, Long> {
}
