DROP DATABASE IF EXISTS preguntados;
CREATE DATABASE preguntados;

USE preguntados;

CREATE TABLE cuentas
(user varchar(50) PRIMARY KEY,
pass varchar(50),
tipo int);

INSERT cuentas VALUES ('diego', 'diego', 1);
INSERT cuentas VALUES ('eze', 'eze', 1);
INSERT cuentas VALUES ('fer', 'fer', 0);
INSERT cuentas VALUES ('seba', 'seba', 1);
INSERT cuentas VALUES ('pepe', '1234', 1);


CREATE TABLE categoria
(id int PRIMARY KEY,
nombre varchar(30));

INSERT categoria VALUES(1, 'Deportes');
INSERT categoria VALUES(2, 'Entretenimiento');
INSERT categoria VALUES(3, 'Arte y Literatura');
INSERT categoria VALUES(4, 'Ciencia y Tecnología');
INSERT categoria VALUES(5, 'Geografía');
INSERT categoria VALUES(6, 'Historia');


CREATE TABLE preguntas
(ID int PRIMARY KEY,
pregunta varchar(300) NOT NULL,
respuestaCorrecta varchar(100) NOT NULL,
respuesta1 varchar(100) NOT NULL,
respuesta2 varchar(100) NOT NULL,
respuesta3 varchar(100) NOT NULL,
categoria varchar(50)

/*
CONSTRAINT categoria FOREIGN KEY (id)
REFERENCES categoria(id)
*/
);

INSERT preguntas VALUES(1, '¿Cuál era la nacionalidad de Heri Cartier-Bresson, considerado por muchos el padre del fotoreportaje?', 'Francesa', 'Inglesa', 'Alemana', 'Polaca', 'Arte y Literatura');
INSERT preguntas VALUES(2, '¿Cuál es la ciudad fetiche del escritor Paul Auster?', 'Nueva York', 'Londres', 'Roma', 'Berlin', 'Arte y Literatura');
INSERT preguntas VALUES(3, '¿Quién vivía en el 221B de Backer Street?', 'Sherlock Holmes', 'Dorian Gray', 'Hannibal Lecter', 'Bruce Wayne', 'Arte y Literatura');

INSERT preguntas VALUES(4, '¿Qué cambio de estado ocurre en la sublimación?', 'De sólido a gaseoso', 'De sólido a líquido', 'De líquido a gaseoso', 'De gaseoso a Líquido', 'Ciencia y Tecnología');
INSERT preguntas VALUES(5, '¿Qué órgano del cuerpo humano produce la bilis?', 'Hígado', 'Estómago', 'Riñon', 'Páncreas', 'Ciencia y Tecnología');
INSERT preguntas VALUES(6, '¿De qué color es la sange de los peces?', 'Roja', 'Negra', 'Blanca', 'Azul', 'Ciencia y Tecnología');
INSERT preguntas VALUES(7, '¿Cuántas caras tiene un icosaedro?', '20', '19', '21', '22', 'Ciencia y Tecnología');
INSERT preguntas VALUES(8, '¿Cuál es el símbolo de Bromo?', 'Br', 'B', 'Bo', 'Bm', 'Ciencia y Tecnología');
INSERT preguntas VALUES(9, '¿Cuántos lóbulos tiene la glándula tiroides?', '2', '0', '1', '3', 'Ciencia y Tecnología');
INSERT preguntas VALUES(10, '¿Qué es la hemofobia?', 'Miedo a la sangre', 'Miedo a los emos', 'Miedo a la altura', 'Miedo a los insectos', 'Ciencia y Tecnología');
INSERT preguntas VALUES(11, '¿Cuál es la distribución de Linux más usada?', 'Ubuntu', 'RedHat', 'Debian', 'Mandriva', 'Ciencia y Tecnología');
INSERT preguntas VALUES(12, '¿Cuál es el símbolo químico del fósforo?', 'P', 'F', 'Fs', 'S', 'Ciencia y Tecnología');

INSERT preguntas VALUES(13, '¿Cuántos jugadores componen un equipo de rugby?', '15', '16', '17', '18', 'Deportes');
INSERT preguntas VALUES(14, '¿Cuántas puntas de cada color hay en un tablero de backgammon?', '12', '10', '14', '16', 'Deportes');
INSERT preguntas VALUES(15, '¿De qué color es el cero en el cilindro de la ruleta?', 'Verde', 'Rojo', 'Negro', 'Azul', 'Deportes');
INSERT preguntas VALUES(16, '¿Dónde se inventó el tenis de mesa?', 'Inglaterra', 'China', 'Japón', 'España', 'Deportes');
INSERT preguntas VALUES(17, '¿Quién es el máximo goleador de la selección argentina de fútbol?', 'Batistuta', 'Maradona', 'Messi', 'Saviola', 'Deportes');
INSERT preguntas VALUES(18, 'Si durante una mano de Póker me tocan 3 cartas del mismo valor, ¿qué jugada tengo?', 'Trío', 'Full House', 'Poker', 'Color', 'Deportes');
INSERT preguntas VALUES(19, '¿Cuál fué el Mundial de fútbol que consagró a Diego Armando Maradona?', '1986', '1982', '1990', '1978', 'Deportes');

INSERT preguntas VALUES(20, '¿Quién es la mascota de SEGA?', 'Sonic', 'Mario', 'Solid Snake', 'Yitan', 'Entretenimiento');
INSERT preguntas VALUES(21, '¿Cómo se llama el protagonista de la saga Indiana Jones?', 'Harrison Ford', 'Bruce Willis', 'Tom Cruise', 'Russell Crowe', 'Entretenimiento');
INSERT preguntas VALUES(22, '¿Qué personaje del videojuego Mortal Kombat tiene poderes de hielo?', 'Sub-Zero', 'Scorpion', 'Smoke', 'Reptile', 'Entretenimiento');
INSERT preguntas VALUES(23, '¿Cómo se llama el pájaro símbolo de los Juegos del Hambre?', 'Sinsajo', 'Silbón', 'Sinsonte', 'Sisón', 'Entretenimiento');
INSERT preguntas VALUES(24, '¿En que banda canta Anthony Kiedis?', 'Red Hot Chili Peppers', 'Metallica', 'Korn', 'Limp Bizkit', 'Entretenimiento');
INSERT preguntas VALUES(25, '¿A qué se dedica el personaje Ted Mosby de "How I Met your mother"?', 'Arquitecto', 'Abogado', 'Contador', 'Periodista', 'Entretenimiento');
INSERT preguntas VALUES(26, '¿Cuántos capítulos tiene la serie de televisión "Friends"?', '236', '256', '246', '266', 'Entretenimiento');

INSERT preguntas VALUES(27, '¿Con cuántos países limita Argentina?', '5', '4', '6', '7', 'Geografía');
INSERT preguntas VALUES(28, '¿En qué país está la Laguna Verde?', 'Bolivia', 'Perú', 'Ecuador', 'Chile', 'Geografía');
INSERT preguntas VALUES(29, '¿Dónde se encuentra la cordillera de Los Andes?', 'América', 'Europa', 'Asia', 'África', 'Geografía');
INSERT preguntas VALUES(30, '¿De qué colores es la bandera de Bélgica?', 'Negro, amarillo y rojo', 'Blanco, verde y rojo', 'Azul, blanco y rojo', 'Azul y amarillo', 'Geografía');
INSERT preguntas VALUES(31, '¿Cuántas estrellas tiene la bandera de China?', '5', '3', '4', '6', 'Geografía');
INSERT preguntas VALUES(32, '¿De qué país es el panda el animal nacional?', 'China', 'India', 'Australia', 'Nueva Zelanda', 'Geografía');

INSERT preguntas VALUES(33, '¿Cuántos soldados argentinos murieron en la Guerra de las Malvinas?', '649', '678', '653', '637', 'Historia');
INSERT preguntas VALUES(34, '¿Quién pronunció la frase: "Bebamos de la copa de la destrucción"?', 'Gengis Kan', 'Atila', 'Barbarroja', 'Saladino', 'Historia');
INSERT preguntas VALUES(35, '¿Qué se celebra el 8 de Marzo?', 'El día de la Mujer', 'El día de la novia', 'El día de la madre', 'El día del niño', 'Historia');
INSERT preguntas VALUES(36, '¿Cómo murió Jacques de Molay, último gran maestre templario?', 'En la hoguera', 'En la horca', 'En la guillotina', 'Ante un verdugo', 'Historia');
INSERT preguntas VALUES(37, '¿En qué país nació el rey Juan Carlos I?', 'Italia', 'España', 'Inglaterra', 'Francia', 'Historia');
INSERT preguntas VALUES(38, '¿En qué siglo termina la Edad Media?', 'XV', 'XIV', 'IX', 'X', 'Historia');



CREATE TABLE puntuaciones
(user varchar(50) PRIMARY KEY,
puntuacion int,
partidasJugadas int,
partidasGanadas int,
partidasPerdidas int,
preguntasCorrectas int,
preguntasIncorrectas int);

INSERT puntuaciones VALUES ('diego', 0, 0, 0, 0, 0, 0);
INSERT puntuaciones VALUES ('eze', 0, 0, 0, 0, 0, 0);
INSERT puntuaciones VALUES ('fer', 0, 0, 0, 0, 0, 0);
INSERT puntuaciones VALUES ('seba', 0, 0, 0, 0, 0, 0);
INSERT puntuaciones VALUES ('pepe', 0, 0, 0, 0, 0, 0);