	--1--
	BEGIN TRANSACTION 
	UPDATE Briose SET pret=10 WHERE cod_briosa=2;
	WAITFOR DELAY '00:00:05'
	ROLLBACK TRANSACTION

	-- 2-WRONG --
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
	BEGIN TRANSACTION
	SELECT * FROM Briose;
	WAITFOR DELAY '00:00:10'
	SELECT * FROM Briose;
	COMMIT TRANSACTION
	
	-- 2-SOLUTION -- schimbam nivelul de izolare de la READ UNCOMMITTED la READ COMMITTED, pentru a se citi datele dupa ce alte tranzactii au dat commit
	SET TRANSACTION ISOLATION LEVEL READ COMMITTED
	BEGIN TRANSACTION
	SELECT * FROM Briose;
	WAITFOR DELAY '00:00:10'
	SELECT * FROM Briose;
	COMMIT TRANSACTION