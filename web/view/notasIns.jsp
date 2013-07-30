<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <fmt:requestEncoding value="UTF-8" />
        <fmt:setLocale value="es_ES"/>

        <form action="../Notas" method="post">
            <input type="hidden" name="accion" value="INS"/>
            <input type="hidden" name="idalumno" 
                   value="${param['idalumno']}"/>

            <table style="margin: auto">
                <tr>
                    <td>Alumno</td>
                    <td>
                        <input type="text" name="nombre" style="width: 160px"
                               readonly="readonly" 
                               value="${param['nombre']}"/>
                    </td>
                </tr>
                <tr>
                    <td>Nota</td>
                    <td>
                        <select name="nota">
                            <c:forEach var="n" begin="0" end="20">
                                <c:choose>
                                    <c:when test="${n==14}">
                                        <option value="${n}" selected="selected">${n}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${n}">${n}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <input type="submit" value="Enviar Datos"/>
                    </td>
                </tr>
            </table>
        </form>

        <p style="text-align: center">
            <a href="alumnosQry.jsp">Cancelar</a>
        </p>
    </body>
</html>
