package com.proyecto_final_ppt3.Repository;

import com.proyecto_final_ppt3.Model.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente,Long> {
}
