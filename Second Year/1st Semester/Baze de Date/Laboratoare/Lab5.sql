

------------------------------------------- OchelariComenzi --------------------------------------------------------------

-- Validarea datelor din tabelul OchelariComenzi (many-to-many)
CREATE FUNCTION validareOchelariComenzi(@ochelari INT, @comanda INT, @descriere VARCHAR(20))
RETURNS VARCHAR(200)
AS 
BEGIN
	DECLARE @errorMessage VARCHAR(200)
	SET @errorMessage = ''
	IF (NOT(EXISTS(SELECT id FROM Ochelari WHERE id = @ochelari)))
		SET @errorMessage = @errorMessage + 'Ochelarii cu id-ul ' + CONVERT(VARCHAR, @ochelari) + ' nu exista. '

	IF (NOT(EXISTS(SELECT id FROM Comenzi WHERE id = @comanda)))
		SET @errorMessage = @errorMessage + 'Comanda cu id-ul ' + CONVERT(VARCHAR, @comanda) + ' nu exista. '

	IF (@descriere = '')
		SET @errorMessage = @errorMessage + 'Descrierea nu poate fi nula.'

	IF (EXISTS(SELECT ochelari, comanda FROM OchelariComenzi WHERE ochelari = @ochelari AND comanda = @comanda))
		SET @errorMessage = @errorMessage + 'Valorile deja exista in tabel.'
	RETURN @errorMessage
END
--PRINT dbo.validareOchelariComenzi(1,2);


GO
--Adaugare nota in tabelul OchelariComenzi
CREATE  OR ALTER PROCEDURE adaugaOchelariComenzi(@ochelari INT, @comanda INT, @descriere VARCHAR(20))
AS
BEGIN

	DECLARE @errorMessage VARCHAR(200)
	SET @errorMessage = dbo.validareOchelariComenzi(@ochelari,@comanda, @descriere)

	IF (@errorMessage != '')
	BEGIN
		PRINT 'ERROR: ' + @errorMessage
		RETURN
	END

	-- CREATE
	INSERT INTO OchelariComenzi(ochelari,comanda,descriere) VALUES (@ochelari,@comanda,@descriere)
END

GO
--Read OchelariComenzi FROM OchelariComenzi (get_all)
CREATE  OR ALTER PROCEDURE readOchelariComenzi(@ochelari INT, @comanda INT)
AS
BEGIN
	-- READ
	SELECT * FROM OchelariComenzi WHERE ochelari = @ochelari AND comanda = @comanda
END

GO

--Update descriere FROM OchelariComenzi
CREATE  OR ALTER PROCEDURE updateNota(@ochelari INT, @comanda INT, @descriere VARCHAR(20))
AS
BEGIN

	DECLARE @errorMessage VARCHAR(200)
	SET @errorMessage = dbo.validareOchelariComenzi(@ochelari,@comanda,@descriere)

	IF (@errorMessage = 'Valorile deja exista in tabel.')
	BEGIN
		-- UPDATE
		UPDATE OchelariComenzi SET descriere = @descriere WHERE ochelari = @ochelari and comanda = @comanda
	END
	ELSE 
	BEGIN
		PRINT 'Nu se poate modifica deoarece nu exista.'
	END

END

GO

--Delete OchelariComenzi FROM OchelariComenzi
CREATE  OR ALTER PROCEDURE deleteOchelariComenzi(@ochelari INT, @comanda INT, @descriere VARCHAR(20))
AS
BEGIN

	DECLARE @errorMessage VARCHAR(200)
	SET @errorMessage = dbo.validareOchelariComenzi(@ochelari, @comanda, @descriere)

	IF (@errorMessage = 'Valorile deja exista in tabel.')
	BEGIN
		-- DELETE
		DELETE FROM OchelariComenzi WHERE ochelari = @ochelari AND comanda = @comanda
	END
	ELSE
	BEGIN
		PRINT 'Nu s-a gasit in tabel.'
	END
END

------------------------------------------- Ochelari --------------------------------------------------------------

-- Validarea datelor din tabelul Ochelari
CREATE FUNCTION validareOchelari(@rama INT, @lentile INT, @dioptrie_st INT, @dioptrie_dr INT, @pret INT)
RETURNS VARCHAR(200)
AS
BEGIN
	DECLARE @errorMessage VARCHAR(200)
	SET @errorMessage = ''
	
	IF (NOT(EXISTS(SELECT cod FROM Rame WHERE cod = @rama)))
		SET @errorMessage = @errorMessage + 'Rama cu codul ' + CONVERT(VARCHAR, @rama) + ' nu exista. '

	IF (NOT(EXISTS(SELECT cod FROM Lentile WHERE cod = @lentile)))
		SET @errorMessage = @errorMessage + 'Lentilele cu codul ' + CONVERT(VARCHAR, @lentile) + ' nu exista. '

	if (@pret < 0)
		SET @errorMessage = @errorMessage + 'Pretul nu poate fi un numar negativ. '

		RETURN @errorMessage
END


GO
--Adaugare in tabelul Ochelari
CREATE  OR ALTER PROCEDURE adaugaOchelari(@rama INT, @lentile INT, @dioptrie_st INT, @dioptrie_dr INT, @pret INT)
AS
BEGIN

	DECLARE @errorMessage VARCHAR(200)
	SET @errorMessage = dbo.validareOchelari(@rama,@lentile,@dioptrie_st,@dioptrie_dr,@pret)

	IF (@errorMessage != '')
	BEGIN
		PRINT 'ERROR: ' + @errorMessage
		RETURN
	END

	-- CREATE
	INSERT INTO Ochelari(rama,lentile,dioptrie_ochi_stang,dioptrie_ochi_drept,pret) VALUES (@rama,@lentile,@dioptrie_st,@dioptrie_dr,@pret)
END

GO
--Read FROM Ochelari
CREATE  OR ALTER PROCEDURE readOchelari
(@rama INT, @lentile INT)
AS
BEGIN
	-- READ
	SELECT * FROM Ochelari WHERE rama = @rama AND lentile = @lentile
END

GO
--Update Ochelari
CREATE  OR ALTER PROCEDURE updateOchelari(@rama INT, @lentile INT, @dioptrie_st INT, @dioptrie_dr INT, @pret INT)
AS
BEGIN

	DECLARE @errorMessage VARCHAR(200)
	SET @errorMessage = dbo.validareOchelari(@rama,@lentile,@dioptrie_st,@dioptrie_dr,@pret)

	IF (@errorMessage != '')
	BEGIN
		PRINT 'ERROR: ' + @errorMessage;
	END
	ELSE 
	BEGIN
		IF (EXISTS(SELECT rama, lentile FROM Ochelari WHERE rama = @rama AND lentile = @lentile))
			UPDATE Ochelari SET dioptrie_ochi_stang = @dioptrie_st, dioptrie_ochi_drept = @dioptrie_dr, pret = @pret WHERE rama = @rama AND lentile = @lentile
		ELSE
			PRINT 'Ochelarii nu exista'
	END

END

GO

--Delete Ochelari
CREATE  OR ALTER PROCEDURE deleteOchelari(@rama INT, @lentile INT, @dioptrie_st INT, @dioptrie_dr INT, @pret INT)
AS
BEGIN
	DECLARE @errorMessage VARCHAR(200)
	SET @errorMessage = dbo.validareOchelari(@rama,@lentile,@dioptrie_st,@dioptrie_dr,@pret)

	IF (@errorMessage != '')
	BEGIN
		PRINT 'ERROR: ' + @errorMessage;
	END
	ELSE 
	BEGIN
		IF (EXISTS(SELECT rama, lentile FROM Ochelari WHERE rama = @rama AND lentile = @lentile))
			DELETE FROM Ochelari WHERE rama = @rama AND lentile = @lentile
		ELSE 
			PRINT 'Ochelarii nu exista.'
	END
END



------------------------------------------- Comenzi --------------------------------------------------------------
-- Validarea datelor din tabelul Comenzi
CREATE FUNCTION validareComenzi(@pacient INT, @data DATE)
RETURNS VARCHAR(200)
AS
BEGIN
	DECLARE @errorMessage VARCHAR(200)
	SET @errorMessage = ''

	IF (NOT(EXISTS(SELECT id FROM Pacienti WHERE id = @pacient)))
		SET @errorMessage = @errorMessage + 'Pacientul cu id-ul ' + CONVERT(VARCHAR, @pacient) + ' nu exista. '
	RETURN @errorMessage
END


GO
--Adaugare in tabelul Comenzi
CREATE  OR ALTER PROCEDURE adaugaComenzi(@pacient INT, @data DATE)
AS
BEGIN

	DECLARE @errorMessage VARCHAR(200)
	SET @errorMessage = dbo.validareComenzi(@pacient,@data)

	IF (@errorMessage != '')
	BEGIN
		PRINT 'ERROR: ' + @errorMessage
		RETURN
	END

	-- CREATE
	INSERT INTO Comenzi(pacient,data_livrarii) VALUES (@pacient,@data)
END

GO
--Read FROM Comenzi
CREATE  OR ALTER PROCEDURE readComenzi(@pacient INT, @data DATE)
AS
BEGIN
	-- READ
	SELECT * FROM Comenzi WHERE pacient = @pacient
END

GO

--Update Comenzi
CREATE  OR ALTER PROCEDURE updateComenzi(@pacient INT, @data DATE)
AS
BEGIN

	DECLARE @errorMessage VARCHAR(200)
	SET @errorMessage = dbo.validareComenzi(@pacient, @data)

	IF (@errorMessage != '')
	BEGIN
		PRINT 'ERROR: ' + @errorMessage;
	END
	ELSE 
	BEGIN
		IF (EXISTS(SELECT pacient FROM Comenzi WHERE pacient = @pacient))
			UPDATE Comenzi SET data_livrarii = @data WHERE pacient = @pacient 
		ELSE
			PRINT 'Comanda nu exista.'
	END

END

GO

--Delete Comenzi
CREATE  OR ALTER PROCEDURE deleteComenzi(@pacient INT, @data DATE)
AS
BEGIN
	DECLARE @errorMessage VARCHAR(200)
	SET @errorMessage = dbo.validareComenzi(@pacient, @data)

	IF (@errorMessage != '')
	BEGIN
		PRINT 'ERROR: ' + @errorMessage;
	END
	ELSE 
	BEGIN
		IF (EXISTS(SELECT pacient FROM Comenzi WHERE pacient = @pacient))
			DELETE FROM Comenzi WHERE pacient = @pacient
		ELSE 
			PRINT 'Comanda nu exista.'
	END
END


------------------------------------------- VIEW URI SI INDECSI --------------------------------------------------------------

CREATE OR ALTER VIEW viewOchelari
AS
	SELECT dioptrie_ochi_drept,dioptrie_ochi_stang FROM Ochelari
GO

CREATE OR ALTER VIEW viewComenzi
AS
	SELECT data_livrarii, pacient FROM Comenzi
GO

SELECT * FROM viewOchelari
SELECT * FROM viewComenzi

CREATE INDEX N_idx_Ochelari_info ON Ochelari(dioptrie_ochi_drept ASC, dioptrie_ochi_stang ASC);
CREATE INDEX N_idx_Comenzi ON Comenzi(data_livrarii ASC, pacient ASC);

SELECT dioptrie_ochi_drept,dioptrie_ochi_stang FROM Ochelari
SELECT data_livrarii,pacient FROM Comenzi

SELECT * FROM viewComenzi
SELECT * FROM viewOchelari

SELECT * FROM Comenzi