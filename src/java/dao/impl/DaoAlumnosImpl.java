package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import parainfo.sql.ConectaDb;

import dao.DaoAlumnos;
import domain.Alumnos;
import domain.Notas;

public class DaoAlumnosImpl implements DaoAlumnos {

    private ConectaDb db;

    public DaoAlumnosImpl() {
        this.db = new ConectaDb();
    }

    @Override
    public List<Alumnos> alumnosQry() {
        List<Alumnos> list = null;
        String sql1 = "SELECT idalumno, nombre FROM alumnos";
        String sql2 = "SELECT idnota, nota FROM notas WHERE idalumno=?";

        Connection cn = db.getConnection();
        if (cn != null) {
            try {
                PreparedStatement ps1 = cn.prepareStatement(sql1);
                PreparedStatement ps2 = cn.prepareStatement(sql2);

                ResultSet rs1 = ps1.executeQuery();

                list = new LinkedList<Alumnos>();
                while (rs1.next()) {
                    Alumnos a = new Alumnos();

                    a.setIdalumno(rs1.getInt(1));
                    a.setNombre(rs1.getString(2));

                    ps2.setInt(1, a.getIdalumno());
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        Notas n = new Notas();

                        n.setIdnota(rs2.getInt(1));
                        n.setNota(rs2.getInt(2));

                        a.getNotas().add(n);
                    }

                    list.add(a);
                }

            } catch (SQLException e) {
            } finally {
                try {
                    cn.close();
                } catch (SQLException e2) {
                }
            }
        }

        return list;
    }

    @Override
    public String alumnosIns(Alumnos alumnos) {
        String error = null;
        String sql = "INSERT INTO alumnos(nombre) VALUES(?)";

        Connection cn = db.getConnection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, alumnos.getNombre());

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
    public String alumnosDel(List<Integer> id) {
        String error = null;
        String sql = "DELETE FROM alumnos WHERE idalumno=?";

        Connection cn = db.getConnection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);

                for (Integer x : id) {
                    ps.setInt(1, x);

                    int ctos = ps.executeUpdate();
                    if (ctos == 0) {
                        throw new SQLException();
                    }
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
    public Alumnos alumnosGet(Integer idalumno) {
        Alumnos alumnos = null;
        String sql = "SELECT idalumno, nombre FROM alumnos "
                + "WHERE idalumno=?";

        Connection cn = db.getConnection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, idalumno);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    alumnos = new Alumnos();

                    alumnos.setIdalumno(rs.getInt(1));
                    alumnos.setNombre(rs.getString(2));
                }

            } catch (SQLException e) {
            } finally {
                try {
                    cn.close();
                } catch (SQLException e2) {
                }
            }
        }

        return alumnos;
    }

    @Override
    public String alumnosUpd(Alumnos alumnos) {
        String error = null;
        String sql = "UPDATE alumnos SET nombre=? WHERE idalumno=?";

        Connection cn = db.getConnection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, alumnos.getNombre());
                ps.setInt(2, alumnos.getIdalumno());

                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    error = "= filas afectadas";
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
