CREATE DATABASE Restaurante
GO
USE Restaurante

CREATE TABLE Tipuri(
	id_T INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	descriere VARCHAR(100)
)

CREATE TABLE Orase(
	id_O INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100)
)

CREATE TABLE Utilizatori(
	id_U INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	email VARCHAR(100),
	parola VARCHAR(100)
)

CREATE TABLE Restaurante(
	id_R INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	adresa VARCHAR(100),
	telefon VARCHAR(100),
	oras INT FOREIGN KEY REFERENCES Orase(id_O),
	tip INT FOREIGN KEY REFERENCES Tipuri(id_T)
)

CREATE TABLE RestauranteUtilizatori(
	restaurant INT FOREIGN KEY REFERENCES Restaurante(id_R),
	utilizator INT FOREIGN KEY REFERENCES Utilizatori(id_U),
	CONSTRAINT PK_restauranteUtilizatori PRIMARY KEY(restaurant, utilizator),
	nota FLOAT
)

INSERT INTO Tipuri(nume, descriere) VALUES ('A','aaaa'), ('B','bbbb'), ('C','cccc')
INSERT INTO Orase(nume) VALUES('Cluj'),('Sibiu'),('Bistrita')
INSERT INTO Utilizatori(nume,email,parola) VALUES ('Ana','a@email','wgwg'),('Bia','a@email','fw'),('Carla','a@email','fe')
INSERT INTO Restaurante(nume,adresa,telefon,oras,tip) VALUES('A','dfesws','07654345678',1,1),('B','gg','443443',3,2),('C','434','43',1,2)
INSERT INTO RestauranteUtilizatori(restaurant,utilizator,nota) VALUES(1,1,8.5)

-- Creați o procedură stocată care primește un restaurant, un utilizator și o notă și adaugă nota dată de către utilizator restaurantului.
-- Dacă utilizatorul a dat deja o notă acelui restaurant, valoarea notei va fi actualizată. 
CREATE OR ALTER PROCEDURE UpAddNota
@restaurant VARCHAR(100),
@utilizator VARCHAR(100),
@nota FLOAT
AS 
BEGIN
	DECLARE @idR INT;
	DECLARE @idU INT;
	SELECT TOP 1 @idR=id_R FROM Restaurante WHERE nume=@restaurant
	SELECT TOP 1 @idU=id_U FROM Utilizatori WHERE nume=@utilizator

	IF(@idR IS NULL OR @idU IS NULL)
		THROW 50003, 'Nu exista restaurantul sau utilizatorul!',1;

	IF EXISTS(SELECT * FROM RestauranteUtilizatori WHERE restaurant=@idR AND utilizator=@idU)
		BEGIN
			UPDATE RestauranteUtilizatori SET nota=@nota WHERE restaurant=@idR AND utilizator=@idU
		END
	ELSE
		BEGIN
			INSERT INTO RestauranteUtilizatori(restaurant,utilizator,nota) VALUES(@idR,@idU,@nota);
		END
END

EXEC UpAddNota 'A', 'Bia', 4

EXEC UpAddNota 'C', 'Bia', 8

SELECT * FROM RestauranteUtilizatori

-- Creați un view care afișează numele restaurantului și media aritmetică a notelor pentru fiecare restaurant care are media aritmetică a notelor 
-- strict mai mare decât 5. 

CREATE OR ALTER VIEW restauranteView
AS
	SELECT R.nume, AVG(RU.nota) AS medieNote FROM RestauranteUtilizatori RU LEFT JOIN Restaurante R ON RU.restaurant=R.id_R GROUP BY R.nume HAVING AVG(RU.nota) >= 5
GO

SELECT * FROM restauranteView
