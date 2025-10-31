package com.vitaLab.ClinicaOcupacional.service;

import com.vitaLab.ClinicaOcupacional.model.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaService {
    List<Cita> listar();
    Optional<Cita> obtenerPorId(Long id);
    Cita guardar(Cita cita);
    void eliminar(Long id);
    List<Cita> buscarPorDni(String dni);
}
