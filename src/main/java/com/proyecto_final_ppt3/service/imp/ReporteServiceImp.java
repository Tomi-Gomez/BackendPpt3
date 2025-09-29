package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Model.Turno;
import com.proyecto_final_ppt3.Repository.MedicoRespository;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.service.ReportService;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ReporteServiceImp implements ReportService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRespository medicoRespository;

    @Override
    public byte[] generarReporteTurno(Turno turno) {
        try {

            Optional<Paciente> paciente = pacienteRepository.findById(turno.getIdPaciente());
            Optional<Medico> medico = medicoRespository.findById(turno.getIdMedico());

            // cargar el jrxml como InputStream desde resources
            InputStream reportStream = new ClassPathResource("templates/report/ReportTurno.jrxml").getInputStream();
            JasperReport report = JasperCompileManager.compileReport(reportStream);

            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("TURNO_ID", turno.getId());
            parameters.put("FECHA_ACTUAL", fechaActual.format(dateFormatter));
            parameters.put("HORA_ACTUAL", LocalTime.now().format(timeFormatter));
            parameters.put("PACIENTE", paciente.get().getNombre() + " " + paciente.get().getApellido());
            parameters.put("ESPECIALIDAD", turno.getEspecialidad());
            parameters.put("FECHA_TURNO", turno.getFecha());
            parameters.put("HORA_TURNO", turno.getHora());
            parameters.put("MEDICO", medico.get().getNombre() + " " + medico.get().getApellido());
            parameters.put("imageDir", "classpath:/static/images/");

            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

            // exportar a bytes en lugar de archivo
            return JasperExportManager.exportReportToPdf(print);

        } catch (Exception e) {
            throw new RuntimeException("No se encontro el paciente solicitado" + e);
        }

    }
}
