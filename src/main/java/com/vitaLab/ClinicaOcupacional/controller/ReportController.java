package com.vitaLab.ClinicaOcupacional.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vitaLab.ClinicaOcupacional.service.impl.ReportServiceImpl;

@Controller
@RequestMapping("/reportes")
public class ReportController {

    private final ReportServiceImpl reportServiceImpl;

    public ReportController(ReportServiceImpl reportServiceImpl) {
        this.reportServiceImpl = reportServiceImpl;
    }

    @GetMapping
    public String mostrarFormularioReporte() {
        return "reportes";  // esto busca templates/reportes.html
    }

    @PostMapping("/generar")
    public String generarReporte(@RequestParam("dni") String dni, Model model) {
        try {
            String mensaje = reportServiceImpl.exportReportByDni(dni);
            model.addAttribute("mensaje", "✅ " + mensaje);
        } catch (Exception e) {
            e.printStackTrace(); // 👈 muestra el error real en consola (clave para depurar)
            model.addAttribute("mensaje", "❌ Error al generar el reporte: " + e.getMessage());
        }
        return "reportes"; // asegúrate que tu HTML se llame reportes.html
    }

}