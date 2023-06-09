select * from Tari
select * from Melodii
select * from Artisti


select * from Melodii order by titlu

SET SHOWPLAN_ALL ON;
GO
SELECT titlu, an_lansare FROM Melodii WHERE an_lansare > 2005 ORDER BY titlu;
GO
SET SHOWPLAN_ALL OFF;
GO
 
CREATE INDEX ind_an_titlu ON Melodii (an_lansare ASC , titlu ASC)