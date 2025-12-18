package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Model.DTO.ReporteCanceladoDTO;
import com.proyecto_final_ppt3.Model.DTO.ReporteDiaDTO;
import com.proyecto_final_ppt3.Model.DTO.ReporteMedicoDTO;
import com.proyecto_final_ppt3.Model.Disponibilidad;
import com.proyecto_final_ppt3.Model.Medico;
import com.proyecto_final_ppt3.Model.Paciente;
import com.proyecto_final_ppt3.Model.Turno;
import com.proyecto_final_ppt3.Repository.DisponibilidadRepository;
import com.proyecto_final_ppt3.Repository.MedicoRespository;
import com.proyecto_final_ppt3.Repository.PacienteRepository;
import com.proyecto_final_ppt3.Repository.TurnoRepository;
import com.proyecto_final_ppt3.handler.PacienteNotFoundException;
import com.proyecto_final_ppt3.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReporteServiceImp implements ReportService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRespository medicoRespository;
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

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
            parameters.put("PROFESIONAL",medico.get().getTipoUsuario());
            parameters.put("ESPECIALIDAD", turno.getEspecialidad());
            parameters.put("FECHA_TURNO", turno.getFecha());
            parameters.put("HORA_TURNO", turno.getHora());
            parameters.put("MEDICO", medico.get().getNombre() + " " + medico.get().getApellido());
            parameters.put("imageDir", "classpath:/static/images/");
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

            return JasperExportManager.exportReportToPdf(print);
        } catch (PacienteNotFoundException e) {
            log.error(e.getMessage());
            throw new PacienteNotFoundException("No se encontro el paciente solicitado" + e);
        } catch (IOException | JRException e) {
            log.error(e.getMessage());
	        throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] generarReporteTurnosxMedico(String fechaInicio, String fechaFinal) {
        try {
            InputStream reportStream = new ClassPathResource("templates/report/ReporteTurnosPorMedico.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            List<Turno> listaTurnoReporte = turnoRepository.findTurnosByFechaBetween(fechaInicio, fechaFinal);
            Map<Integer, Medico> medicosMap = medicoRespository.findAll()
                    .stream()
                    .collect(Collectors.toMap(Medico::getId, m -> m));
            Map<Medico, Long> cantidadTurnoXMedico = listaTurnoReporte.parallelStream()
                    .map(t -> medicosMap.get(t.getIdMedico()))
                    .filter(Objects::nonNull)
                    .collect(Collectors.groupingBy(m -> m, Collectors.counting()));
            List<ReporteMedicoDTO> reporte = cantidadTurnoXMedico.entrySet()
                    .stream()
                    .map(entry -> new ReporteMedicoDTO(
                            entry.getKey().getNombre() + " " + entry.getKey().getApellido(),
                            capitalizar(entry.getKey().getTipoUsuario()),
                            entry.getKey().getEspecialidad(),
                            entry.getValue().intValue()
                    ))
                    .toList();

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reporte);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("FECHA_ACTUAL", fechaActual.format(dateFormatter));
            parameters.put("HORA_ACTUAL", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
            parameters.put("DATOS_REPORTE", dataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Error al generar reporte de turnos por médico: " + e.getMessage(), e);
        }
    }

    private String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    @Override
    public byte[] generarReporteMedicoXDia(String dia) {
        try {
            InputStream reportStream = new ClassPathResource("templates/report/ReporteDia.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            List<Disponibilidad> disponibilidades = disponibilidadRepository.findAll();
            List<Disponibilidad> filtroDisponibilidades = disponibilidades.stream()
                    .filter(p -> p.getDias() != null && Arrays.asList(p.getDias().split(",")).contains(dia))
                    .toList();
            Map<String, List<Disponibilidad>> agrupadoPorEspecialidad = filtroDisponibilidades.stream()
                    .collect(Collectors.groupingBy(Disponibilidad::getEspecialidad));
            List<ReporteDiaDTO> reporte = agrupadoPorEspecialidad.entrySet().stream()
                    .map(entry -> new ReporteDiaDTO(
                            entry.getValue().stream()
                                    .map(Disponibilidad::getMedico)
                                    .map(Medico::getNombre).distinct()
                                    .collect(Collectors.joining(", ")) , // lista de médicos
                            entry.getKey()) // especialidad
                    )
                    .toList();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("FECHA_ACTUAL", LocalDate.now().toString());
            parameters.put("HORA_ACTUAL", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
            parameters.put("DATOS_REPORTE", dataSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public byte[] generarReporteTurnosXCancelado(String fechaInicio, String fechaFinal) {
        try {
            InputStream reportStream = new ClassPathResource("templates/report/ReporteTurnosCancelados.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date periodoInicial = formato.parse(fechaInicio);
            Date periodoFinal = formato.parse(fechaFinal);

            List<Turno> turnosCancelados = turnoRepository.findAll().stream()
                    .filter(t -> {
                        try {
                            Date fechaTurno = formato.parse(t.getFecha());
                            return !fechaTurno.before(periodoInicial)
                                    && !fechaTurno.after(periodoFinal)
                                    && "cancelado".equalsIgnoreCase(t.getEstado());
                        } catch (Exception e) {
                            return false;
                        }
                    })
                    .collect(Collectors.toList());

            List<ReporteCanceladoDTO> reporte = new ArrayList<>();
            for (Turno t : turnosCancelados) {
                String nombreMedico = medicoRespository.findById(t.getIdMedico())
                        .map(m -> m.getNombre() + " " + m.getApellido())
                        .orElse("Desconocido");
                String nombrePaciente = pacienteRepository.findById(t.getIdPaciente())
                        .map(p -> p.getNombre() + " " + p.getApellido())
                        .orElse("Desconocido");
                reporte.add(new ReporteCanceladoDTO(
                        t.getFecha(),
                        t.getHora(),
                        nombreMedico,
                        nombrePaciente
                ));
            }

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("FECHA_ACTUAL", LocalDate.now().toString());
            parameters.put("HORA_ACTUAL", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
            parameters.put("DATOS_REPORTE", dataSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Error al generar reporte de turnos cancelados: " + e.getMessage(), e);
        }
    }
}
