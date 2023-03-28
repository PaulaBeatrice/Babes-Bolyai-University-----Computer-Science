CREATE DATABASE Examen
GO
USE Examen

CREATE TABLE Hala(
	idH INT PRIMARY KEY IDENTITY(1,1),
	litera VARCHAR(2),
	suprafata INT
)

CREATE TABLE Tarabe(
	idT INT PRIMARY KEY IDENTITY(1,1),
	suprafata INT,
	nr INT,
	hala INT FOREIGN KEY REFERENCES Hala(idH)
)

CREATE TABLE Categorii(
	idC INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100)
)

CREATE TABLE TarabeCategorii(
	taraba INT FOREIGN KEY REFERENCES Tarabe(idT),
	categorie INT FOREIGN KEY REFERENCES Categorii(idC),
	CONSTRAINT pk_TarabeCategorii PRIMARY KEY(taraba,categorie)
)

CREATE TABLE Produse(
	idP INT PRIMARY KEY IDENTITY(1,1),
	denumire VARCHAR(100),
	pret INT,
	categorie INT FOREIGN KEY REFERENCES Categorii(idC)
)


-- Procedura ce actualizeaza pretul produselor de pe o taraba data
CREATE OR ALTER PROCEDURE UpdatePret
@idTaraba INT
AS BEGIN
	DECLARE @gasit INT
	SELECT TOP 1 @gasit = idT FROM Tarabe WHERE idT=@idTaraba
	IF(@gasit IS NULL)
		THROW 50003,'Nu exista taraba!',1
	DECLARE @pret INT
	DECLARE @idProdus INT
						   
	SELECT TOP 1 @idProdus=P.idP FROM Tarabe T INNER JOIN TarabeCategorii TC ON T.idT=TC.taraba
						   LEFT JOIN Categorii C ON C.idC = TC.categorie
						   LEFT JOIN Produse P ON C.idC=P.categorie 
						   WHERE idT=@idTaraba
	SELECT @pret = pret FROM Produse WHERE idP=@idProdus
	if(@pret < 100)
		UPDATE Produse SET pret=pret + 10 WHERE idP = @idProdus
	if(@pret > 200)
		UPDATE Produse SET pret=pret + 50 WHERE idP = @idProdus
	if(@pret > 100 AND @pret < 200)
		UPDATE Produse SET pret=pret + 10/100 * pret WHERE idP = @idProdus
END

EXEC UpdatePret 1

SELECT * FROM Produse

--- View ce afiseaza produsele(denumire, pret) avand pretul redus cu 60% din categoria "haine" si "vesela" din halele A, F sau X
CREATE OR ALTER VIEW viewProduse
AS
	SELECT denumire, pret FROM Produse P INNER JOIN Categorii C ON C.idC=P.categorie
										 INNER JOIN TarabeCategorii TC ON TC.categorie=C.idC
										 INNER JOIN Tarabe T ON TC.taraba=T.idT
										 INNER JOIN Hala H ON H.idH = T.hala
										 WHERE NOT(C.nume IN ('haine','vesela') AND H.litera IN('A','F','X'))
	UNION 
	SELECT denumire, pret - pret * 60 / 100 FROM Produse P INNER JOIN Categorii C ON C.idC=P.categorie
										 INNER JOIN TarabeCategorii TC ON TC.categorie=C.idC
										 INNER JOIN Tarabe T ON TC.taraba=T.idT
										 INNER JOIN Hala H ON H.idH = T.hala
										 WHERE C.nume IN ('haine','vesela') AND H.litera IN('A','F','X')
GO
SELECT * FROM viewProduse


INSERT INTO Hala(litera,suprafata) VALUES('A',1522),('B',1444),('C',1458)
INSERT INTO Tarabe(suprafata,nr,hala) VALUES(123,1,1),(1222,2,1),(12345,3,2)
INSERT INTO Categorii(nume) VALUES('haine'),('vesela'),('jucarii')
INSERT INTO TarabeCategorii(taraba,categorie) VALUES(1,1),(1,2),(2,3)
INSERT INTO Produse(denumire,pret,categorie) VALUES('tricou',50,1),('cana',40,2),('bluza',200,1)
INSERT INTO Produse(denumire,pret,categorie) VALUES('masina',150,3)


SELECT * FROM Hala
SELECT * FROM Tarabe
SELECT * FROM Categorii
SELECT * FROM TarabeCategorii
SELECT * FROM Produse




CREATE OR ALTER PROCEDURE UpdatePret2
@idTaraba INT
AS BEGIN
	DECLARE @gasit INT
	SELECT TOP 1 @gasit = idT FROM Tarabe WHERE idT=@idTaraba
	IF(@gasit IS NULL)
		THROW 50003,'Nu exista taraba!',1
	DECLARE @pret INT
					
	SELECT * FROM Tarabe T INNER JOIN TarabeCategorii TC ON T.idT=TC.taraba
						   INNER JOIN Categorii C ON C.idC = TC.categorie
						   INNER JOIN Produse P ON C.idC=P.categorie 
						   WHERE idT=@idTaraba  UPDATE Produse SET pret=pret+10 WHERE pret <100
												UPDATE Produse SET pret=pret+50 WHERE pret > 200
												UPDATE Produse SET pret=pret+pret *10/100 WHERE pret > 100 AND pret < 200
END

EXEC UpdatePret2 1

insert into Produse(denumire,pret,categorie) VALUES('cravata',15,1)
insert into Produse(denumire,pret,categorie) VALUES('sacou',115,1)
SELECT * FROM Produse
--- produsele unei Tarabe
