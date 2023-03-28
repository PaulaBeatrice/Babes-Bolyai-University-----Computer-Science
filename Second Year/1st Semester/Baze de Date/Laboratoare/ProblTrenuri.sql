--- –1) Scriețiun script SQL care creeazăun model relaționalpentrua reprezenta datele. ---
CREATE DATABASE Trenuri
GO
USE Trenuri

CREATE TABLE Tipuri(
	id_tip INT PRIMARY KEY IDENTITY(1,1),
	descriere VARCHAR(100)
)

CREATE TABLE Trenuri(
	id_tren INT PRIMARY KEY IDENTITY(1,1),
	id_tip INT FOREIGN KEY REFERENCES Tipuri(id_tip),
	nume VARCHAR(100)
)

CREATE TABLE Rute(
	id_ruta INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	id_tren INT FOREIGN KEY REFERENCES Trenuri(id_tren)
)

CREATE TABLE Statii(
	id_statie INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100)
)

CREATE TABLE RutaStatie(
	id_ruta INT FOREIGN KEY REFERENCES Rute(id_ruta),
	id_statie INT FOREIGN KEY REFERENCES Statii(id_statie),
	ora_sosirii TIME,
	ora_plecarii TIME,
	CONSTRAINT pk_rutastatie PRIMARY KEY(id_ruta, id_statie)
)

---- INSERT URI IN TABELE -----

INSERT INTO Tipuri(descriere) VALUES ('de jucarie'), ('tgv'), ('accelerat'), ('cu aburi')
INSERT INTO Trenuri(nume, id_tip) VALUES ('thomas', 4), ('james', 1), ('trenul lui stefan', 3), ('trenul lui iulian', 2), ('abcd', 1)
INSERT INTO Rute(nume, id_tren) VALUES ('cluj-brasov', 3), ('cluj-bucuresti', 4), ('roma-budapesta', 2), ('iasi-timisoara', 1)
INSERT INTO Statii(nume) VALUES ('cluj'), ('budapesta'), ('timisoara'), ('bucuresti'), ('roma')
INSERT INTO RutaStatie(id_ruta, id_statie, ora_sosirii, ora_plecarii) VALUES (1, 1, '12:40', '12:45'), (2, 1, '12:35', '12:42'), (3, 5, '22:30', '22:37'),
    (4, 3, '22:35', '22:40')

SELECT * FROM Tipuri;
SELECT * FROM Trenuri;
SELECT * FROM Rute;
SELECT * FROM Statii;
SELECT * FROM RutaStatie;

--- –2) Creați o procedură stocată care primește o rută, o stație, ora sosirii, ora plecării și adaugă noua stație rutei. 
--- Dacă stația există deja, se actualizează ora sosirii și ora plecării. 
CREATE OR ALTER PROCEDURE Upsert
@numeRuta VARCHAR(100),
@numeStatie VARCHAR(100),
@oraSosire TIME,
@oraPlecare TIME
AS 
BEGIN
	DECLARE @idRuta INT;
	DECLARE @idStatie INT;
	SELECT TOP 1 @idRuta=id_ruta FROM Rute WHERE nume = @numeRuta;
	SELECT TOP 1 @idStatie=id_statie FROM Statii WHERE nume = @numeStatie;

	IF(@idRuta IS NULL OR @idStatie IS NULL)
		THROW 50003, 'Nu exista statia sau ruta!', 1;

	IF EXISTS(SELECT * FROM RutaStatie WHERE id_ruta=@idRuta AND id_statie=@idStatie)
		BEGIN
			UPDATE RutaStatie SET ora_sosirii = @oraSosire, ora_plecarii = @oraPlecare WHERE id_statie = @idStatie AND id_ruta = @idRuta
		END
	ELSE
		BEGIN
			INSERT INTO RutaStatie(id_ruta, id_statie, ora_sosirii, ora_plecarii) VALUES (@idRuta, @idStatie, @oraSosire, @oraPlecare);
		END
END

GO
EXEC Upsert 'cluj-bucuresti', 'cluj', '10:45', '11:00'
EXEC Upsert 'iasi-timisoara', 'roma', '08:00', '08:30'


--- –3) Creați un view care afișează numele rutelor care conțin toate stațiile.
GO 
CREATE OR ALTER VIEW toateStatiile 
AS 
	SELECT Rute.nume FROM Rute INNER JOIN RutaStatie RS ON Rute.id_ruta = RS.id_ruta 
	GROUP BY Rute.id_ruta, Rute.nume HAVING COUNT(*) = (SELECT COUNT(*) FROM Statii);
GO
SELECT * FROM toateStatiile;

--- 3) Creați o funcție care afișează toate stațiile care au mai mult de un tren la un anumit moment din zi. 
GO
CREATE FUNCTION Functie1()
RETURNS TABLE AS
RETURN SELECT DISTINCT Statii.nume FROM Statii INNER JOIN RutaStatie ON Statii.id_statie = RutaStatie.id_statie 
											   INNER JOIN RutaStatie RS2 ON RutaStatie.id_statie = RS2.id_statie 
											   AND RutaStatie.id_ruta <> RS2.id_ruta WHERE RutaStatie.ora_sosirii BETWEEN RS2.ora_sosirii AND RS2.ora_plecarii

GO
SELECT * FROM dbo.Functie1()