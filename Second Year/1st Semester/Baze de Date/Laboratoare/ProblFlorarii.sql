CREATE DATABASE Florarie
GO
USE Florarie

CREATE TABLE Categorii(
	id_C INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100)
)

CREATE TABLE Florarii(
	id_F INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	telefon VARCHAR(100),
	adresa VARCHAR(100)
)

CREATE TABLE Plante(
	id_P INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	descriere VARCHAR(100)
)

CREATE TABLE Aranjamente(
	id_A INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	pret INT,
	descriere VARCHAR(100),
	inaltime FLOAT,
	categorie INT FOREIGN KEY REFERENCES Categorii(id_C),
	florarie INT FOREIGN KEY REFERENCES Florarii(id_F)
)

CREATE TABLE AranjamentePlante(
	aranjament INT FOREIGN KEY REFERENCES Aranjamente(id_A),
	planta INT FOREIGN KEY REFERENCES Plante(id_P),
	CONSTRAINT PK_aranjPl PRIMARY KEY(aranjament,planta),
	nr_exemplare INT
)


INSERT INTO Categorii(nume) VALUES('a'),('b'),('c')
INSERT INTO Florarii(nume,telefon,adresa) VALUES('F1','74566','fsfs'),('F2','74566','fsfs'),('F3','74566','fsfs')
INSERT INTO Plante(nume,descriere) VALUES('P1','sfw'),('P2','sfw'),('P3','sfw')
INSERT INTO Aranjamente(nume,pret,descriere,inaltime,categorie,florarie) VALUES('A1',100,'FFSV',1.2,1,1),('A2',100,'FFSV',1.2,1,2),('A3',100,'FFSV',1.2,2,3)
INSERT INTO AranjamentePlante(aranjament,planta,nr_exemplare) VALUES(1,1,20)
--- Creați o procedură stocată care primește un aranjament floral, o plantă și un număr de exemplare și adaugă noua plantă aranjamentului floral.
--- Dacă planta a fost deja adăugată aranjamentului floral, se va actualiza numărul de exemplare.

CREATE OR ALTER PROCEDURE UpAddArPl
@aranjament VARCHAR(100),
@planta VARCHAR(100),
@nr INT
AS
BEGIN
	DECLARE @idA INT;
	DECLARE @idP INT;
	SELECT TOP 1 @idA=id_A FROM Aranjamente WHERE nume=@aranjament
	SELECT TOP 1 @idP=id_P FROM Plante WHERE nume=@planta
	IF(@idA IS NULL OR @idP IS NULL)
		THROW 50003, 'Nu exista aranjamentul sau planta',1

	IF EXISTS(SELECT * FROM AranjamentePlante WHERE aranjament=@idA AND planta=@idP)
		BEGIN
			UPDATE AranjamentePlante SET nr_exemplare=@nr  WHERE aranjament=@idA AND planta=@idP
		END
	ELSE
		BEGIN
			INSERT INTO AranjamentePlante(aranjament,planta,nr_exemplare) VALUES(@idA,@idP,@nr)
		END
END

SELECT * FROM AranjamentePlante

GO
EXEC UpAddArPl 'A2','P3',15
EXEC UpAddArPl 'A1','P1',30

EXEC UpAddArPl 'A','P1',30


-- Creați un view care afișează numele florăriei, numele aranjamentului floral, prețul aranjamentului floral, numărul de exemplare și 
-- numele plantei pentru toate florăriile al căror nume nu începe cu litera ‘M’. 

CREATE OR ALTER VIEW viewFlorarii
AS
	SELECT F.nume AS numeF,A.nume AS numeA,A.pret,AP.nr_exemplare,P.nume AS numeP FROM Plante P RIGHT JOIN AranjamentePlante AP ON P.id_P=AP.planta
																								LEFT JOIN Aranjamente A ON AP.aranjament=A.id_A
																								LEFT JOIN Florarii F ON A.florarie=F.id_F
																									WHERE F.nume LIKE '[^M]%';
GO

SELECT * FROM Aranjamente

INSERT INTO Florarii(nume,telefon,adresa) VALUES('MMMM','333','DS')
INSERT INTO Aranjamente(nume,pret,descriere,inaltime,categorie,florarie) VALUES('NU I BINE',12,'svd',4,1,4)
INSERT INTO AranjamentePlante (aranjament,planta,nr_exemplare) VALUES(7,2,20)

SELECT * FROM viewFlorarii