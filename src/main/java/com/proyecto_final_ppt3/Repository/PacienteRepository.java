package com.proyecto_final_ppt3.Repository;

import com.proyecto_final_ppt3.Model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Integer> {
    List<Paciente> findByDni(Integer dni);
    Optional<Paciente> findByEmail(String email);
    @Query("SELECT MAX(p.credencial) FROM Paciente p")
    String findUltimaCredencial();
}
