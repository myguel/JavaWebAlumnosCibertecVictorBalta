package dao;

import java.util.List;

import domain.Alumnos;

public interface DaoAlumnos {

    public List<Alumnos> alumnosQry();

    public String alumnosIns(Alumnos alumnos);

    public String alumnosDel(List<Integer> id);

    public Alumnos alumnosGet(Integer idalumno);

    public String alumnosUpd(Alumnos alumnos);
}
