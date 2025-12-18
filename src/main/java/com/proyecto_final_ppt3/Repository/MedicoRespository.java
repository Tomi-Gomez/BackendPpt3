package com.proyecto_final_ppt3.Repository;

import com.proyecto_final_ppt3.Model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicoRespository extends JpaRepository<Medico, Integer> {
    List<Medico> findByDniAndContrasenia(Integer dni, String contra);
    List<Medico> findByDni(Integer dni);
}
