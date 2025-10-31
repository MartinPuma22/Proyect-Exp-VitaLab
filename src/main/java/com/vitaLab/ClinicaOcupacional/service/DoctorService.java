package com.vitaLab.ClinicaOcupacional.service;

import com.vitaLab.ClinicaOcupacional.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> listar();
    Optional<Doctor> obtenerPorId(Long id);
    Doctor guardar(Doctor doctor);
    void eliminar(Long id);
    List<Doctor> buscarPorEspecialidad(String especialidadStr);
}
