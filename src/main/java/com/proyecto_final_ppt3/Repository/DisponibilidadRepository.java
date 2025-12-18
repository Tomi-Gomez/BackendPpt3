package com.proyecto_final_ppt3.Repository;

import com.proyecto_final_ppt3.Model.Disponibilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Integer> {
    List<Disponibilidad> findByEspecialidad(String especialidad);
    List<Disponibilidad> findByMedicoId(Integer idMedico);
}
