package com.vitaLab.ClinicaOcupacional.controller;
import com.vitaLab.ClinicaOcupacional.model.Cita;
import com.vitaLab.ClinicaOcupacional.model.Doctor;
import com.vitaLab.ClinicaOcupacional.model.Paciente;
import com.vitaLab.ClinicaOcupacional.service.CitaService;
import com.vitaLab.ClinicaOcupacional.service.PacienteService;
import com.vitaLab.ClinicaOcupacional.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/citas")
public class CitaController {
    private final CitaService citaService;
    private final PacienteService pacienteService;
    private final DoctorService doctorService;

    public CitaController(CitaService citaService, PacienteService pacienteService,DoctorService doctorService) {
        this.citaService = citaService;
        this.pacienteService = pacienteService;
        this.doctorService = doctorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaCitas", citaService.listar());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("doctores", doctorService.listar());
        model.addAttribute("cita", new Cita());
        model.addAttribute("modoEdicion", false);
        return "citas";
    }

    // ✏️ Modo edición: cargar cita existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Cita cita = citaService.obtenerPorId(id).orElse(new Cita());
        model.addAttribute("listaCitas", citaService.listar());
        model.addAttribute("pacientes", pacienteService.listar());
        model.addAttribute("doctores", doctorService.listar());
        model.addAttribute("cita", cita);
        model.addAttribute("modoEdicion", true);
        return "citas";
    }

    // 💾 Guardar o actualizar
    @PostMapping("/guardar")
    public String guardar(
            @RequestParam("pacienteId") Long pacienteId,
            @RequestParam("doctorId") Long doctorId,
            @ModelAttribute Cita cita) {

        // Asignamos manualmente las entidades relacionadas
        Paciente paciente = pacienteService.obtenerPorId(pacienteId).orElse(null);
        Doctor doctor = doctorService.obtenerPorId(doctorId).orElse(null);

        cita.setPaciente(paciente);
        cita.setDoctor(doctor);

        citaService.guardar(cita);
        return "redirect:/citas";
    }

    // 🗑️ Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return "redirect:/citas";
    }
}
