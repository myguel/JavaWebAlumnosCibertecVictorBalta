<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="../Alumnos" method="post">
            <input type="hidden" name="accion" value="INS"/>

            <table style="margin: auto">
                <tr>
                    <td>Alumno</td>
                    <td>
                        <input type="text" name="nombre" style="width: 160px"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <input type="submit" value="Enviar Dato"/>
                    </td>
                </tr>
            </table>
        </form>

        <p style="text-align: center">
            <a href="alumnosQry.jsp">Cancelar</a>
        </p>
    </body>
</html>
