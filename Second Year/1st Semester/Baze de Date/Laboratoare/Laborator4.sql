---- if tables exist drop any foreign constraints ----
if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunTables_Tables]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestRunTables] DROP CONSTRAINT FK_TestRunTables_Tables
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestTables_Tables]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestTables] DROP CONSTRAINT FK_TestTables_Tables
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunTables_TestRuns]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestRunTables] DROP CONSTRAINT FK_TestRunTables_TestRuns
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunViews_TestRuns]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestRunViews] DROP CONSTRAINT FK_TestRunViews_TestRuns
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestTables_Tests]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestTables] DROP CONSTRAINT FK_TestTables_Tests
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestViews_Tests]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestViews] DROP CONSTRAINT FK_TestViews_Tests
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunViews_Views]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestRunViews] DROP CONSTRAINT FK_TestRunViews_Views
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestViews_Views]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [TestViews] DROP CONSTRAINT FK_TestViews_Views
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[Tables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [Tables]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestRunTables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestRunTables]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestRunViews]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestRunViews]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestRuns]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestRuns]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestTables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestTables]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[TestViews]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [TestViews]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[Tests]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [Tests]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[Views]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [Views]
GO

---- create the tables ----
CREATE TABLE [Tables] (
	[TableID] [int] IDENTITY (1, 1) NOT NULL ,
	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestRunTables] (
	[TestRunID] [int] NOT NULL ,
	[TableID] [int] NOT NULL ,
	[StartAt] [datetime] NOT NULL ,
	[EndAt] [datetime] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestRunViews] (
	[TestRunID] [int] NOT NULL ,
	[ViewID] [int] NOT NULL ,
	[StartAt] [datetime] NOT NULL ,
	[EndAt] [datetime] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestRuns] (
	[TestRunID] [int] IDENTITY (1, 1) NOT NULL ,
	[Description] [nvarchar] (2000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[StartAt] [datetime] NULL ,
	[EndAt] [datetime] NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestTables] (
	[TestID] [int] NOT NULL ,
	[TableID] [int] NOT NULL ,
	[NoOfRows] [int] NOT NULL ,
	[Position] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [TestViews] (
	[TestID] [int] NOT NULL ,
	[ViewID] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [Tests] (
	[TestID] [int] IDENTITY (1, 1) NOT NULL ,
	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [Views] (
	[ViewID] [int] IDENTITY (1, 1) NOT NULL ,
	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 
) ON [PRIMARY]
GO

ALTER TABLE [Tables] WITH NOCHECK ADD 
	CONSTRAINT [PK_Tables] PRIMARY KEY  CLUSTERED 
	(
		[TableID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestRunTables] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestRunTables] PRIMARY KEY  CLUSTERED 
	(
		[TestRunID],
		[TableID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestRunViews] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestRunViews] PRIMARY KEY  CLUSTERED 
	(
		[TestRunID],
		[ViewID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestRuns] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestRuns] PRIMARY KEY  CLUSTERED 
	(
		[TestRunID]
	)  ON [PRIMARY] 

GO

ALTER TABLE [TestTables] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestTables] PRIMARY KEY  CLUSTERED 
	(
		[TestID],
		[TableID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestViews] WITH NOCHECK ADD 
	CONSTRAINT [PK_TestViews] PRIMARY KEY  CLUSTERED 
	(
		[TestID],
		[ViewID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [Tests] WITH NOCHECK ADD 
	CONSTRAINT [PK_Tests] PRIMARY KEY  CLUSTERED 
	(
		[TestID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [Views] WITH NOCHECK ADD 
	CONSTRAINT [PK_Views] PRIMARY KEY  CLUSTERED 
	(
		[ViewID]
	)  ON [PRIMARY] 
GO

ALTER TABLE [TestRunTables] ADD 
	CONSTRAINT [FK_TestRunTables_Tables] FOREIGN KEY 
	(
		[TableID]
	) REFERENCES [Tables] (
		[TableID]
	) ON DELETE CASCADE  ON UPDATE CASCADE ,
	CONSTRAINT [FK_TestRunTables_TestRuns] FOREIGN KEY 
	(
		[TestRunID]
	) REFERENCES [TestRuns] (
		[TestRunID]
	) ON DELETE CASCADE  ON UPDATE CASCADE 
GO

ALTER TABLE [TestRunViews] ADD 
	CONSTRAINT [FK_TestRunViews_TestRuns] FOREIGN KEY 
	(
		[TestRunID]
	) REFERENCES [TestRuns] (
		[TestRunID]
	) ON DELETE CASCADE  ON UPDATE CASCADE ,
	CONSTRAINT [FK_TestRunViews_Views] FOREIGN KEY 
	(
		[ViewID]
	) REFERENCES [Views] (
		[ViewID]
	) ON DELETE CASCADE  ON UPDATE CASCADE 
GO

ALTER TABLE [TestTables] ADD 
	CONSTRAINT [FK_TestTables_Tables] FOREIGN KEY 
	(
		[TableID]
	) REFERENCES [Tables] (
		[TableID]
	) ON DELETE CASCADE  ON UPDATE CASCADE ,
	CONSTRAINT [FK_TestTables_Tests] FOREIGN KEY 
	(
		[TestID]
	) REFERENCES [Tests] (
		[TestID]
	) ON DELETE CASCADE  ON UPDATE CASCADE 
GO

ALTER TABLE [TestViews] ADD 
	CONSTRAINT [FK_TestViews_Tests] FOREIGN KEY 
	(
		[TestID]
	) REFERENCES [Tests] (
		[TestID]
	),
	CONSTRAINT [FK_TestViews_Views] FOREIGN KEY 
	(
		[ViewID]
	) REFERENCES [Views] (
		[ViewID]
	)
GO



-------------------------------CREARE TABELE-------------------------------

DROP TABLE Calificare
DROP TABLE Laboranti
DROP TABLE Instrumente

--  o tabelă având un câmp ca şi cheie primară şi nici o cheie străină
CREATE TABLE Instrumente(
	id INT PRIMARY KEY IDENTITY(1,1),
	denumire NVARCHAR(100),
)

-- o tabelă având un câmp ca şi cheie primară şi cel puţin o cheie străină
CREATE TABLE Laboranti(
     id INT PRIMARY KEY IDENTITY(1,1),
	 nume NVARCHAR(100),
	 instrument INT FOREIGN KEY REFERENCES Instrumente(id)
)

-- o tabelă având două câmpuri ca şi cheie primară

CREATE TABLE Calificare(
	laborant INT FOREIGN KEY REFERENCES Laboranti(id),
	instrument INT FOREIGN KEY REFERENCES Instrumente(id),
	CONSTRAINT PK_LabInstrument PRIMARY KEY (laborant, instrument),
	experienta INT
)



--------------------------------------- VIEW ---------------------------------------------------

-- primul View: un view ce conţine o comandă SELECT pe o tabelă
-- Afisam instrumentele 
CREATE OR ALTER VIEW viewInstrumente AS
	SELECT * FROM Instrumente
GO

-- al doilea View: un view ce conţine o comandă SELECT aplicată pe cel puţin două tabele
-- Experienta laborantilor pentru instumentele folosite
CREATE OR ALTER VIEW viewLaboranti AS
	SELECT Laboranti.nume, Instrumente.denumire, Calificare.experienta
	FROM Laboranti, Instrumente, Calificare
	WHERE Calificare.laborant = Laboranti.id AND Laboranti.instrument = Instrumente.id
GO

-- al treilea View: un view ce conţine o comandă SELECT aplicată pe cel puţin două tabele şi având o clauză GROUP BY.
-- Afiseaza instrumentele folosite de cel putin 1 laborant
CREATE OR ALTER VIEW viewCalificare AS 
	SELECT I.denumire, COUNT(*) AS nr_laboranti
	FROM Laboranti L, Instrumente I
	WHERE L.instrument = I.id
	GROUP BY I.denumire
GO




-- Tables – conţine listele tabelelor ce ar putea face parte din testare
DELETE FROM Tables
INSERT INTO Tables VALUES ('Instrumente'), ('Laboranti'),('Calificare')

-- Views – o muţime de view-uri existente în baza de date şi care sunt utilizate în testarea performanţei unor interogări particulare
DELETE FROM Views
INSERT INTO Views VALUES ('viewInstrumente'),('viewLaboranti'),('viewCalificare')

-- Tests – conţine informaţii despre diferite configuraţii de testare
DELETE FROM Tests
INSERT INTO Tests VALUES ('selectView'),('insertInstrumente'),('deleteInstrumente'),('insertLaboranti'),('deleteLaboranti'),('insertCalificare'),('deleteCalificare')

SELECT * FROM Tests
SELECT * FROM Tables
SELECT * FROM Views

-- TestViews – este  tabela  de legătură dintre Tests şi Views şi conţine lista view-urilor implicate în fiecare test
DELETE FROM TestViews
INSERT INTO TestViews VALUES (1,1)
INSERT INTO TestViews VALUES (1,2)
INSERT INTO TestViews VALUES (1,3)

SELECT * FROM TestViews

-- TestTables – este  tabela  de  legătură dintre Tests şi Tables şi conţine lista tabelelor implicate în fiecare test
DELETE FROM TestTables
INSERT INTO TestTables VALUES (2,1,100,1),(4,2,100,2),(6,3,100,3)

SELECT * FROM TestTables
GO



---------------------- PROCEDURI DE INSERARE SI STERGERE --------------------

CREATE OR ALTER PROC insertInstrumente
AS
	DECLARE @crt INT = 1
	DECLARE @rows INT
	SELECT @rows = NoOfRows FROM TestTables WHERE TestID = 2
	PRINT @rows
	WHILE @crt <= @rows
	BEGIN
		INSERT INTO Instrumente VALUES (CAST(@crt as nvarchar(20)))
		SET @crt = @crt + 1
	END
GO
CREATE OR ALTER PROC deleteInstrumente
AS
	DELETE FROM Instrumente WHERE id > 0
	
GO


CREATE OR ALTER PROC insertLaboranti
AS
	DECLARE @crt INT = 1
	DECLARE @rows INT
	SELECT @rows = NoOfRows FROM TestTables WHERE TestID = 4
	PRINT @rows
	WHILE @crt <= @rows
	BEGIN
		INSERT INTO Laboranti VALUES (CAST(@crt as nvarchar(20)),@crt)
		SET @crt = @crt + 1
	END

GO
CREATE OR ALTER PROC deleteLaboranti
AS
	DELETE FROM Laboranti WHERE id > 0

GO


CREATE OR ALTER PROC insertCalificare
AS
	DECLARE @crt INT = 1
	DECLARE @rows INT
	SELECT @rows = NoOfRows FROM TestTables WHERE TestID = 6
	PRINT @rows
	WHILE @crt <= @rows
	BEGIN
		INSERT INTO Calificare VALUES (@crt,@crt,@crt)
		SET @crt = @crt + 1
	END
GO
CREATE OR ALTER PROC deleteCalificare
AS
	DELETE FROM Calificare
GO



CREATE OR ALTER PROC TestRunTablesProc
AS 
	DECLARE @start DATETIME;
	DECLARE @start1 DATETIME;
	DECLARE @start2 DATETIME;
	DECLARE @start3 DATETIME;
	DECLARE @start4 DATETIME;
	DECLARE @start5 DATETIME;
	DECLARE @start6 DATETIME;
	DECLARE @end1 DATETIME;
	DECLARE @end2 DATETIME;
	DECLARE @end3 DATETIME;
	DECLARE @end4 DATETIME;
	DECLARE @end5 DATETIME;
	DECLARE @end6 DATETIME;
	DECLARE @end DATETIME;
	DECLARE @description VARCHAR(100);
	SET @description = '';
	SET @start = GETDATE();
	-- 1) ştergere a datelor  din  tabelele  asociate testului (în ordinea dată de câmpul Position) 
	EXEC deleteCalificare;
	SET @description = @description +  'deleteCalificare ';
	EXEC deleteLaboranti;
	SET @description = @description +  'deleteLaboranti ';
	EXEC deleteInstrumente;
	SET @description = @description +  'deleteInstrumente ';
	
	-- 2) inserareaînregistrărilor în tabele în ordinea inversă dată de Position (numărul de înregistrări inserate este dat de câmpul NoOfRows) 
	SET @start4 = GETDATE();
	EXEC insertInstrumente;
	SET @description = @description +  'insertInstrumente ';
	SET @end4 = GETDATE();
	SET @start5 = GETDATE();
	EXEC insertLaboranti;
	SET @description = @description +  'insertLaboranti ';
	SET @end5 = GETDATE();
	SET @start6 = GETDATE();
	EXEC insertCalificare;
	SET @description = @description +  'insertCalificare ';
	SET @end6 = GETDATE();
	
	DECLARE @start1view DATETIME;
	DECLARE @start2view DATETIME;
	DECLARE @start3view DATETIME;
	DECLARE @end1view DATETIME;
	DECLARE @end2view DATETIME;
	DECLARE @end3view DATETIME;
	
	-- 3) Evaluareatimpului de execuţie a view-urilor
	SET @start1view = GETDATE();
	EXEC ('SELECT * FROM viewInstrumente');
	SET @description = @description +  'viewInstrumente ';
	SET @end1view = GETDATE();

	SET @start2view = GETDATE();
	EXEC ('SELECT * FROM viewLaboranti');
	SET @description = @description +  'viewLaboranti ';
	SET @end2view = GETDATE();

	SET @start3view = GETDATE();
	EXEC ('SELECT * FROM viewCalificare');
	SET @description = @description +  'viewCalificare ';
	SET @end3view = GETDATE();

	SET @end = GETDATE();
	
	-- TestRuns – conţine rezultatele execuţiei diferitelor teste
	INSERT INTO TestRuns VALUES (@description,@start,@end)
	DECLARE @ID INT = @@IDENTITY
	INSERT INTO TestRunTables VALUES(@ID,1,@start4,@end4);
	INSERT INTO TestRunTables VALUES(@ID,2,@start5,@end5);
	INSERT INTO TestRunTables VALUES(@ID,3,@start6,@end6);
	INSERT INTO TestRunViews VALUES (@ID, 1, @start1view, @end1view);
	INSERT INTO TestRunViews VALUES (@ID, 2, @start2view, @end2view);
	INSERT INTO TestRunViews VALUES (@ID, 3, @start3view, @end3view);
GO 

EXEC TestRunTablesProc


SELECT * FROM TestRuns
SELECT * FROM TestRunTables
SELECT * FROM TestRunViews

DELETE FROM TestRuns
DELETE FROM TestRunTables
DELETE FROM TestRunViews