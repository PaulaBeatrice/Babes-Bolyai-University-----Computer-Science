USE master;
GO
DROP DATABASE IF EXISTS Caleidoscop;
GO

CREATE DATABASE Caleidoscop;
GO
USE Caleidoscop;
GO

CREATE TABLE Episoade(
	id INT PRIMARY KEY IDENTITY,
	durata INT, -- durata in minute
	nume VARCHAR(50),
	ordine INT
);

CREATE TABLE Utilizatori(
	id INT PRIMARY KEY IDENTITY,
	nume VARCHAR(50),
	email VARCHAR(50)
);

CREATE TABLE Vizionari(
	id_utilizator INT FOREIGN KEY REFERENCES Utilizatori(id),
	id_episod INT FOREIGN KEY REFERENCES Episoade(id),
	ordine INT,
	CONSTRAINT pk_UtilizatorEpisod PRIMARY KEY (id_utilizator, id_episod)
);

CREATE TABLE Actori(
	id INT PRIMARY KEY IDENTITY,
	nume VARCHAR(100)
);

CREATE TABLE Distributii(
	id_actor INT FOREIGN KEY REFERENCES Actori(id),
	id_episod INT FOREIGN KEY REFERENCES Episoade(id),
	CONSTRAINT pk_ActoriEpisoade PRIMARY KEY (id_actor, id_episod)
);

CREATE TABLE Replici(
	id_actor INT FOREIGN KEY REFERENCES Actori(id),
	id_episod INT FOREIGN KEY REFERENCES Episoade(id),
	text_replica VARCHAR(100),
	moment INT, -- in minute de la inceputul episodului
	CONSTRAINT pk_ActoriEpisoadeReplici PRIMARY KEY (id_actor, id_episod, moment)
);
GO

CREATE OR ALTER PROCEDURE usp_DaPlay
	@id_utilizator INT
AS
BEGIN
	DECLARE @ordine INT;
	DECLARE @nrEpisoadeVizionate INT;
	DECLARE @id_episod INT;

	SELECT TOP 1 @nrEpisoadeVizionate=COUNT(*) FROM Vizionari V WHERE V.id_utilizator=@id_utilizator;
	IF (@nrEpisoadeVizionate = 9)
	BEGIN
		RETURN;
	END;

	IF (@nrEpisoadeVizionate = 8)
	BEGIN
		SELECT @id_episod=E.id FROM Episoade E WHERE E.ordine=9;
		INSERT INTO Vizionari(id_episod, id_utilizator, ordine) VALUES (@id_episod, @id_utilizator, 9);
		RETURN;
	END;

	SET @ordine=FLOOR(RAND() * 8 + 1);
	WHILE (@ordine IN (SELECT E.ordine FROM Vizionari V INNER JOIN Episoade E ON V.id_episod=E.id WHERE V.id_utilizator=@id_utilizator))
	BEGIN
		SET @ordine=FLOOR(RAND() * 8 + 1);
	END;
	SELECT @id_episod=E.id FROM Episoade E WHERE E.ordine=@ordine;
	
	SET @nrEpisoadeVizionate = @nrEpisoadeVizionate + 1;
	INSERT INTO Vizionari(id_episod, id_utilizator, ordine) VALUES (@id_episod, @id_utilizator, @nrEpisoadeVizionate);
END;
GO

CREATE OR ALTER VIEW vw_ScriptReplici
AS
	SELECT TOP 100000 (A.nume + ': ' + R.text_replica) AS Replici
	FROM Replici R INNER JOIN Actori A ON R.id_actor=A.id
				   INNER JOIN Episoade E ON R.id_episod=E.id
	ORDER BY E.ordine, R.moment
GO

-- Testing zone

EXEC usp_DaPlay 1;
EXEC usp_DaPlay 1;
EXEC usp_DaPlay 1;
EXEC usp_DaPlay 1;
EXEC usp_DaPlay 1;
EXEC usp_DaPlay 1;
EXEC usp_DaPlay 1;
EXEC usp_DaPlay 1;
EXEC usp_DaPlay 1;
SELECT * FROM Vizionari;

SELECT * FROM vw_ScriptReplici;

GO