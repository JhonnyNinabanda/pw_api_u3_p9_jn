package uce.edu.web.api.matricula.infraestructure;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import uce.edu.web.api.matricula.domain.Materia;

@ApplicationScoped
public class MateriaRepository implements PanacheRepository<Materia>{

}
