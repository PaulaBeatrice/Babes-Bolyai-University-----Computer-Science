USE CabinetOftalmologic

CREATE TABLE IstoricTranzactii(
	ID INT PRIMARY KEY IDENTITY(1,1),
	actiune VARCHAR(50),
	data_executiei DATETIME,
	mesaj VARCHAR(50)
)

SELECT * FROM IstoricTranzactii
SELECT * FROM Roluri

--------------------------------------------------------------- Dirty Reads ---------------------------------------------------------
	--1--
	BEGIN TRANSACTION 
	UPDATE Roluri SET salar=4400 WHERE rol='asistent';
	WAITFOR DELAY '00:00:05'
	ROLLBACK TRANSACTION
	INSERT INTO IstoricTranzactii(actiune, data_executiei,mesaj) VALUES('Dr Rollback', CURRENT_TIMESTAMP, 'Rollback successfully')

	-- 2-WRONG --
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
	BEGIN TRANSACTION
	SELECT * FROM Roluri;
	INSERT INTO IstoricTranzactii(actiune, data_executiei, mesaj) VALUES('SELECT Roluri', CURRENT_TIMESTAMP, 'SELECT ALL from Roluri after update')
	WAITFOR DELAY '00:00:10'
	SELECT * FROM Roluri;
	INSERT INTO IstoricTranzactii(actiune, data_executiei, mesaj) VALUES('SELECT Roluri', CURRENT_TIMESTAMP, 'SELECT ALL from Roluri after Rollback')
	COMMIT TRANSACTION
	
	-- 2-SOLUTION -- schimbam nivelul de izolare de la READ UNCOMMITTED la READ COMMITTED, pentru a se citi datele dupa ce alte tranzactii au dat commit
	SET TRANSACTION ISOLATION LEVEL READ COMMITTED
	BEGIN TRANSACTION
	SELECT * FROM Roluri;
	INSERT INTO IstoricTranzactii(actiune, data_executiei, mesaj) VALUES('SELECT Roluri', CURRENT_TIMESTAMP, 'SELECT ALL from Roluri after update')
	WAITFOR DELAY '00:00:10'
	SELECT * FROM Roluri;
	INSERT INTO IstoricTranzactii(actiune, data_executiei, mesaj) VALUES('SELECT Roluri', CURRENT_TIMESTAMP, 'SELECT ALL from Roluri after Rollback')
	COMMIT TRANSACTION



-------------------------------------------------------- Non-Repeatable Reads ------------------------------------------------------------
	-- 1-WRONG --
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED
    BEGIN TRANSACTION
    SELECT * FROM Roluri;
    WAITFOR DELAY '00:00:06'
    SELECT * FROM Roluri;
    COMMIT TRANSACTION


    -- 1-SOLUTION -- schimbam nivelul de izolare de la la READ COMMITED la REPEATABLE READ, pentru a afisa de fiecare data aceleasi date in interiorul uni tranzactii (se va face lock pe datele din tabelul respevctiv)
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
    BEGIN TRANSACTION
    SELECT * FROM Roluri;
    WAITFOR DELAY '00:00:06'
    SELECT * FROM Roluri;
    COMMIT TRANSACTION


	-- 2 -- 
    BEGIN TRANSACTION
	WAITFOR DELAY '00:00:03';
    UPDATE Roluri SET salar=4400 WHERE rol='asistent';
	INSERT INTO IstoricTranzactii(actiune, data_executiei,mesaj) VALUES('asistent updated', CURRENT_TIMESTAMP, 'Delay for asistent update')
    COMMIT TRANSACTION
	INSERT INTO IstoricTranzactii(actiune, data_executiei,mesaj) VALUES('asistent updated', CURRENT_TIMESTAMP, 'Update rollback ended')



-------------------------------------------------------- Phantom Reads -------------------------------------------------------------------

-- 1-WRONG --
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
BEGIN TRANSACTION;
SELECT * FROM Angajati WHERE id BETWEEN 1 AND 100;
WAITFOR DELAY '00:00:07';
SELECT * FROM Angajati WHERE id BETWEEN 1 AND 100;
COMMIT TRAN;


-- 1-SOLUTION -- schimbam nivelul de izolare la SERIALIZABLE pentru a arata de fiecare data aceleasi date
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
BEGIN TRANSACTION
SELECT * FROM Angajati WHERE id BETWEEN 1 AND 100;
WAITFOR DELAY '00:00:07'
SELECT * FROM Angajati WHERE id BETWEEN 1 AND 100;
COMMIT TRANSACTION
INSERT INTO IstoricTranzactii(actiune, data_executiei, mesaj) VALUES('Phantom reads', CURRENT_TIMESTAMP, 'PR succeded')


-- 2 --
BEGIN TRANSACTION
INSERT INTO IstoricTranzactii(actiune, data_executiei, mesaj) VALUES('PR Insert', CURRENT_TIMESTAMP, 'Delay for insert started')
WAITFOR DELAY '00:00:05'
INSERT INTO Angajati(nume, prenume, rol) VALUES ('A', 'A', 1);
INSERT INTO IstoricTranzactii(actiune, data_executiei, mesaj) VALUES('PR Insert', CURRENT_TIMESTAMP, 'Inserted')
COMMIT TRANSACTION
INSERT INTO IstoricTranzactii(actiune, data_executiei, mesaj) VALUES('PR Insert', CURRENT_TIMESTAMP, 'Insert rollback ended')



------------------------------------------------------------- Deadlock -------------------------------------------------------------------------

-- 1 --
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
BEGIN TRAN;
UPDATE Roluri SET salar=3500 WHERE rol='asistent';
WAITFOR DELAY '00:00:05';
UPDATE Angajati SET nume='B' WHERE prenume='A';
COMMIT TRAN;
INSERT INTO IstoricTranzactii(actiune,data_executiei,mesaj) VALUES ('Deadlock', CURRENT_TIMESTAMP, 'First transaction - victim')


-- 2-WRONG --
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
BEGIN TRAN;
UPDATE Angajati SET nume='C' WHERE prenume='A';
WAITFOR DELAY '00:00:05';
UPDATE Roluri SET salar=3000 WHERE rol='asistent';
COMMIT TRAN;
INSERT INTO IstoricTranzactii(actiune,data_executiei,mesaj) VALUES('Deadlock', CURRENT_TIMESTAMP, 'Second transaction - succeeded')


-- 2-SOLUTION -- prin SET DEADLOCK_PRIORITY HIGH, tranzactia va avea prioritate mai mare la deadlock si nu va fi aleasa ca si victima, pe cand cea de-a doua tranzactie va esua.
SET DEADLOCK_PRIORITY HIGH;
BEGIN TRANSACTION;
UPDATE Angajati SET nume='C' WHERE prenume='A';
WAITFOR DELAY '00:00:05';
UPDATE Roluri SET salar=3000 WHERE rol='asistent';
COMMIT TRAN;
INSERT INTO IstoricTranzactii(actiune,data_executiei,mesaj) VALUES('Deadlock', CURRENT_TIMESTAMP, 'Second transaction - succeeded')

SELECT * FROM Roluri
SELECT * FROM Angajati



----------------------------------------------------------- C# ----------------------------------------------------------------------------
GO
CREATE OR ALTER PROCEDURE run_thread1
AS
    BEGIN
        BEGIN TRANSACTION
        UPDATE Roluri SET salar=2000 WHERE rol='paznic';
        WAITFOR DELAY '00:00:10'
        UPDATE Angajati SET nume = nume + ' THR1' WHERE nume='A';
        COMMIT TRANSACTION
    END

GO
CREATE OR ALTER PROCEDURE run_thread2
AS
    BEGIN
		SET DEADLOCK_PRIORITY HIGH
		-- SET DEADLOCK_PRIORITY LOW
        BEGIN TRANSACTION
        UPDATE Roluri SET salar=3000 WHERE rol='paznic';
        WAITFOR DELAY '00:00:10'
        UPDATE Angajati SET nume = nume + ' THR2' WHERE nume='A';
        COMMIT TRANSACTION
    END