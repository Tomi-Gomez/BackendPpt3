package com.proyecto_final_ppt3.Repository;

import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.controller.request.UsuarioRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrativoRepository extends JpaRepository<Administrativo, Integer> {
}
