package uce.edu.web.api.matricula.interfaces;

import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;
import jakarta.ws.rs.GET;
import java.util.List;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.DELETE;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/todos")
    public List<Materia> listarTodas() {
        return this.materiaService.listarTodas();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Materia consultarPorId(@PathParam("id") Integer ids) {
        return this.materiaService.consultarPorId(ids);
    }

    @POST
    @Path("/crear")
    public void crearMateria(Materia materia) {
        this.materiaService.crearMateria(materia);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizar(id, materia);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizarParcial(id, materia);
    }


    @DELETE
    @Path("/eliminar/{id}")
    public void eliminar(@PathParam("id") Long id) {
        this.materiaService.eliminarMateria(id);
    }

    // Endpoint para buscar materias por nombre
    @GET
    @Path("/buscarPorNombre/{nombre}")
    public List<Materia> buscarPorNombre(@PathParam("nombre") String nombre) {
        return this.materiaService.buscarPorNombre(nombre);
    }

    // Endpoint para contar el número de materias registradas
    @GET
    @Path("/contar")
    public long contarMaterias() {
        return this.materiaService.contarMaterias();
    }
    
}
