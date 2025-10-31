package com.vitaLab.ClinicaOcupacional.service.impl;

import com.vitaLab.ClinicaOcupacional.model.Paciente;
import org.springframework.stereotype.Service;
import com.vitaLab.ClinicaOcupacional.repository.PacienteRepository;
import com.vitaLab.ClinicaOcupacional.service.PacienteService;

import java.util.List;
import java.util.Optional;

@Service

public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> obtenerPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente buscarPorDni(String dni) {
        return pacienteRepository.findByDni(dni);
    }
}
