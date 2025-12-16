package com.proyecto_final_ppt3.controller;

import com.proyecto_final_ppt3.Model.Administrativo;
import com.proyecto_final_ppt3.controller.request.AdministrativoRequest;
import com.proyecto_final_ppt3.controller.request.MedicoRequest;
import com.proyecto_final_ppt3.controller.response.MedicoResponse;
import com.proyecto_final_ppt3.service.AdministrativoService;
import com.proyecto_final_ppt3.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdministrativoService administrativo;

    @Autowired
    private ReportService reportService;

    @PostMapping("/insertarAdmin")
    public Administrativo insertarAdmin(@RequestBody  AdministrativoRequest administrativoRequest) {
        return administrativo.insertarAdmin(administrativoRequest);
    }

    @PostMapping("/insertarMed")
    public MedicoResponse insertarMedico(@RequestBody MedicoRequest medicoRequest) {
        return administrativo.insertarMedico(medicoRequest);
    }

    @GetMapping("/reporteMedico")
    public ResponseEntity<byte[]> generarReporteTurnosxMedico(
            @RequestParam("inicio") String fechaInicio,
            @RequestParam("fin") String fechaFin) {

        byte[] pdf = reportService.generarReporteTurnosxMedico(fechaInicio, fechaFin);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=reporte_medicos.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("/reporteMedicoXDia")
    public ResponseEntity<byte[]> generarReporteMedicosXDia(
            @RequestParam("dia") String dia) {

        byte[] pdf = reportService.generarReporteMedicoXDia(dia);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=reporte_medicos.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("/reporteMedicoXCancelado")
    public ResponseEntity<byte[]> generarReporteTurnosXCancelado(
            @RequestParam("inicio") String fechaInicio,
            @RequestParam("fin") String fechaFin) {

        byte[] pdf = reportService.generarReporteTurnosXCancelado(fechaInicio, fechaFin);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=reporte_medicos.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
