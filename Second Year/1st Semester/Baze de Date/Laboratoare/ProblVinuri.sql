CREATE DATABASE Vinuri
GO
USE Vinuri

CREATE TABLE Soiuri(
	id_S INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	calitate VARCHAR(100)
)

CREATE TABLE Tari(
	id_T INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100)
)

CREATE TABLE Somelieri(
	id_Sm INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	telefon VARCHAR(100),
	email VARCHAR(100)
)

CREATE TABLE Vinuri(
	id_V INT PRIMARY KEY IDENTITY(1,1),
	denumire VARCHAR(100),
	descriere VARCHAR(100),
	an_fabricatie INT,
	tara INT FOREIGN KEY REFERENCES Tari(id_T),
	soi INT FOREIGN KEY REFERENCES Soiuri(id_S)
)

CREATE TABLE Degustari(
	vin INT FOREIGN KEY REFERENCES Vinuri(id_V),
	somelier INT FOREIGN KEY REFERENCES Somelieri(id_Sm),
	CONSTRAINT PK_Degustari PRIMARY KEY(vin,somelier),
	comentariu VARCHAR(100)
)


INSERT INTO Soiuri(nume, calitate) VALUES ('S1', 'superioara'),('S2', 'superioara'),('S3', 'decenta')
INSERT INTO Tari(nume) VALUES ('China'),('Moldova'), ('Peru')
INSERT INTO Somelieri(nume, telefon,email) VALUES ('Alice','046566556','aa@gmail.com'),('Amy','04444','T@gmail.com'),('Antonia','04366.','ooo@gmail.com')
INSERT INTO Vinuri(denumire,descriere,an_fabricatie,tara,soi) VALUES ('V1','BUN',2002,1,1), ('V2','dulce',2010,1,2),('V3','ok',2002,2,3)
INSERT INTO Degustari(vin,somelier,comentariu) VALUES(1,1,'perfect')

SELECT * FROM Degustari
--- Creați o procedură stocată care primește un vin, un somelier și un comentariu și adaugă comentariul lăsat de somelier vinului.
--- Dacă există deja un comentariu lăsat de somelier vinului, comentariul va fi actualizat. 
CREATE OR ALTER PROCEDURE UpAddDegustare
@vin VARCHAR(100),
@somelier VARCHAR(100),
@comentariu VARCHAR(100)
AS
BEGIN
	DECLARE @idV INT;
	DECLARE @idS INT;
	SELECT TOP 1 @idV=id_V FROM Vinuri WHERE denumire = @vin
	SELECT TOP 1 @idS=id_Sm FROM Somelieri WHERE nume = @somelier

	IF(@idV IS NULL OR @idS IS NULL)
		THROW 50003,'Nu exista vin sau somelier!',1

	IF EXISTS(SELECT * FROM Degustari WHERE vin=@idV AND somelier=@idS)
		BEGIN
			UPDATE Degustari SET comentariu=@comentariu WHERE vin=@idV AND somelier=@idS
		END
	ELSE
		BEGIN 
			INSERT INTO Degustari(vin,somelier,comentariu) VALUES(@idV,@idS,@comentariu)
		END
END

GO

EXEC UpAddDegustare 'V1','Alice','se poate mai bine'

INSERT INTO Degustari(vin,somelier,comentariu) VALUES(1,2,'perfect'), (2,3,'Bine rau')
INSERT INTO Degustari(vin,somelier,comentariu) VALUES(1,3,'perfect')
---- Creați o funcție definită de către utilizator care afișează denumirea vinului și numărul de comentarii lăsate de somelieri pentru toate
---- vinurile care au numărul de comentarii diferit de 2. 

GO
CREATE FUNCTION Functie()
RETURNS TABLE AS
RETURN SELECT  V.denumire, COUNT(*) AS nrComentarii FROM Vinuri V RIGHT JOIN Degustari D ON V.id_V=D.vin GROUP BY V.denumire HAVING COUNT(*) <> 2

GO
SELECT * FROM dbo.Functie()