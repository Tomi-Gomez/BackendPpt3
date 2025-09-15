package com.proyecto_final_ppt3.Repository;

import com.proyecto_final_ppt3.Model.Disponibilidad;
import com.proyecto_final_ppt3.Model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Integer> {
}
