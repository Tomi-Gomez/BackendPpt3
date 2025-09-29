package com.proyecto_final_ppt3.service;

import com.proyecto_final_ppt3.Model.Turno;
import org.springframework.stereotype.Service;

@Service
public interface ReportService {
    byte[] generarReporteTurno(Turno turno);
}
