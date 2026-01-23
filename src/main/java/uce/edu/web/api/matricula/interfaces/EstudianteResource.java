package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;
import jakarta.ws.rs.core.Response;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("")
    public List<Estudiante> ListarTodos() {
        return this.estudianteService.ListarTodos();
    }

    /* LUEGO SE USARA EL MODELO DE MADURES DE RICHARSON */
    @GET
    @Path("/{id}")
    public Estudiante consultarPorId(@PathParam("id") Integer ids) {
        return this.estudianteService.consultarPorId(ids);
    }

    @POST
    @Path("")
    public Response guardar(Estudiante estudiante) {
        this.estudianteService.crearEstudiante(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizarEstudiante(id, estudiante);
        return Response.status(209).entity(estudiante).build();
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@PathParam("id") Integer id, Estudiante estudiante) {
        this.estudianteService.actualizarParcialEstudiante(id, estudiante);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        this.estudianteService.deleteEstudiante(id);
    }

    @GET
    @Path("/provincia")
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia) {
        return this.estudianteService.buscarPorProvincia(provincia);
    }

    @GET
    @Path("/nombre-e-id") 
    public List<Estudiante> buscarPorNombreEId(@QueryParam("nombre") String nombre, @QueryParam("id") Integer id) {
        return this.estudianteService.buscarPorNombreEId(nombre, id);
    }

    @GET
    @Path("/buscarPorNombreGeneroYProvincia")
    public List<Estudiante> buscarPorNombreGeneroYProvincia(@QueryParam("nombre") String nombre, @QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        return this.estudianteService.buscarPorNombreGeneroYProvincia(nombre, genero, provincia);
    }

    @GET
    @Path("/genero-provincia")
    public List<Estudiante> buscarPorGeneroYProvincia(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        return this.estudianteService.buscarporGeneroyProvincia(genero, provincia);
    }

    @GET
    @Path("/buscarPorNombreIdYProvincia")
    public List<Estudiante> buscarPorNombreIdYProvincia(@QueryParam("nombre") String nombre, @QueryParam("id") Integer id, @QueryParam("provincia") String provincia) {
        return this.estudianteService.buscarPorNombreIdYProvincia(nombre, id, provincia);
    }

}