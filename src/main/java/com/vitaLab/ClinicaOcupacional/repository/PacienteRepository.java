package com.vitaLab.ClinicaOcupacional.repository;

import com.vitaLab.ClinicaOcupacional.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByDni(String dni);
}
