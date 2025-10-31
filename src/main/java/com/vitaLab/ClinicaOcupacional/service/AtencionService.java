package com.vitaLab.ClinicaOcupacional.service;

import com.vitaLab.ClinicaOcupacional.model.Atencion;
import java.util.List;
import java.util.Optional;

public interface AtencionService {
    List<Atencion> listarTodas();
    Optional<Atencion> buscarPorId(Long id);
    Atencion guardar(Atencion atencion);
    void eliminar(Long id);
}
