package com.vitaLab.ClinicaOcupacional.controller;
import com.vitaLab.ClinicaOcupacional.model.Paciente;
import com.vitaLab.ClinicaOcupacional.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // ========== VISTA PRINCIPAL de pacientes (usa pacientes.html)
    @GetMapping
    public String listarPacientes(Model model) {
        List<Paciente> lista = pacienteService.listar();
        model.addAttribute("listaPacientes", lista);
        model.addAttribute("paciente", new Paciente()); // para el formulario embebido
        return "pacientes"; // tu vista única para pacientes
    }

    // ========== Guardar (crear o actualizar) desde formulario Thymeleaf.
    // Post a /pacientes/guardar (o en el form usar action="/pacientes/guardar")
    @PostMapping("/guardar")
    public String guardarPaciente(@ModelAttribute Paciente paciente) {
        pacienteService.guardar(paciente);
        // Volvemos al index principal como pediste
        return "redirect:/pacientes";
    }

    // ========== Eliminar (desde enlace)
    @GetMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return "redirect:/";
    }

    // ========== Editar: carga los datos para rellenar el formulario en la misma vista
    // Si quieres abrir el formulario en la misma página pacientes.html vía Thymeleaf, usa:
    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteService.obtenerPorId(id).orElse(new Paciente());
        model.addAttribute("paciente", paciente);
        model.addAttribute("listaPacientes", pacienteService.listar());
        // Puedes usar la misma vista 'pacientes' y el formulario mostrará los datos por th:object
        return "pacientes";
    }
    @GetMapping("/nuevo")
    public String nuevoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("listaPacientes", pacienteService.listar());
        return "pacientes";
    }

    // ========== ENDPOINTS JSON / REST (útiles para AJAX o consumo desde JS)

    // Listar en JSON
    @GetMapping("/api")
    @ResponseBody
    public List<Paciente> listarPacientesJson() {
        return pacienteService.listar();
    }

    // Obtener por id en JSON
    @GetMapping("/api/{id}")
    public ResponseEntity<Paciente> obtenerPorIdJson(@PathVariable Long id) {
        return pacienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar por DNI en JSON
    @GetMapping("/api/buscar")
    @ResponseBody
    public ResponseEntity<?> buscarPorDni(@RequestParam("dni") String dni) {
        Paciente p = pacienteService.buscarPorDni(dni);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }
}
