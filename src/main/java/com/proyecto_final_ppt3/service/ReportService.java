package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.Model.Turno;
import org.springframework.stereotype.Service;

@Service
public interface ReportService {
    byte[] generarReporteTurno(Turno turno);
    byte[] generarReporteTurnosxMedico(String fechaInicio, String fechaFinal);
    byte[] generarReporteMedicoXDia(String dia);
    byte[] generarReporteTurnosXCancelado(String fechaInicio, String fechaFinal);
}

