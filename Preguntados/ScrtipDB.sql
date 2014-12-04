DROP DATABASE IF EXISTS preguntados;
CREATE DATABASE preguntados;

USE preguntados;

CREATE TABLE user
(user varchar(50) PRIMARY KEY,
password varchar(50),
userType int);

INSERT user VALUES ('admin', 'admin', 0);
INSERT user VALUES ('fer', 'fer', 1);
INSERT user VALUES ('diego', 'diego', 1);
INSERT user VALUES ('eze', 'eze', 1);
INSERT user VALUES ('seba', 'seba', 1);
INSERT user VALUES ('pepe', 'pepe', 1);
INSERT user VALUES ('juan', 'juan', 1);
INSERT user VALUES ('carlos', 'carlos', 1);
INSERT user VALUES ('lucas', 'lucas', 1);
INSERT user VALUES ('julio', 'julio', 1);
INSERT user VALUES ('tequi', 'tequi', 1);
INSERT user VALUES ('dark', 'dark', 1);


CREATE TABLE category
(id int PRIMARY KEY,
name varchar(30));

INSERT category VALUES(1, 'Deportes');
INSERT category VALUES(2, 'Entretenimiento');
INSERT category VALUES(3, 'Arte y Literatura');
INSERT category VALUES(4, 'Ciencia y Tecnología');
INSERT category VALUES(5, 'Geografía');
INSERT category VALUES(6, 'Historia');


CREATE TABLE question
(id int PRIMARY KEY,
question varchar(300) NOT NULL,
correctAnswer varchar(100) NOT NULL,
answer1 varchar(100) NOT NULL,
answer2 varchar(100) NOT NULL,
answer3 varchar(100) NOT NULL,
categoryId int,
CONSTRAINT categoryId FOREIGN KEY(categoryId)
REFERENCES category(id)
);

INSERT question VALUES(1, '¿Cuál era la nacionalidad de Heri Cartier-Bresson, considerado por muchos el padre del fotoreportaje?', 'Francesa', 'Inglesa', 'Alemana', 'Polaca', 3);
INSERT question VALUES(2, '¿Cuál es la ciudad fetiche del escritor Paul Auster?', 'Nueva York', 'Londres', 'Roma', 'Berlin', 3);
INSERT question VALUES(3, '¿Quién vivía en el 221B de Backer Street?', 'Sherlock Holmes', 'Dorian Gray', 'Hannibal Lecter', 'Bruce Wayne', 3);

INSERT question VALUES(4, '¿Qué cambio de estado ocurre en la sublimación?', 'De sólido a gaseoso', 'De sólido a líquido', 'De líquido a gaseoso', 'De gaseoso a Líquido', 4);
INSERT question VALUES(5, '¿Qué órgano del cuerpo humano produce la bilis?', 'Hígado', 'Estómago', 'Riñon', 'Páncreas', 4);
INSERT question VALUES(6, '¿De qué color es la sange de los peces?', 'Roja', 'Negra', 'Blanca', 'Azul', 4);
INSERT question VALUES(7, '¿Cuántas caras tiene un icosaedro?', '20', '19', '21', '22', 4);
INSERT question VALUES(8, '¿Cuál es el símbolo de Bromo?', 'Br', 'B', 'Bo', 'Bm', 4);
INSERT question VALUES(9, '¿Cuántos lóbulos tiene la glándula tiroides?', '2', '0', '1', '3', 4);
INSERT question VALUES(10, '¿Qué es la hemofobia?', 'Miedo a la sangre', 'Miedo a los emos', 'Miedo a la altura', 'Miedo a los insectos', 4);
INSERT question VALUES(11, '¿Cuál es la distribución de Linux más usada?', 'Ubuntu', 'RedHat', 'Debian', 'Mandriva', 4);
INSERT question VALUES(12, '¿Cuál es el símbolo químico del fósforo?', 'P', 'F', 'Fs', 'S', 4);

INSERT question VALUES(13, '¿Cuántos jugadores componen un equipo de rugby?', '15', '16', '17', '18', 1);
INSERT question VALUES(14, '¿Cuántas puntas de cada color hay en un tablero de backgammon?', '12', '10', '14', '16', 1);
INSERT question VALUES(15, '¿De qué color es el cero en el cilindro de la ruleta?', 'Verde', 'Rojo', 'Negro', 'Azul', 1);
INSERT question VALUES(16, '¿Dónde se inventó el tenis de mesa?', 'Inglaterra', 'China', 'Japón', 'España', 1);
INSERT question VALUES(17, '¿Quién es el máximo goleador de la selección argentina de fútbol?', 'Batistuta', 'Maradona', 'Messi', 'Saviola', 1);
INSERT question VALUES(18, 'Si durante una mano de Póker me tocan 3 cartas del mismo valor, ¿qué jugada tengo?', 'Trío', 'Full House', 'Poker', 'Color', 1);
INSERT question VALUES(19, '¿Cuál fué el Mundial de fútbol que consagró a Diego Armando Maradona?', '1986', '1982', '1990', '1978', 1);

INSERT question VALUES(20, '¿Quién es la mascota de SEGA?', 'Sonic', 'Mario', 'Solid Snake', 'Yitan', 2);
INSERT question VALUES(21, '¿Cómo se llama el protagonista de la saga Indiana Jones?', 'Harrison Ford', 'Bruce Willis', 'Tom Cruise', 'Russell Crowe', 2);
INSERT question VALUES(22, '¿Qué personaje del videojuego Mortal Kombat tiene poderes de hielo?', 'Sub-Zero', 'Scorpion', 'Smoke', 'Reptile', 2);
INSERT question VALUES(23, '¿Cómo se llama el pájaro símbolo de los Juegos del Hambre?', 'Sinsajo', 'Silbón', 'Sinsonte', 'Sisón', 2);
INSERT question VALUES(24, '¿En que banda canta Anthony Kiedis?', 'Red Hot Chili Peppers', 'Metallica', 'Korn', 'Limp Bizkit', 2);
INSERT question VALUES(25, '¿A qué se dedica el personaje Ted Mosby de "How I Met your mother"?', 'Arquitecto', 'Abogado', 'Contador', 'Periodista', 2);
INSERT question VALUES(26, '¿Cuántos capítulos tiene la serie de televisión "Friends"?', '236', '256', '246', '266', 2);

INSERT question VALUES(27, '¿Con cuántos países limita Argentina?', '5', '4', '6', '7', 5);
INSERT question VALUES(28, '¿En qué país está la Laguna Verde?', 'Bolivia', 'Perú', 'Ecuador', 'Chile', 5);
INSERT question VALUES(29, '¿Dónde se encuentra la cordillera de Los Andes?', 'América', 'Europa', 'Asia', 'África', 5);
INSERT question VALUES(30, '¿De qué colores es la bandera de Bélgica?', 'Negro, amarillo y rojo', 'Blanco, verde y rojo', 'Azul, blanco y rojo', 'Azul y amarillo', 5);
INSERT question VALUES(31, '¿Cuántas estrellas tiene la bandera de China?', '5', '3', '4', '6', 5);
INSERT question VALUES(32, '¿De qué país es el panda el animal nacional?', 'China', 'India', 'Australia', 'Nueva Zelanda', 5);

INSERT question VALUES(33, '¿Cuántos soldados argentinos murieron en la Guerra de las Malvinas?', '649', '678', '653', '637', 6);
INSERT question VALUES(34, '¿Quién pronunció la frase: "Bebamos de la copa de la destrucción"?', 'Gengis Kan', 'Atila', 'Barbarroja', 'Saladino', 6);
INSERT question VALUES(35, '¿Qué se celebra el 8 de Marzo?', 'El día de la mujer', 'El día de la novia', 'El día de la madre', 'El día del niño', 6);
INSERT question VALUES(36, '¿Cómo murió Jacques de Molay, último gran maestre templario?', 'En la hoguera', 'En la horca', 'En la guillotina', 'Ante un verdugo', 6);
INSERT question VALUES(37, '¿En qué país nació el rey Juan Carlos I?', 'Italia', 'España', 'Inglaterra', 'Francia', 6);
INSERT question VALUES(38, '¿En qué siglo termina la Edad Media?', 'XV', 'XIV', 'IX', 'X', 6);

CREATE TABLE score
(user varchar(50) PRIMARY KEY,
gamesPlayed int,
gamesWon int,
gamesLost int,
correctAnswers int,
wrongAnswers int,
CONSTRAINT user FOREIGN KEY (user)
REFERENCES user(user)
);

INSERT score VALUES ('admin', 50, 20, 30, 200, 300);
INSERT score VALUES ('fer', 0, 0, 0, 0, 0);
INSERT score VALUES ('diego', 0, 0, 0, 0, 0);
INSERT score VALUES ('eze', 0, 0, 0, 0, 0);
INSERT score VALUES ('seba', 0, 0, 0, 0, 0);
INSERT score VALUES ('pepe', 0, 0, 0, 0, 0);
INSERT score VALUES ('juan', 0, 0, 0, 0, 0);
INSERT score VALUES ('carlos', 0, 0, 0, 0, 0);
INSERT score VALUES ('lucas', 0, 0, 0, 0, 0);
INSERT score VALUES ('julio', 0, 0, 0, 0, 0);
INSERT score VALUES ('tequi', 100, 100, 0, 1000, 0);
INSERT score VALUES ('dark', 0, 0, 0, 0, 0);
