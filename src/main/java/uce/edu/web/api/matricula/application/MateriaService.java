package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;
import uce.edu.web.api.matricula.domain.Materia;
import java.util.List;


@ApplicationScoped
public class MateriaService {

    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodas() {
        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id) {
        return this.materiaRepository.findById(id.longValue());
    }

    @Transactional
    public void crearMateria(Materia materia) {
        this.materiaRepository.persist(materia);
    }

    @Transactional
     public void actualizar(Integer id, Materia materia) {
        Materia matActual = this.consultarPorId(id);
        matActual.nombre = materia.nombre;
        matActual.creditos = materia.creditos;
    }

@Transactional
public void actualizarParcial(Integer id, Materia materia) {
    Materia matActual = this.consultarPorId(id);

    if (materia.nombre != null) {
        matActual.nombre = materia.nombre;
    }

    if (materia.creditos != null) {
        matActual.creditos = materia.creditos;
    }
}


    @Transactional
    public void eliminarMateria(Long id) {
        this.materiaRepository.deleteById(id);
    }

    // Buscar materias por nombre
     public List<Materia> buscarPorNombre(String nombre) {
        return this.materiaRepository
                .find("nombre", nombre)
                .list();
    }

    // Contar el número de materias registradas
    public long contarMaterias() {
        return this.materiaRepository.count();
    }







    
}
