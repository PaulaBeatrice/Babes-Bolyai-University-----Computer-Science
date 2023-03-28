CREATE DATABASE Spa
GO
USE Spa

CREATE TABLE Clienti(
	idC INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	prenume VARCHAR(100),
	ocupatie VARCHAR(100)
)

CREATE TABLE Orase(
	idO INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100)
)

CREATE TABLE CentreSpa(
	idCS INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	sitewev VARCHAR(100),
	oras INT FOREIGN KEY REFERENCES Orase(idO)
)

CREATE TABLE ServiciiSpa(
	idS INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	descriere VARCHAR(100),
	pret INT,
	recomandare VARCHAR(100),
	centru INT FOREIGN KEY REFERENCES CentreSpa(idCS)
)

CREATE TABLE ServiciiClienti(
	serviciu INT FOREIGN KEY REFERENCES ServiciiSpa(idS),
	client INT FOREIGN KEY REFERENCES Clienti(idC),
	CONSTRAINT PK_ServCl PRIMARY KEY(serviciu,client),
	nota INT
)

INSERT INTO Clienti(nume,prenume,ocupatie) VALUES('A','DDD','VGSDG'),('B','DDD','VGSDG'),('C','DDD','VGSDG')
INSERT INTO Orase(nume) VALUES('CJ'),('SB'),('IS')
INSERT INTO CentreSpa(nume,sitewev,oras) VALUES('C1','sfsf',1),('C2','sfsf',1),('C3','sfsf',2)
INSERT INTO ServiciiSpa(nume,descriere,pret,recomandare,centru) VALUES('S1','',10,'ok',1),('S2','ddd',10,'ok',1),('S3','dd',10,'ok',3)
INSERT INTO ServiciiClienti(serviciu,client,nota) VALUES(1,1,10),(2,2,8),(3,3,5)


--- Creați o procedură stocată care primește un serviciu spa, un client și o notă și adaugă noul serviciu spa clientului.
--- Dacă serviciul spa a fost deja adăugat, se va actualiza valoarea notei. 
CREATE OR ALTER PROCEDURE CerintaB
@serviciu VARCHAR(100),
@client VARCHAR(100),
@nota INT
AS 
BEGIN
	DECLARE @idS INT;
	DECLARE @idC INT;
	SELECT TOP 1 @idC=idC FROM Clienti WHERE nume=@client
	SELECT TOP 1 @idS=idS FROM ServiciiSpa WHERE nume=@serviciu

	IF(@idC IS NULL OR @idS IS NULL)
		THROW 50003, 'Nu exista serviciul sau clientul!', 1

	IF EXISTS (SELECT * FROM ServiciiClienti WHERE serviciu=@idS AND client=@idC)
		BEGIN
			UPDATE ServiciiClienti SET nota=@nota WHERE serviciu=@idS AND client=@idC
		END
	ELSE
		BEGIN
			INSERT INTO ServiciiClienti(serviciu,client,nota) VALUES(@idS,@idC,@nota)
		END
END

SELECT * FROM ServiciiClienti
EXEC CerintaB 'S2','A',10

--- Creați o funcție definită de utilizator care afișează numele centrului spa, numele și descrierea serviciului spa, nota și numele clientului
--- pentru toate serviciile spa care au descrierea diferită de NULL. 
GO
CREATE FUNCTION Functie()
RETURNS TABLE AS
RETURN SELECT CS.nume AS numeSpa, S.nume AS numeSV, S.descriere, C.nume AS numeCl, SC.nota FROM
													CentreSpa CS RIGHT JOIN ServiciiSpa S ON CS.idCS=S.centru
																 RIGHT JOIN ServiciiClienti SC ON S.idS=SC.client
																 LEFT JOIN Clienti C ON C.idC = SC.client
																		WHERE S.descriere <> ''

GO
SELECT * FROM dbo.Functie()

INSERT INTO ServiciiSpa(nume,descriere,pret,recomandare,centru) VALUES('S4',NULL,100,'merge',2)
INSERT INTO ServiciiClienti(serviciu,client,nota) VALUES(4,1,4)