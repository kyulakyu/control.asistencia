package model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.entity.Asistencia;
import model.repository.IAsistenciaRepository;

@Service
public class AsistenciaService {

    private final IAsistenciaRepository asistenciaRepository;

    @Autowired
    public AsistenciaService(IAsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    public List<Asistencia> getAsistencias() {
        return asistenciaRepository.findAll();
    }

    public List<Asistencia> getUltimasTresAsistencias() {
        List<Asistencia> todasLasAsistencias = asistenciaRepository.findUltimasTresAsistencias();
        return limitarATres(todasLasAsistencias);
    }

    private List<Asistencia> limitarATres(List<Asistencia> asistencias) {
        return asistencias.subList(0, Math.min(asistencias.size(), 3));
    }

    public void crearAsistencias(Asistencia a, String detalle) {
        // Antes de guardar la capacitación, asignamos el detalle usando el método mostrarDetalle()
        a.getDetalle();
        asistenciaRepository.save(a);
    }
}