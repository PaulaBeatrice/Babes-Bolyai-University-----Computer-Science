USE CabinetOftalmologic
GO

GO
CREATE OR ALTER FUNCTION validareOchelari
(@idRama INT, @idLentile INT, @dioptr_st FLOAT, @dioptr_dr FLOAT, @pret INT)
RETURNS VARCHAR(100)
AS 
BEGIN
	DECLARE @mesaj VARCHAR(100)
	SET @mesaj = ''

	IF(NOT(EXISTS(SELECT cod FROM Rame WHERE cod = @idRama)))
		SET @mesaj += 'Nu exista rame cu acest id!'

	IF(NOT(EXISTS(SELECT id FROM Lentile WHERE id = @idLentile)))
		SET @mesaj += 'Nu exista lentile cu acest id!'

	IF(@dioptr_st < -10.0 OR @dioptr_st > 10.0)
		SET @mesaj += 'Dimensiunea dioptriei stangi este incorecta!'

	IF(@dioptr_dr < -10.0 OR @dioptr_dr > 10.0)
		SET @mesaj += 'Dimensiunea dioptriei drepte este incorecta!'

	IF(@pret < 0)
		SET @mesaj += 'Pretul este incorect!'

	RETURN @mesaj
END

GO
CREATE OR ALTER FUNCTION validareComanda
(@idPacient INT, @data_livrarii DATETIME)
RETURNS VARCHAR(100)
AS 
BEGIN
	DECLARE @mesaj VARCHAR(100)
	SET @mesaj = ''

	IF(NOT(EXISTS(SELECT id FROM Pacienti WHERE id = @idPacient)))
		SET @mesaj += 'Nu exista pacient cu acest id!'

	IF(@data_livrarii < GETDATE())
		SET @mesaj += 'Data invalida!'

	RETURN @mesaj
END


-----SISTEMUL DE LOGARE-----
CREATE TABLE IstoricLogare
(
	id  INT PRIMARY KEY IDENTITY(1,1),
	actiune VARCHAR(15),
	tabel VARCHAR(15),
	data_executiei DATETIME
)


----- PROCEDURA CE INSEREAZA DATE IN TABELELE Ochelari SI Comunezi SI FACE ROLLBOCK PE INTREAGA PROCEDURA-----
GO
CREATE OR ALTER PROCEDURE AddOchelariComenzi @idRama INT, @idLentile INT, @dioptr_st FLOAT, @dioptr_dr FLOAT, @pret INT, @idPacient INT, @data_livrarii DATETIME
AS
BEGIN
	BEGIN TRAN
	BEGIN TRY

		DECLARE @mesaj VARCHAR(100) = '';

		DECLARE @mesaj_ochelari VARCHAR(100) = dbo.validareOchelari(@idRama,@idLentile,@dioptr_st,@dioptr_dr,@pret);
		IF (@mesaj_ochelari <> '')
			SET @mesaj += @mesaj_ochelari + CHAR(13) + CHAR(10);

		DECLARE @mesaj_comanda VARCHAR(100) = dbo.validareComanda(@idPacient,@data_livrarii);
		IF (@mesaj_comanda <> '')
			SET @mesaj += @mesaj_comanda + CHAR(13) + CHAR(10);

		IF (@mesaj <> '')
		BEGIN
			RAISERROR(@mesaj, 14, 1);
		END


		DECLARE @idOchelari INT
		DECLARE @idComanda INT

		INSERT INTO Ochelari(rama,lentile,dioptrie_ochi_stang,dioptrie_ochi_drept,pret) VALUES(@idRama,@idLentile,@dioptr_st,@dioptr_dr,@pret)
		SET @idOchelari = SCOPE_IDENTITY()
		INSERT INTO IstoricLogare(actiune, tabel, data_executiei) VALUES ('Insert', 'Ochelari', CURRENT_TIMESTAMP)
		
		INSERT INTO Comenzi(pacient,data_livrarii) VALUES(@idPacient,@data_livrarii)
		SET @idComanda = SCOPE_IDENTITY()
		INSERT INTO IstoricLogare(actiune, tabel, data_executiei) VALUES ('Insert', 'Comenzi', CURRENT_TIMESTAMP)

		INSERT INTO OchelariComenzi(ochelari,comanda) VALUES (@idOchelari,@idComanda)
		INSERT INTO IstoricLogare(actiune, tabel, data_executiei) VALUES ('Insert', 'OchelariComenzi', CURRENT_TIMESTAMP)

		COMMIT TRAN
		SELECT 'Transaction committed'
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
		SELECT 'Transaction rollbacked'
	END CATCH
END

SELECT * FROM Ochelari
SELECT * FROM Comenzi
SELECT * FROM OchelariComenzi
SELECT * FROM Rame
SELECT * FROM Lentile
SELECT * FROM Pacienti
SELECT * FROM IstoricLogare

--- Scenariul de succes
EXEC AddOchelariComenzi 1, 1, 1.0, 1.0, 100, 1, '2023-05-05 12:00:00'

--- Rollback
EXEC AddOchelariComenzi 100, 1, 1.0, 1.0, 100, 1, '2023-05-05 12:00:00'
EXEC AddOchelariComenzi 1, 100, 1.0, 1.0, 100, 1, '2023-05-05 12:00:00'
EXEC AddOchelariComenzi 1, 1, 100.0, 1.0, 100, 1, '2023-05-05 12:00:00'
EXEC AddOchelariComenzi 1, 1, 1.0, 100.0, 100, 1, '2023-05-05 12:00:00'
EXEC AddOchelariComenzi 1, 1, 1.0, 1.0, -100, 1, '2023-05-05 12:00:00'
EXEC AddOchelariComenzi 1, 1, 1.0, 1.0, 100, 100, '2023-05-05 12:00:00'
EXEC AddOchelariComenzi 1, 1, 1.0, 1.0, 100, 1, '2020-05-05 12:00:00'




----- PROCEDURA CE INSEREAZA DATE IN TABELELE Ochelari SI Comunezi SI PASTREAZA CE E CORECT-----
GO
CREATE OR ALTER PROCEDURE AddOchelariComenzi2 @idRama INT, @idLentile INT, @dioptr_st FLOAT, @dioptr_dr FLOAT, @pret INT, @idPacient INT, @data_livrarii DATETIME
AS
BEGIN
	DECLARE @inserareOchelari INT
	SET @inserareOchelari = 0

	BEGIN TRAN
	BEGIN TRY
		DECLARE @mesaj_ochelari VARCHAR(100) = dbo.validareOchelari(@idRama,@idLentile,@dioptr_st,@dioptr_dr,@pret);
		IF (@mesaj_ochelari <> '')
		BEGIN
			RAISERROR(@mesaj_ochelari, 14, 1);
		END
		DECLARE @idOchelari INT

		INSERT INTO Ochelari(rama,lentile,dioptrie_ochi_stang,dioptrie_ochi_drept,pret) VALUES(@idRama,@idLentile,@dioptr_st,@dioptr_dr,@pret)
		SET @idOchelari = SCOPE_IDENTITY()
		INSERT INTO IstoricLogare(actiune, tabel, data_executiei) VALUES ('Insert', 'Ochelari', CURRENT_TIMESTAMP)
	
		COMMIT TRAN
		SELECT 'Transaction Ochelari committed'
		SET @inserareOchelari = @idOchelari
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
		SELECT 'Transaction Ochelari rollbacked'
		INSERT INTO IstoricLogare(actiune, tabel, data_executiei) VALUES ('ROLLBACK', 'Ochelari', CURRENT_TIMESTAMP)
	END CATCH



	DECLARE @inserareComanda INT
	SET @inserareComanda = 0

	BEGIN TRAN
	BEGIN TRY
		DECLARE @mesaj_comanda VARCHAR(100) = dbo.validareComanda(@idPacient,@data_livrarii);
		IF (@mesaj_comanda <> '')
		BEGIN
			RAISERROR(@mesaj_comanda, 14, 1);
		END
		DECLARE @idComanda INT
		
		INSERT INTO Comenzi(pacient,data_livrarii) VALUES(@idPacient,@data_livrarii)
		SET @idComanda = SCOPE_IDENTITY()
		INSERT INTO IstoricLogare(actiune, tabel, data_executiei) VALUES ('Insert', 'Comenzi', CURRENT_TIMESTAMP)
	
		COMMIT TRAN
		SELECT 'Transaction Comanda committed'
		SET @inserareComanda = @idComanda
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
		SELECT 'Transaction Comanda rollbacked'
		INSERT INTO IstoricLogare(actiune, tabel, data_executiei) VALUES ('ROLLBACK', 'Comanda', CURRENT_TIMESTAMP)
	END CATCH


	BEGIN TRAN
	BEGIN TRY
		IF(@inserareComanda = 0 OR @inserareOchelari = 0)
		BEGIN
			RAISERROR(@mesaj_comanda, 14, 1);
		END
		
		INSERT INTO OchelariComenzi(ochelari,comanda) VALUES (@inserareOchelari,@inserareComanda)
		INSERT INTO IstoricLogare(actiune, tabel, data_executiei) VALUES ('Insert', 'OchelariComenzi', CURRENT_TIMESTAMP)
	
		COMMIT TRAN
		SELECT 'Transaction OchelariComanda committed'
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN
		SELECT 'Transaction OchelariComanda rollbacked'
		INSERT INTO IstoricLogare(actiune, tabel, data_executiei) VALUES ('ROLLBACK', 'OchelariComanda', CURRENT_TIMESTAMP)
	END CATCH

END



SELECT * FROM Ochelari
SELECT * FROM Comenzi
SELECT * FROM OchelariComenzi
SELECT * FROM Rame
SELECT * FROM Lentile
SELECT * FROM Pacienti
SELECT * FROM IstoricLogare

--- Scenariul de succes
EXEC AddOchelariComenzi2 1, 1, 1.0, 1.0, 100, 1, '2023-05-05 12:00:00'

--- Rollback
--- Se adauga Comanda, dar Ochelari si Ochelari Comenzi nu se adauga
EXEC AddOchelariComenzi2 100, 1, 1.0, 1.0, 100, 1, '2023-05-05 12:00:00'
EXEC AddOchelariComenzi2 1, 100, 1.0, 1.0, 100, 1, '2023-05-05 12:00:00'
EXEC AddOchelariComenzi2 1, 1, 100.0, 1.0, 100, 1, '2023-05-05 12:00:00'
EXEC AddOchelariComenzi2 1, 1, 1.0, 100.0, 100, 1, '2023-05-05 12:00:00'
EXEC AddOchelariComenzi2 1, 1, 1.0, 1.0, -100, 1, '2023-05-05 12:00:00'

--- Se adauga Ochelari, dar Comanda si OchelariComenzi nu se adauga
EXEC AddOchelariComenzi2 1, 1, 1.0, 1.0, 100, 100, '2023-05-05 12:00:00'
EXEC AddOchelariComenzi2 1, 1, 1.0, 1.0, 100, 1, '2020-05-05 12:00:00'