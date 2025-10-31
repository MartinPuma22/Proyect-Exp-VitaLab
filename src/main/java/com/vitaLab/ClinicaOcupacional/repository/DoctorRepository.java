package com.vitaLab.ClinicaOcupacional.repository;

import com.vitaLab.ClinicaOcupacional.model.Doctor;
import com.vitaLab.ClinicaOcupacional.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByEspecialidad(Especialidad especialidad);
}
