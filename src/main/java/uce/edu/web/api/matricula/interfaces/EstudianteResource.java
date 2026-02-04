package uce.edu.web.api.matricula.interfaces;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDto;
import uce.edu.web.api.matricula.domain.Estudiante;


@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")

    public List<EstudianteRepresentation> ListarTodos() {
        List<EstudianteRepresentation> estu = new ArrayList<>();
        for (EstudianteRepresentation est : this.estudianteService.ListarTodos()) {
            estu.add(this.construirLinks(est));
        }
        return estu;
    }

    /* LUEGO SE USARA EL MODELO DE MADURES DE RICHARSON */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    //@PermitAll
    public EstudianteRepresentation consultarPorId(@PathParam("id") Integer ids) {
        return this.construirLinks(this.estudianteService.consultarPorId(ids));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response guardar(EstudianteRepresentation estudiante) {
        this.estudianteService.crearEstudiante(estudiante);
        return Response.status(Response.Status.CREATED).entity(estudiante).build();
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizar(@PathParam("id") Integer id, EstudianteRepresentation estudiante) {
        this.estudianteService.actualizarEstudiante(id, estudiante);
        return Response.status(209).entity("Actualizando").build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public void actualizarParcial(@PathParam("id") Integer id, EstudianteRepresentation estudiante) {
        this.estudianteService.actualizarParcialEstudiante(id, estudiante);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        this.estudianteService.deleteEstudiante(id);
    }

    @GET
    @Path("/{id}/hijos")
    @RolesAllowed("admin")
    public List<HijoRepresentation> buscarPorIdEstudiante(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorIdEstudiante(id);
    }

    @GET
    @Path("/provincia")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia) {
        return this.estudianteService.buscarPorProvincia(provincia);
    }

    @GET
    @Path("/nombre-e-id") 
    @RolesAllowed("admin")
    public List<Estudiante> buscarPorNombreEId(@QueryParam("nombre") String nombre, @QueryParam("id") Integer id) {
        return this.estudianteService.buscarPorNombreEId(nombre, id);
    }

    @GET
    @Path("/buscarPorNombreGeneroYProvincia")
    @RolesAllowed("admin")
    public List<Estudiante> buscarPorNombreGeneroYProvincia(@QueryParam("nombre") String nombre, @QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        return this.estudianteService.buscarPorNombreGeneroYProvincia(nombre, genero, provincia);
    }

    @GET
    @Path("/genero-provincia")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<Estudiante> buscarPorGeneroYProvincia(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        return this.estudianteService.buscarporGeneroyProvincia(genero, provincia);
    }

    @GET
    @Path("/buscarPorNombreIdYProvincia")
    @RolesAllowed("admin")
    public List<Estudiante> buscarPorNombreIdYProvincia(@QueryParam("nombre") String nombre, @QueryParam("id") Integer id, @QueryParam("provincia") String provincia) {
        return this.estudianteService.buscarPorNombreIdYProvincia(nombre, id, provincia);
    }

    private EstudianteRepresentation construirLinks(EstudianteRepresentation estr){
        String self = this.uriInfo.getBaseUriBuilder()
            .path(EstudianteResource.class)
            .path(String.valueOf(estr.id))
            .build(estr.id)
            .toString();

            String hijos = this.uriInfo.getBaseUriBuilder()
            .path(EstudianteResource.class)
            .path(String.valueOf(estr.id))
            .path("hijos")
            .build()
            .toString();

            estr.links = List.of(new LinkDto(self, "self"),new LinkDto(hijos, "hijosSelf"));

        return estr;
    }

}