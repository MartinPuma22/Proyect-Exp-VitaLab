package com.vitaLab.ClinicaOcupacional.repository;

import com.vitaLab.ClinicaOcupacional.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByPaciente_Dni(String dni);
}
