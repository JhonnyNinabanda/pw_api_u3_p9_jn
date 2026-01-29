package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;




@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> ListarTodos() {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for(Estudiante estu : this.estudianteRepository.listAll()){
            list.add(this.mapperToEstudianteRepresentation(estu));

        }

        return list;
    }

    public EstudianteRepresentation consultarPorId(Integer id) {
        return this.mapperToEstudianteRepresentation(this.estudianteRepository.findById(id.longValue()));

    }

    @Transactional
    public void crearEstudiante(EstudianteRepresentation estudiante) {
        this.estudianteRepository.persist(this.mapperToEstudiante(estudiante));
    }

    
    @Transactional
    public void actualizarEstudiante(Integer id, EstudianteRepresentation estudiante) {
        Estudiante estuActual = this.mapperToEstudiante(this.consultarPorId(id));
        estuActual.apellido = estudiante.apellido;
        estuActual.nombre = estudiante.nombre;
        estuActual.fechaNacimiento = estudiante.fechaNacimiento;
    }
    
    // SE ACTUALIZA AUTOMATICAMENTE POR DIRTY CHECKING
    @Transactional
    public void actualizarParcialEstudiante(Integer id, EstudianteRepresentation estudiante) {
        Estudiante estuActual = this.mapperToEstudiante(this.consultarPorId(id));
        if (estudiante.apellido != null) {
            estuActual.apellido = estudiante.apellido;
        }
        if (estudiante.nombre != null) {
            estuActual.nombre = estudiante.nombre;
        }
        if (estudiante.fechaNacimiento != null) {
            estuActual.fechaNacimiento = estudiante.fechaNacimiento;
        }
    }

    @Transactional
    public void deleteEstudiante(Long id) {
        this.estudianteRepository.deleteById(id);
    }

    public List<Estudiante> buscarPorProvincia(String provincia) {
        System.out.println("Buscar por Provincia en el servicio: " + provincia);
        return this.estudianteRepository.find("provincia = ?1", provincia).list();
    }

    public List<Estudiante> buscarporGeneroyProvincia(String genero, String provincia) {
        return this.estudianteRepository.find("genero = ?1 and provincia = ?2", genero, provincia).list();
    }

    //Ejemplos de consultas personalizadas

    // 4 ejemplos mas complejos de consultas se dejan como ejercicio

    // buscar nombre apellido
    public List<Estudiante> buscarPorNombreYApellido(String nombre, String apellido) {
        return this.estudianteRepository.find("nombre = ?1 and apellido = ?2", nombre, apellido).list();
    }

    // buscar nombre id
    public List<Estudiante> buscarPorNombreEId(String nombre, Integer id) {
        return this.estudianteRepository.find("nombre = ?1 and id = ?2", nombre, id.longValue()).list();
    }

    // nombre genero provincia
    public List<Estudiante> buscarPorNombreGeneroYProvincia(String nombre, String genero, String provincia) {
        return this.estudianteRepository
                .find("nombre = ?1 and genero = ?2 and provincia = ?3", nombre, genero, provincia).list();
    }
    // nombre id provincia
    public List<Estudiante> buscarPorNombreIdYProvincia(String nombre, Integer id, String provincia) {
        return this.estudianteRepository
                .find("nombre = ?1 and id = ?2 and provincia = ?3", nombre, id.longValue(), provincia).list();
    }

    private Estudiante mapperToEstudiante(EstudianteRepresentation est) {
        Estudiante estuR = new Estudiante();
        estuR.id = est.id;
        estuR.nombre = est.nombre;
        estuR.apellido = est.apellido;
        estuR.fechaNacimiento = est.fechaNacimiento;
        estuR.provincia = est.provincia;
        estuR.genero = est.genero;
        return estuR;
    }

      private EstudianteRepresentation mapperToEstudianteRepresentation(Estudiante est) {
        EstudianteRepresentation estuR = new EstudianteRepresentation();
        estuR.id = est.id;
        estuR.nombre = est.nombre;
        estuR.apellido = est.apellido;
        estuR.fechaNacimiento = est.fechaNacimiento;
        estuR.provincia = est.provincia;
        estuR.genero = est.genero;

        return estuR;
    }


}