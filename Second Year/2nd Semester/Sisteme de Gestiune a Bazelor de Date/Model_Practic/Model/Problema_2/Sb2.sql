
	-- 1-WRONG --
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED
    BEGIN TRANSACTION
    SELECT * FROM Tari;
    WAITFOR DELAY '00:00:06'
    SELECT * FROM Tari;
    COMMIT TRANSACTION


    -- 1-SOLUTION -- schimbam nivelul de izolare de la la READ COMMITED la REPEATABLE READ, pentru a afisa de fiecare data aceleasi date in interiorul uni tranzactii (se va face lock pe datele din tabelul respevctiv)
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
    BEGIN TRANSACTION
    SELECT * FROM Tari;
    WAITFOR DELAY '00:00:06'
    SELECT * FROM Tari;
    COMMIT TRANSACTION


	-- 2 -- 
    BEGIN TRANSACTION
	WAITFOR DELAY '00:00:03';
    UPDATE Tari SET descriere='descr' WHERE nume_tara='tara1';
    COMMIT TRANSACTION
