package com.proyecto_final_ppt3.Repository;

import com.proyecto_final_ppt3.Model.Administrativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministrativoRepository extends JpaRepository<Administrativo, Integer> {
    List<Administrativo> findByDniAndContrasenia(Integer dni, String contra);
    List<Administrativo> findByDni(Integer dni);
}
