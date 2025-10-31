package com.vitaLab.ClinicaOcupacional.repository;

import com.vitaLab.ClinicaOcupacional.model.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtencionRepository extends JpaRepository<Atencion, Long> {
}
