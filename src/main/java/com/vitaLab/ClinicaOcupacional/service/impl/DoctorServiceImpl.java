package com.vitaLab.ClinicaOcupacional.service.impl;

import com.vitaLab.ClinicaOcupacional.model.Doctor;
import com.vitaLab.ClinicaOcupacional.model.Especialidad;
import org.springframework.stereotype.Service;
import com.vitaLab.ClinicaOcupacional.repository.DoctorRepository;
import com.vitaLab.ClinicaOcupacional.service.DoctorService;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service

public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> listar() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> obtenerPorId(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor guardar(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void eliminar(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public List<Doctor> buscarPorEspecialidad(String especialidadStr) {
        if (especialidadStr == null || especialidadStr.isBlank()) {
            return listar();
        }

        // 1) Intentar con valueOf directo (el HTML idealmente envía MEDICINA_GENERAL, etc.)
        try {
            Especialidad e = Especialidad.valueOf(especialidadStr.trim().toUpperCase(Locale.ROOT));
            return doctorRepository.findByEspecialidad(e);
        } catch (IllegalArgumentException ex) {
            // 2) Normalizar etiquetas humanas: "Medicina General" -> "MEDICINA_GENERAL"
            String cleaned = normalizeToEnumName(especialidadStr);
            try {
                Especialidad e2 = Especialidad.valueOf(cleaned);
                return doctorRepository.findByEspecialidad(e2);
            } catch (IllegalArgumentException ex2) {
                // 3) Si aún no coincide, devolver lista vacía o todos según prefieras.
                // Aquí devolvemos lista vacía (más seguro).
                return List.of();
            }
        }
    }

    private String normalizeToEnumName(String input) {
        // Normaliza acentos, pasa a mayúsculas, reemplaza espacios y guiones por underscore
        String s = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toUpperCase(Locale.ROOT)
                .trim()
                .replaceAll("[\\s\\-]+", "_")
                .replaceAll("[^A-Z0-9_]", ""); // eliminar caracteres extra
        return s;
    }
}
