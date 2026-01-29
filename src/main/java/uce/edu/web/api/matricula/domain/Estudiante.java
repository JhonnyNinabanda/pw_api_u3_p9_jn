package uce.edu.web.api.matricula.domain;

import java.time.LocalDateTime;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;


@Entity
@Table (name = "estudiante")
@SequenceGenerator(name = "estudiante_seq", sequenceName = "estudiante_secuencia", allocationSize = 1)
public class Estudiante extends PanacheEntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiante_seq")
    public Integer id;
    public String nombre;
    public String apellido;
    public LocalDateTime fechaNacimiento;
    public String provincia;
    public String genero;

    @OneToMany(mappedBy = "estudiante",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Hijo> hijos;
}