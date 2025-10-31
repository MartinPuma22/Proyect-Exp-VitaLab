package com.vitaLab.ClinicaOcupacional.controller;
import com.vitaLab.ClinicaOcupacional.model.Atencion;
import com.vitaLab.ClinicaOcupacional.model.Cita;
import com.vitaLab.ClinicaOcupacional.repository.CitaRepository;
import com.vitaLab.ClinicaOcupacional.service.AtencionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/atenciones")
public class AtencionController {
    private final AtencionService atencionService;
    private final CitaRepository citaRepository;

    public AtencionController(AtencionService atencionService, CitaRepository citaRepository) {
        this.atencionService = atencionService;
        this.citaRepository = citaRepository;
    }

    @GetMapping
    public String listarAtenciones(Model model) {
        model.addAttribute("listaAtenciones", atencionService.listarTodas());
        return "atencion/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaAtencion(Model model) {
        model.addAttribute("atencion", new Atencion());
        model.addAttribute("citas", citaRepository.findAll());
        return "atencion/form";
    }

    @PostMapping("/guardar")
    public String guardarAtencion(@ModelAttribute Atencion atencion) {
        atencion.setFecha(LocalDate.now());
        atencionService.guardar(atencion);
        return "redirect:/atenciones";
    }

    @GetMapping("/editar/{id}")
    public String editarAtencion(@PathVariable Long id, Model model) {
        Atencion atencion = atencionService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Atención no encontrada"));
        model.addAttribute("atencion", atencion);
        model.addAttribute("citas", citaRepository.findAll());
        return "atencion/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAtencion(@PathVariable Long id) {
        atencionService.eliminar(id);
        return "redirect:/atenciones";
    }
}
