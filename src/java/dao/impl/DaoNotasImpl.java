package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import parainfo.sql.ConectaDb;
import dao.DaoNotas;
import domain.Notas;

public class DaoNotasImpl implements DaoNotas {

    private ConectaDb db;

    public DaoNotasImpl() {
        this.db = new ConectaDb();
    }

    @Override
    public String notasIns(Notas notas) {
        String error = null;
        String sql = "INSERT INTO notas(idalumno, nota) VALUES(?, ?)";

        Connection cn = db.getConnection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, notas.getIdalumno());
                ps.setInt(2, notas.getNota());

                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    error = "0 filas afectadas";
                }
            } catch (SQLException e) {
                error = e.getMessage();
            } finally {
                try {
                    cn.close();
                } catch (SQLException e2) {
                    error = e2.getMessage();
                }
            }
        } else {
            error = "No se conecta a DB.";
        }

        return error;
    }

    @Override
    public String notasDel(Integer idnota) {
        String error = null;
        String sql = "DELETE FROM notas WHERE idnota=?";

        Connection cn = db.getConnection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, idnota);

                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    error = "0 filas afectadas";
                }
            } catch (SQLException e) {
                error = e.getMessage();
            } finally {
                try {
                    cn.close();
                } catch (SQLException e2) {
                    error = e2.getMessage();
                }
            }
        } else {
            error = "No se conecta a DB.";
        }

        return error;
    }

    @Override
    public Notas notasGet(Integer idnota) {
        Notas notas = null;
        String sql = "SELECT idnota, idalumno, nota FROM notas "
                + "WHERE idnota=?";

        Connection cn = db.getConnection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, idnota);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    notas = new Notas();

                    notas.setIdnota(rs.getInt(1));
                    notas.setIdalumno(rs.getInt(2));
                    notas.setNota(rs.getInt(3));
                }

            } catch (SQLException e) {
            } finally {
                try {
                    cn.close();
                } catch (SQLException e2) {
                }
            }
        }

        return notas;
    }

    @Override
    public String notasUpd(Notas notas) {
        String error = null;
        String sql = "UPDATE notas SET nota=? WHERE idnota=?";

        Connection cn = db.getConnection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, notas.getNota());
                ps.setInt(2, notas.getIdnota());

                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    error = "0 filas afectadas";
                }
            } catch (SQLException e) {
                error = e.getMessage();
            } finally {
                try {
                    cn.close();
                } catch (SQLException e2) {
                    error = e2.getMessage();
                }
            }
        } else {
            error = "No se conecta a DB.";
        }

        return error;
    }
}
