package com.vitaLab.ClinicaOcupacional.service;

import com.vitaLab.ClinicaOcupacional.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    List<Paciente> listar();
    Optional<Paciente> obtenerPorId(Long id);
    Paciente guardar(Paciente paciente);
    void eliminar(Long id);
    Paciente buscarPorDni(String dni);
}
