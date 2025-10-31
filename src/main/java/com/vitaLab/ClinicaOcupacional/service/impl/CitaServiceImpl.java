package com.vitaLab.ClinicaOcupacional.service.impl;

import com.vitaLab.ClinicaOcupacional.model.Cita;
import org.springframework.stereotype.Service;
import com.vitaLab.ClinicaOcupacional.repository.CitaRepository;
import com.vitaLab.ClinicaOcupacional.service.CitaService;

import java.util.List;
import java.util.Optional;

@Service

public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public List<Cita> listar() {
        return citaRepository.findAll();
    }

    @Override
    public Optional<Cita> obtenerPorId(Long id) {
        return citaRepository.findById(id);
    }

    @Override
    public Cita guardar(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }

    @Override
    public List<Cita> buscarPorDni(String dni) {
        return citaRepository.findByPaciente_Dni(dni);
    }
}