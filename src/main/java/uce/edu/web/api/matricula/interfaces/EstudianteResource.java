package uce.edu.web.api.matricula.interfaces;

import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import java.util.List;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;


@Path("/todos")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("/listar")
    public List<Estudiante> listarTodos() {
        return this.estudianteService.listarTodos();
    }



}
