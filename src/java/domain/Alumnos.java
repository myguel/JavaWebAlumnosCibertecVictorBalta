package domain;

import java.util.HashSet;
import java.util.Set;

public class Alumnos {

    private Integer idalumno;
    private String nombre;
    private Set<Notas> notas = new HashSet<Notas>();

    public Integer getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(Integer idalumno) {
        this.idalumno = idalumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Notas> getNotas() {
        return notas;
    }

    public void setNotas(Set<Notas> notas) {
        this.notas = notas;
    }
}