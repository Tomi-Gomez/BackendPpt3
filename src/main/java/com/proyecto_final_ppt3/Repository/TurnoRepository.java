package com.proyecto_final_ppt3.Repository;

import com.proyecto_final_ppt3.Model.DTO.TurnoDTO;
import com.proyecto_final_ppt3.Model.Turno;
import com.proyecto_final_ppt3.dto.TurnoDetalleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {

    List<Turno> findByIdPaciente(Integer idPaciente);

    List<Turno> findByIdPacienteAndEstado(Integer idPaciente, String estado);

    @Query(value = """
        SELECT T.id AS id,
               T.fecha AS fecha,
               CONCAT(A.nombre, ' ', A.apellido) AS paciente,
               A.dni AS dniPaciente,
               CONCAT(M.nombre, ' ', M.apellido) AS medico,
               T.hora AS hora,
               T.estado AS estado
        FROM turno T
        INNER JOIN paciente A ON A.id = T.id_paciente
        INNER JOIN medico M ON M.id = T.id_medico
        WHERE M.id = :medicoId
        """, nativeQuery = true
    )
    List<TurnoDetalleProjection> findTurnosDetalleByMedicoId(@Param("medicoId") Integer medicoId);

    List<Turno> findByIdMedicoAndFecha(Integer idMedico, String fecha);

    List<Turno> findByIdMedico(Integer idMedico);

    List<Turno> findByFecha(String fecha);

    @Query("SELECT t FROM Turno t WHERE t.fecha BETWEEN :inicio AND :fin")
    List<Turno> findTurnosByFechaBetween(@Param("inicio") String fechaInicio, @Param("fin") String fechaFin);

    List<Turno> findByIdPacienteAndEstadoNot(Integer idPaciente, String estado);
}
