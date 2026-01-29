package uce.edu.web.api.matricula.application.representation;

import java.util.List;
import java.time.LocalDateTime;

public class EstudianteRepresentation {
    public Integer id;
    public String nombre;
    public String apellido;
    public LocalDateTime fechaNacimiento;
    public String provincia;
    public String genero;

    public List<LinkDto> links;

}
