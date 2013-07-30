<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../css/main.css" type="text/css" rel="stylesheet" />

        <script src="../js/alumnos.js" type="text/javascript"></script>
    </head>
    <body>
        <table class="clasico">
            <thead>
                <tr>
                    <td>Alumno <a href="#" onclick="alumnosIns();"> <img
                                src="../images/ins.png" title="Nuevo Alumno" /></a>
                    </td>
                    <td>Notas</td>
                    <th style="width: 32px"><a href="#" onclick="alumnosDel();">
                            <img src="../images/del.png" title="Retirar Alumno" />
                        </a></th>
                    <th style="width: 32px"><a href="#" onclick="alumnosUpd();">
                            <img src="../images/upd.png" title="Actualizar Alumno" />
                        </a></th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="f" items="${list}">
                    <tr>
                        <td>${f.nombre}</td>
                        <td>
                            <c:if test="${f.notas.size() > 0}" var="tiene">
                                <select id="idnota_${f.idalumno}">
                                    <c:forEach var="n" items="${f.notas}">
                                        <option value="${n.idnota}">${n.nota}</option>
                                    </c:forEach>
                                </select>
                                <a href="#"
                                   onclick="notasIns('${f.idalumno}', '${f.nombre}');"> <img
                                        src="../images/ins.png" title="Nueva Nota" /></a>
                                <a href="#" onclick="notasDel('${f.idalumno}');"> <img
                                        src="../images/del.png" title="Retirar Nota" /></a>
                                <a href="#"
                                   onclick="notasUpd('${f.idalumno}');"> <img
                                        src="../images/upd.png" title="Actualizar Nota" /></a>
                                </c:if> 
                                <c:if test="${!tiene}">
                                sin notas 
                                <a href="#"
                                   onclick="notasIns('${f.idalumno}', '${f.nombre}');"> <img
                                        src="../images/ins.png" title="Nueva Nota" /></a>
                                </c:if>
                        </td>
                        <th>
                            <input type="checkbox" value="${f.idalumno}" name="_del" />
                        </th>
                        <th>
                            <input type="radio" value="${f.idalumno}" name="_upd" />
                        </th>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
