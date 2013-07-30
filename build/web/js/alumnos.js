function alumnosIns() {
    window.location = "alumnosIns.jsp";
}

function alumnosDel() {
    var del = document.getElementsByName("_del");
    var id = "";

    for (var i = 0; i < del.length; i++) {
        if (del[i].checked) {
            id += del[i].value + ",";
        }
    }

    if (id === "") {
        alert("Debe seleccionar fila(s) a Retirar");
    } else {
        if (confirm("Retirar fila(s)?")) {
            window.location = "../Alumnos?accion=DEL&ids="
                    + id.substring(0, id.length - 1);
        }
    }
}

function alumnosUpd() {
    var upd = document.getElementsByName("_upd");
    var id = "";

    for (var i = 0; i < upd.length; i++) {
        if (upd[i].checked) {
            id = upd[i].value;
            break;
        }
    }

    if (id === "") {
        alert("Debe seleccionar Fila para Actualizar Datos");
    } else {
        if (confirm("Actualizar Registro?")) {
            window.location = "../Alumnos?accion=GET&id=" + id;
        }
    }
}

function notasIns(idalumno, nombre) {
    window.location = "notasIns.jsp?idalumno=" + idalumno + "&nombre=" + nombre;
}

function notasDel(idalumno) {
    if (confirm("Retirar Nota?")) {
        var combo = document.getElementById("idnota_" + idalumno);
        var idsel = combo.selectedIndex;
        var opcionsel = combo.options[idsel];

        window.location = "../Notas?accion=DEL&idnota=" + opcionsel.value;

        // opcionsel.text == texto de opcion seleccionada
    }
}

function notasUpd(idalumno) {
    var combo = document.getElementById("idnota_" + idalumno);
    var idsel = combo.selectedIndex;
    var opcionsel = combo.options[idsel];

    window.location = "../Notas?accion=GET&idnota=" + opcionsel.value;
}
