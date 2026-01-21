package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;
import uce.edu.web.api.matricula.domain.Estudiante;

@ApplicationScoped
public class EstudianteService {

    @Inject
private EstudianteRepository estudianteRepository;

public List<Estudiante> listarTodos() {
    return this.estudianteRepository.listAll();

    }
}
