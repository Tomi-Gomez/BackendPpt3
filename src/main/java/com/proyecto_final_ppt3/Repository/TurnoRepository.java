package com.proyecto_final_ppt3.Repository;

import com.proyecto_final_ppt3.Model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {
}
