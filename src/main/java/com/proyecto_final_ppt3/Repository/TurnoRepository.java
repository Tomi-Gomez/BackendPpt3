package com.proyecto_final_ppt3.Repository;

import com.proyecto_final_ppt3.Model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {

    List<Turno> findByIdPaciente(Integer idPaciente);

    List<Turno> findByIdPacienteAndEstado(Integer idPaciente, String estado);
}
