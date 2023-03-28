CREATE DATABASE Sectie
GO
USE Sectie

CREATE TABLE Grade(
	id INT PRIMARY KEY IDENTITY (1,1),
	denumire VARCHAR(100)
)

CREATE TABLE Sectoare(
	id INT PRIMARY KEY IDENTITY (1,1),
	denumire VARCHAR(100)
)

CREATE TABLE Sectii(
	id INT PRIMARY KEY IDENTITY (1,1),
	denumire VARCHAR(100),
	adresa VARCHAR(100)
)

CREATE TABLE Politisti(
	id INT PRIMARY KEY IDENTITY (1,1),
	nume VARCHAR(100),
	prenume VARCHAR(100),
	sectie INT FOREIGN KEY REFERENCES Sectii(id),
	grad INT FOREIGN KEY REFERENCES Grade(id)
)

CREATE TABLE PolitistiSectie(
	politist INT FOREIGN KEY REFERENCES Politisti(id),
	sectie INT FOREIGN KEY REFERENCES Sectii(id),
	dataIntrare DATETIME,
	oraIntrare TIME,
	dataIesire DATETIME,
	oraIesire TIME
)

--- View care afiseaza lista politistilor ordonata alfabetic dupa sectie si dupa numele politistilor care sa prezinte nr total de ore de munca
--- pt fiecare politist pr luna ianuarie a anului curent
CREATE OR ALTER VIEW ViewPolitisti
AS
	SELECT P.nume AS numeP, S.denumire AS numeS, COUNT(PS.politist)*8 AS nrOre FROM Politisti P INNER JOIN PolitistiSectie PS ON PS.politist=P.id
																					INNER JOIN Sectii S ON PS.sectie=S.ID
																					GROUP BY S.denumire,P.nume
GO 

CREATE OR ALTER VIEW ViewPolitisti2
AS
	SELECT P.nume AS numeP, S.denumire AS numeS FROM Politisti P INNER JOIN PolitistiSectie PS ON PS.politist=P.id
																					INNER JOIN Sectii S ON PS.sectie=S.ID
																					GROUP BY P.nume
GO



SELECT * FROM ViewPolitisti2  ORDER BY numeP
GO