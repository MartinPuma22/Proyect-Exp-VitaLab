package com.vitaLab.ClinicaOcupacional.service.impl;

import com.vitaLab.ClinicaOcupacional.model.Cita;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import com.vitaLab.ClinicaOcupacional.repository.CitaRepository;
import com.vitaLab.ClinicaOcupacional.service.ReportService;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class ReportServiceImpl implements ReportService {

    private final CitaRepository citaRepository;

    public ReportServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public String exportReportByDni(String dni) throws Exception {
        List<Cita> citas = citaRepository.findByPaciente_Dni(dni);
        if (citas.isEmpty()) {
            throw new IllegalArgumentException("No hay citas para el DNI: " + dni);
        }

        String path = "C:/reportes";
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();

        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/reporte_cita.jrxml"));

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(citas);
        Map<String, Object> params = new HashMap<>();
        params.put("createdBy", "Sistema VitaLab");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/Reporte_" + dni + ".pdf");

        return "Reporte generado en: " + path + "/Reporte_" + dni + ".pdf";
    }
}
