package com.proyecto_final_ppt3.service.imp;

import com.proyecto_final_ppt3.Enum.DiasEnum;
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
import com.proyecto_final_ppt3.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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


    @Override
    public byte[] generarReporteTurnosxMedico(String fechaInicio, String fechaFinal) {
        try {
            //Cargar el jrxml como InputStream desde resources
            InputStream reportStream = new ClassPathResource("templates/report/ReporteMedico.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            //Traigo la fecha y hora Actual
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            Date periodoInicialReporte = formato.parse(fechaInicio);
            Date periodoFinalReporte = formato.parse(fechaFinal);

            List<Turno> listaTotalTurno = turnoRepository.findAll();
            List<Turno> listaTurnoReporte = new ArrayList<>();

            for (Turno l : listaTotalTurno){
                String f = l.getFecha();
                Date fecha = formato.parse(f);

                if (!fecha.before(periodoInicialReporte) && !fecha.after(periodoFinalReporte)) {
                    listaTurnoReporte.add(l);
                }
            }

            // Creo un map donde le asigno al medico la cantidad de turnos que tuvo en el periodo seleccionado
            Map<Medico,Integer> cantidadTurnoXMedico = new HashMap<>();

            for (Turno t : listaTurnoReporte){
                Optional<Medico> m = medicoRespository.findById(t.getIdMedico());
                Medico medico = null;

                if (m.isPresent()){
                    medico = m.get();
                }
                cantidadTurnoXMedico.put(medico,cantidadTurnoXMedico.getOrDefault(medico,0) + 1 );
            }

            // Creo una lista donde añado el map anterior y le agrego la espcialidad por ReporteMedicoDTO
            List<ReporteMedicoDTO> reporte = new ArrayList<>();
            for(Map.Entry<Medico,Integer> entry : cantidadTurnoXMedico.entrySet()){
                Medico m = entry.getKey();
                int cantidad = entry.getValue();

                reporte.add(new ReporteMedicoDTO(
                        m.getNombre(),
                        m.getEspecialidad(),
                        cantidad
                ));
            }

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reporte);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("FECHA_ACTUAL", fechaActual.format(dateFormatter));
            parameters.put("HORA_ACTUAL", LocalTime.now().format(timeFormatter));
            parameters.put("DATOS_REPORTE", dataSource);


            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch(Exception e){
            throw new RuntimeException("No se encontro el paciente solicitado" + e);
        }
    }

    @Override
    public byte[] generarReporteMedicoXDia(String dia) {
        try {
            //Cargar el jrxml como InputStream desde resources
            InputStream reportStream = new ClassPathResource("templates/report/ReporteDia.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            List<Disponibilidad> disponibilidades = disponibilidadRepository.findAll();

            List<Disponibilidad> filtroDisponibilidades = disponibilidades.stream()
                    .filter(p -> p.getDias() != null && Arrays.asList(p.getDias().split(",")).contains(dia))
                    .toList();

            // key seria la especialidad y el valor seria la lista de la disponibilidad
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
            parameters.put("DATOS_REPORTE", dataSource);
            parameters.put("diaParametro", DiasEnum.obtenerDiaEnteroPorDiaCortado(dia));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch(Exception e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
