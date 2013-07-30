package dao;


import domain.Notas;

public interface DaoNotas {

    public String notasIns(Notas notas);

    public String notasDel(Integer idnota);

    public Notas notasGet(Integer idnota);

    public String notasUpd(Notas notas);
}
