package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoAlumnos;
import dao.DaoNotas;
import dao.impl.DaoAlumnosImpl;
import dao.impl.DaoNotasImpl;
import domain.Alumnos;
import domain.Notas;

@WebServlet(name = "ServletNotas", urlPatterns = {"/Notas"})
public class ServletNotas extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ServletNotas() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        procesa(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        procesa(request, response);
    }

    private void procesa(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");
        String target = "index.jsp";
        String error = null;
        //
        DaoNotas daoNotas = new DaoNotasImpl();
        //
        if (accion == null) {
            error = "Solicitud no recibida";

        } else if (accion.equals("INS")) {
            Notas n = new Notas();
            error = valida(request, n);

            if (error == null) {
                error = daoNotas.notasIns(n);
            }

        } else if (accion.equals("DEL")) {
            String idnota = request.getParameter("idnota");
            try {
                error = daoNotas.notasDel(Integer.valueOf(idnota));
            } catch (NumberFormatException e) {
                error = "ID de Nota incorrecto";
            }

        } else if (accion.equals("GET")) {
            String idnota = request.getParameter("idnota");

            Notas n = null;
            try {
                n = daoNotas.notasGet(Integer.valueOf(idnota));
            } catch (NumberFormatException e) {
                error = "ID de Nota incorrecto";
            }

            if (n != null) {
                DaoAlumnos daoAlumnos = new DaoAlumnosImpl();
                Alumnos a = daoAlumnos.alumnosGet(n.getIdalumno());

                request.getSession().setAttribute("alumno", a);
                request.getSession().setAttribute("nota", n);

                target = "view/notasUpd.jsp";
            }

        } else if (accion.equals("UPD")) {
            Notas n = new Notas();
            error = valida(request, n);

            if (error == null) {
                error = daoNotas.notasUpd(n);
            }

        } else {
            error = "Solicitud no reconocida";
        }

        if (error != null) {
            request.getSession().setAttribute("msg", error);
            target = "mensaje.jsp";
        }

        response.sendRedirect(target);
    }

    // apoyo
    private String valida(HttpServletRequest request, Notas notas) {
        String error = null;
        String idnota = request.getParameter("idnota");
        String idalumno = request.getParameter("idalumno");
        String nota = request.getParameter("nota");

        Integer idnotax = null;
        if (idnota != null) {
            try {
                idnotax = Integer.valueOf(idnota);
            } catch (NumberFormatException e) {
                error = "ID de Nota incorrecto";
            }
        }

        Integer idalumnox = null;
        if (error == null) {
            try {
                idalumnox = Integer.valueOf(idalumno);
            } catch (NumberFormatException e) {
                error = "ID de Alumno incorrecto";
            }
        }

        Integer notax = null;
        if (error == null) {
            try {
                notax = Integer.valueOf(nota);

                if ((notax < 0) || (notax > 20)) {
                    error = "Nota de estar entre [0, 20]";
                }

            } catch (NumberFormatException e) {
                error = "Nota de Alumno incorrecta";
            }
        }

        if (error == null) {
            notas.setIdnota(idnotax);
            notas.setIdalumno(idalumnox);
            notas.setNota(notax);
        }

        return error;
    }
}
