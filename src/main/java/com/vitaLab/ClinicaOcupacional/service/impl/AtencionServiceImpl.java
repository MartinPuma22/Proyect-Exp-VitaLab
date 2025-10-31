package com.vitaLab.ClinicaOcupacional.service.impl;
import com.vitaLab.ClinicaOcupacional.model.Atencion;
import com.vitaLab.ClinicaOcupacional.repository.AtencionRepository;
import com.vitaLab.ClinicaOcupacional.service.AtencionService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AtencionServiceImpl implements AtencionService {

    private final AtencionRepository atencionRepository;

    public AtencionServiceImpl(AtencionRepository atencionRepository) {
        this.atencionRepository = atencionRepository;
    }

    @Override
    public List<Atencion> listarTodas() {
        return atencionRepository.findAll();
    }

    @Override
    public Optional<Atencion> buscarPorId(Long id) {
        return atencionRepository.findById(id);
    }

    @Override
    public Atencion guardar(Atencion atencion) {
        return atencionRepository.save(atencion);
    }

    @Override
    public void eliminar(Long id) {
        atencionRepository.deleteById(id);
    }
}
