package com.vitaLab.ClinicaOcupacional.controller;
import com.vitaLab.ClinicaOcupacional.model.Doctor;
import com.vitaLab.ClinicaOcupacional.model.Especialidad;
import com.vitaLab.ClinicaOcupacional.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctores")
public class DoctorController {


    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String especialidad, Model model) {
        List<Doctor> doctores;
        if (especialidad != null && !especialidad.isBlank()) {
            doctores = doctorService.buscarPorEspecialidad(especialidad);
        } else {
            doctores = doctorService.listar();
        }

        model.addAttribute("listaDoctores", doctores);
        // Aseguramos que siempre haya un objeto doctor para el formulario (nuevo/editar)
        model.addAttribute("doctor", new Doctor());

        // Pasar opciones de especialidad como nombres de enum (para que el select envíe el valor correcto)
        List<String> especialidades = Arrays.stream(Especialidad.values())
                .map(Enum::name) // envía MEDICINA_GENERAL, CARDIOLOGIA, ...
                .collect(Collectors.toList());
        model.addAttribute("especialidades", especialidades);

        model.addAttribute("especialidadSeleccionada", especialidad);
        return "doctores";
    }

    @GetMapping("/nuevo")
    public String nuevoDoctor(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("especialidades",
                Arrays.stream(Especialidad.values()).map(Enum::name).toList());
        return "doctores"; // reutilizamos la misma vista doctores.html que ya contiene el form
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Doctor doctor) {
        // Guardar: doctor.especialidad debe estar correctamente convertido por Spring si usas EnumType.STRING en entidad
        doctorService.guardar(doctor);
        return "redirect:/doctores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Optional<Doctor> opt = doctorService.obtenerPorId(id);
        if (opt.isPresent()) {
            model.addAttribute("doctor", opt.get());
        } else {
            model.addAttribute("doctor", new Doctor());
        }
        model.addAttribute("listaDoctores", doctorService.listar()); // para que la tabla también esté presente
        model.addAttribute("especialidades",
                Arrays.stream(Especialidad.values()).map(Enum::name).toList());
        return "doctores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        doctorService.eliminar(id);
        return "redirect:/doctores";
    }
}
