package uce.edu.web.api.matricula.application.representation;

import java.time.LocalDate;
import java.util.List;

public class EstudianteRepresentation {
    public Integer id;
    public String nombre;
    public String apellido;
    public LocalDate fechaNacimiento;
    public String provincia;
    public String genero;

    public List<LinkDto> links;

}
