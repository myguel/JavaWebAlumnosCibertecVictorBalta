# DBTools DBMYSQL - MySQL Database Dump
#
# USEGO
SET FOREIGN_KEY_CHECKS=0;

# GO
# Dumping Table Structure for alumnos

#
CREATE TABLE `alumnos` (
  `idalumno` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`idalumno`),
  UNIQUE KEY `IDX_alumnos_2` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
# GO
#

# Dumping Data for alumnos
#
BEGIN;
# GO
INSERT INTO `alumnos` (idalumno, nombre) VALUES (2, 'Anabel Aranda');
# GO
INSERT INTO `alumnos` (idalumno, nombre) VALUES (5, 'Carlos Bustamente');
# GO
INSERT INTO `alumnos` (idalumno, nombre) VALUES (4, 'Carmen Sierra');
# GO
INSERT INTO `alumnos` (idalumno, nombre) VALUES (1, 'Jorge Risco');
# GO
INSERT INTO `alumnos` (idalumno, nombre) VALUES (3, 'Luis Roncal');
# GO
COMMIT;
# GO
# Dumping Table Structure for notas

#
CREATE TABLE `notas` (
  `idnota` int(11) NOT NULL AUTO_INCREMENT,
  `idalumno` int(11) NOT NULL,
  `nota` smallint(6) NOT NULL,
  PRIMARY KEY (`idnota`),
  KEY `FK_notas_1` (`idalumno`),
  CONSTRAINT `FK_notas_1` FOREIGN KEY (`idalumno`) REFERENCES `alumnos` (`idalumno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
# GO
#

# Dumping Data for notas
#
BEGIN;
# GO
INSERT INTO `notas` (idnota, idalumno, nota) VALUES (1, 1, 14);
# GO
INSERT INTO `notas` (idnota, idalumno, nota) VALUES (2, 1, 15);
# GO
INSERT INTO `notas` (idnota, idalumno, nota) VALUES (3, 1, 15);
# GO
INSERT INTO `notas` (idnota, idalumno, nota) VALUES (4, 2, 15);
# GO
INSERT INTO `notas` (idnota, idalumno, nota) VALUES (5, 2, 15);
# GO
INSERT INTO `notas` (idnota, idalumno, nota) VALUES (6, 2, 16);
# GO
INSERT INTO `notas` (idnota, idalumno, nota) VALUES (7, 3, 11);
# GO
INSERT INTO `notas` (idnota, idalumno, nota) VALUES (8, 3, 8);
# GO
INSERT INTO `notas` (idnota, idalumno, nota) VALUES (9, 5, 12);
# GO
COMMIT;
# GO
SET FOREIGN_KEY_CHECKS=1

# GO
