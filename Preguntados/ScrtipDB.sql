DROP DATABASE IF EXISTS preguntados;
CREATE DATABASE preguntados;

USE preguntados;

CREATE TABLE cuentas
(user varchar(50) PRIMARY KEY,
pass varchar(50),
tipo int);

INSERT cuentas VALUES ('diego', 'diego', 0);
INSERT cuentas VALUES ('eze', 'eze', 0);
INSERT cuentas VALUES ('fer', 'fer', 0);
INSERT cuentas VALUES ('seba', 'seba', 0);
INSERT cuentas VALUES ('pepe', '1234', 1);

/*
CREATE TABLE categoria
(id int PRIMARY KEY,
nombre varchar(30));

INSERT categoria VALUES(1, 'deportes');
INSERT categoria VALUES(2, 'cine');
INSERT categoria VALUES(3, 'arte');
INSERT categoria VALUES(4, 'politica'); 
*/

CREATE TABLE preguntas
(ID int PRIMARY KEY,
pregunta varchar(100) NOT NULL,
respuesta1 varchar(100) NOT NULL,
respuesta2 varchar(100) NOT NULL,
respuesta3 varchar(100) NOT NULL,
respuestaCorrecta varchar(100) NOT NULL,
categoria varchar(50)
/*
CONSTRAINT categoria FOREIGN KEY (id)
REFERENCES categoria(id)
*/
);

INSERT preguntas VALUES(1, 'pregunta1', 'incorrecta11', 'incorrecta21', 'incorrecta31', 'correcta1', '1');
INSERT preguntas VALUES(2, 'pregunta2', 'incorrecta12', 'incorrecta22', 'incorrecta32', 'correcta2', '2');
INSERT preguntas VALUES(3, 'pregunta3', 'incorrecta13', 'incorrecta23', 'incorrecta33', 'correcta3', '3');
INSERT preguntas VALUES(4, 'pregunta4', 'incorrecta14', 'incorrecta24', 'incorrecta34', 'correcta4', '4');

