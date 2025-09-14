package com.proyecto_final_ppt3.Repository;

import com.proyecto_final_ppt3.Model.Disponibilidad;
import com.proyecto_final_ppt3.Model.Medico;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DisponibilidadRepository extends CrudRepository<Disponibilidad, Long> {

    List<Disponibilidad> findByEspecialidad(String especialidad);
}
