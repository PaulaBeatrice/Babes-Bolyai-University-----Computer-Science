-- 1) Modifica tipul unei coloane
GO
CREATE OR ALTER PROCEDURE do1 
AS
BEGIN
	ALTER TABLE Roluri
	ALTER COLUMN salar FLOAT
	UPDATE VersiuniDB SET versiuneCurenta = 1;
END
GO

GO
CREATE OR ALTER PROCEDURE undo1
AS
BEGIN
	ALTER TABLE Roluri
	ALTER COLUMN salar INT
	UPDATE VersiuniDB SET versiuneCurenta = 0;
END
GO

-- 2) Adauga o costrângere de “valoare implicită” pentru un câmp
GO
CREATE OR ALTER PROCEDURE do2 
AS 
BEGIN
	ALTER TABLE Ochelari
	ADD CONSTRAINT pret_nenul
	DEFAULT 100 FOR pret
	UPDATE VersiuniDB SET versiuneCurenta = 2;
END
GO

CREATE OR ALTER PROCEDURE undo2
AS
BEGIN
	ALTER TABLE Ochelari
	DROP CONSTRAINT pret_nenul
	UPDATE VersiuniDB SET versiuneCurenta = 1;
END
GO

-- 3) Creeaza/şterge o tabelă
CREATE OR ALTER PROCEDURE do3
AS
BEGIN
	CREATE TABLE Echipamente(
			id INT PRIMARY KEY,
			denumire VARCHAR(100),
			cantitate INT
		);
	UPDATE VersiuniDB SET versiuneCurenta = 3;
END
GO

CREATE OR ALTER PROCEDURE undo3
AS 
BEGIN
	DROP TABLE IF EXISTS Echipamente
	UPDATE VersiuniDB SET versiuneCurenta = 2;
END
GO

-- 4) Adauga un camp nou
GO
CREATE OR ALTER PROCEDURE do4
AS
BEGIN
	ALTER TABLE Angajati ADD Sex VARCHAR(20);
	UPDATE VersiuniDB SET versiuneCurenta = 4;
END
GO

GO
CREATE OR ALTER PROCEDURE undo4
AS
BEGIN
	ALTER TABLE Angajati DROP COLUMN Sex;
	UPDATE VersiuniDB SET versiuneCurenta = 3;
END

-- 5) Creeaza/şterge o constrângere de cheie străină
GO
CREATE OR ALTER PROCEDURE do5
AS
BEGIN
	ALTER TABLE Angajati
	ADD pacient_id INT;

	ALTER TABLE Angajati
	ADD CONSTRAINT Pacient_Preferat_FK FOREIGN KEY (pacient_id) REFERENCES Pacienti(id);
	UPDATE VersiuniDB SET versiuneCurenta = 5;
END 
GO

GO
CREATE OR ALTER PROCEDURE undo5
AS 
BEGIN
	ALTER TABLE Angajati
	DROP CONSTRAINT Pacient_Preferat_FK;

	ALTER TABLE Angajati
	DROP COLUMN pacient_id;
	UPDATE VersiuniDB
	SET versiuneCurenta = 4;
END
GO


-- Creaţi o nouă tabelă care să memoreze versiunea structurii bazei de date (presupunând că acestă versiune este pur şi simplu un număr întreg)
USE CabinetOftalmologic

DROP TABLE IF EXISTS VersiuniDB
CREATE TABLE VersiuniDB
	(
		versiuneCurenta INT
	);

INSERT INTO VersiuniDB VALUES(0);
 SELECT * FROM VersiuniDB;

GO
CREATE OR ALTER PROCEDURE run @versiune INT
AS
BEGIN
	IF @versiune < 0 OR @versiune > 5
	BEGIN 
		PRINT 'Versiune invalida!'
		RETURN
	END

	DECLARE @versiune_curr AS INT
	SET @versiune_curr  = (SELECT versiuneCurenta FROM VersiuniDB)
	IF @versiune = @versiune_curr
	BEGIN
		PRINT 'Ne aflam deja in versiunea data'
		RETURN
	END
	DECLARE @procedura VARCHAR(20)
	DECLARE @undo_procedura VARCHAR(20)

	WHILE(@versiune_curr < @versiune )
		BEGIN
			SET @versiune_curr = @versiune_curr + 1
			SET @procedura = 'do' + CAST(@versiune_curr AS VARCHAR(20))
			PRINT 'Se executa ' + @procedura;
			EXEC @procedura
		END
	WHILE(@versiune_curr > @versiune)
		BEGIN
			SET @undo_procedura = 'undo' + CAST(@versiune_curr AS VARCHAR(20))
			EXEC @undo_procedura
			PRINT 'Se executa ' + @undo_procedura;
			SET @versiune_curr = @versiune_curr - 1
		END
END
GO

EXEC run 0
EXEC run 1
EXEC run 2
EXEC run 3
EXEC run 4
EXEC run 5

