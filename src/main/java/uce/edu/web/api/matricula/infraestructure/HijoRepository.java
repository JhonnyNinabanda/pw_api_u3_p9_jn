package uce.edu.web.api.matricula.infraestructure;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import uce.edu.web.api.matricula.domain.Hijo;


@ApplicationScoped
public class HijoRepository implements PanacheRepository<Hijo> {
    public List<Hijo> buscarporIdEstudiante(Integer idEstudisnte){
        return find("estudiante.id", idEstudisnte).list();
    }

}
