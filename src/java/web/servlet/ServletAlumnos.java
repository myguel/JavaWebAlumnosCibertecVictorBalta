package web.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoAlumnos;
import dao.impl.DaoAlumnosImpl;
import domain.Alumnos;

@WebServlet(name = "/ServletAlumnos", urlPatterns = {"/Alumnos"})
public class ServletAlumnos extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ServletAlumnos() {
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

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        String target = "index.jsp";
        String error = null;
        //
        DaoAlumnos daoAlumnos = new DaoAlumnosImpl();
        //
        if (accion == null) {
            error = "Solicitud no recibida";

        } else if (accion.equals("QRY")) {
            List<Alumnos> list = daoAlumnos.alumnosQry();

            request.getSession().setAttribute("list", list);
            target = "view/alumnosQry.jsp";

        } else if (accion.equals("INS")) {
            Alumnos alumnos = new Alumnos();
            error = valida(request, alumnos);

            if (error == null) {
                error = daoAlumnos.alumnosIns(alumnos);
            }

        } else if (accion.equals("DEL")) {
            String ids = request.getParameter("ids");
            String[] id = ids.split(",");
            List<Integer> list = new LinkedList<Integer>();

            for (String x : id) {
                list.add(Integer.valueOf(x));
            }

            error = daoAlumnos.alumnosDel(list);

        } else if (accion.equals("GET")) {
            String idalumno = request.getParameter("id");
            Alumnos a = daoAlumnos.alumnosGet(Integer.valueOf(idalumno));

            if (a != null) {
                request.getSession().setAttribute("alumno", a);
                target = "view/alumnosUpd.jsp";

            } else {
                error = "No existe Alumno";
            }

        } else if (accion.equals("UPD")) {
            Alumnos alumnos = new Alumnos();
            error = valida(request, alumnos);

            if (error == null) {
                error = daoAlumnos.alumnosUpd(alumnos);
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
    private String valida(HttpServletRequest request, Alumnos alAlumnos) {
        String error = null;
        String idalumno = request.getParameter("idalumno");
        String nombre = request.getParameter("nombre");

        Integer idalumnox = null;
        if (idalumno != null) {
            try {
                idalumnox = Integer.valueOf(idalumno);
            } catch (Exception e) {
                error = "ID debe ser entero";
            }
        }

        if (error == null) {
            if ((nombre == null) || (nombre.trim().length() == 0)) {
                error = "Ingrese Nombre de Alumno";
            }
        }

        if (error == null) {
            alAlumnos.setIdalumno(idalumnox);
            alAlumnos.setNombre(nombre);
        }

        return error;
    }
}
