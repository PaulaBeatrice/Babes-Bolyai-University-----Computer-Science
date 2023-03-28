CREATE DATABASE JackSparrow
GO
USE JackSparrow

CREATE TABLE Corabii(
	id INT PRIMARY KEY,
	nume VARCHAR(20),
	lemn BIT,
	capitan_id INT
)

CREATE TABLE Pirati(
	id INT PRIMARY KEY,
	nume VARCHAR(10),
	varsta INT,
	corabie_id INT FOREIGN KEY REFERENCES Corabii(id)
)

CREATE TABLE Loves(
	id INT PRIMARY KEY,
	nume VARCHAR(20),
	dimensiune_cupa VARCHAR(10),
	varsta INT
)

CREATE TABLE Misiuni(
	id INT PRIMARY KEY,
	nume VARCHAR(20),
	durata INT
)

CREATE TABLE PiratLove(
	id_pirat INT FOREIGN KEY REFERENCES Pirati(id),
	id_love INT FOREIGN KEY REFERENCES Loves(id),
	CONSTRAINT pk_PRKEY PRIMARY KEY (id_pirat, id_love)
)

CREATE TABLE Recruti(
	id_pirat INT FOREIGN KEY REFERENCES Pirati(id),
	id_misiune INT FOREIGN KEY REFERENCES Misiuni(id),
	CONSTRAINT pk_PRKEY2 PRIMARY KEY (id_pirat, id_misiune)
)

--- 2) Sa se creeze o procedura care asigneaza un pirat la o misiune daca piratul a mai fost in mai putin de 3 misiuni.
--- Params: id_pirat, id_misiune
CREATE OR ALTER PROCEDURE UpAdd
@pirat_id INT, @misiune_id INT
AS
	DECLARE @nrm INT = 0
	SELECT @nrm=COUNT(*) FROM Recruti WHERE id_pirat=@pirat_id

	IF @nrm < 3
		INSERT INTO Recruti(id_pirat, id_misiune) VALUES (@pirat_id, @misiune_id)
GO		
	
--- 3) View loves care sunt virgine si au dimensiune cupei DD
CREATE VIEW vw_VirginDDLoves
AS
	SELECT id FROM Loves WHERE id NOT IN (SELECT id_love FROM PiratLove) AND dimensiune_cupa = 'DD'
	
--- 4) Nr total de loves ai piratilor din corabia a carui capitan e dat
SELECT COUNT(DISTINCT PL.id_love) FROM Corabii C INNER JOIN Pirati P ON P.corabie_id = C.id
												 INNER JOIN PiratLove PL ON PL.id_pirat=P.id
													WHERE C.capitan_id = 1

--- 5) Cea mai iubita love?
SELECT TOP 1 PL.id_love
FROM PiratLove PL
GROUP BY PL.id_love
ORDER BY COUNT(PL.id_love) DESC
WITH QUEEN AS
(SELECT id_love, COUNT(*) AS C
 FROM PiratLove PL
 GROUP BY PL.id_love
)
SELECT TOP 1 id_love FROM QUEEN ORDER BY QUEEN.C DESC