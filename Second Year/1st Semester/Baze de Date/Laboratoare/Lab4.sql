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
--  o tabelă având un câmp ca şi cheie primară şi nici o cheie străină
CREATE TABLE Instrumente(
	id INT PRIMARY KEY IDENTITY(1,1),
	denumire VARCHAR(100),
)

-- o tabelă având un câmp ca şi cheie primară şi cel puţin o cheie străină
CREATE TABLE Laboranti(
     id INT PRIMARY KEY IDENTITY(1,1),
	 nume VARCHAR(100),
	 instrument INT FOREIGN KEY REFERENCES Instrumente(id)
)

-- o tabelă având două câmpuri ca şi cheie primară
CREATE TABLE Calificare(
	laborant INT FOREIGN KEY REFERENCES Laboranti(id),
	instrument INT FOREIGN KEY REFERENCES Instrumente(id),
	CONSTRAINT PK_LabInstrument PRIMARY KEY (laborant, instrument),
	experienta INT
)

--drop table Instrumente
--drop table Calificare
--drop table Laboranti






--------------------------------------- VIEW ---------------------------------------------------

-- primul View: un view ce conţine o comandă SELECT pe o tabelă
-- Instrumentele al caror nume incepe cu litera A
CREATE OR ALTER VIEW viewInstrumente AS
	SELECT * FROM Instrumente
	WHERE denumire LIKE 'A%'
GO

-- al doilea View: un view ce conţine o comandă SELECT aplicată pe cel puţin două tabele
-- Experienta laborantilor pentru instumentele folosite
CREATE OR ALTER VIEW viewExperienta AS
	SELECT Laboranti.nume, Instrumente.denumire, Calificare.experienta
	FROM Laboranti, Instrumente, Calificare
	WHERE Calificare.laborant = Laboranti.id AND Laboranti.instrument = Instrumente.id
GO

-- al treilea View: un view ce conţine o comandă SELECT aplicată pe cel puţin două tabele şi având o clauză GROUP BY.
-- Afiseaza instrumentele folosite de cel putin 1 laborant
CREATE or ALTER VIEW viewUtile AS 
	SELECT I.denumire, COUNT(*) AS nr_laboranti
	FROM Laboranti L, Instrumente I
	WHERE L.instrument = I.id
	GROUP BY I.denumire
GO






----------------------------------- HELPER FUNCTIONS -------------------------------------------
-- get the column data type
CREATE OR ALTER FUNCTION ColumnDataType (@tableName varchar(max), @columnName varchar(max))
RETURNS VARCHAR(max) AS
BEGIN
	RETURN (
		SELECT DATA_TYPE from INFORMATION_SCHEMA.COLUMNS 
		where table_name = @tableName and column_name = @columnName)
END
GO

-- check if it's a primary key
CREATE OR ALTER FUNCTION IsPrimaryKey (@tableName varchar(max), @columnName varchar(max))
RETURNS VARCHAR(max) AS
BEGIN
	DECLARE @isPrimaryKey int;
	SET @isPrimaryKey = (SELECT count(*) from 
							INFORMATION_SCHEMA.TABLE_CONSTRAINTS Tab, 
							INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE Col
						 WHERE 
							Col.Constraint_Name = Tab.Constraint_Name 
						 AND Col.Table_Name = Tab.Table_Name
						 AND Constraint_Type = 'PRIMARY KEY'
						 AND Tab.Table_name = @tableName 
						 AND Col.column_name = @columnName 
						 )
	IF @isPrimaryKey = 0 RETURN 'NO'
	RETURN 'YES';
END
GO

-- check if it's a foreign key
CREATE OR ALTER FUNCTION IsForeignKey (@tableName varchar(max), @columnName varchar(max))
RETURNS VARCHAR(max) AS
BEGIN
	DECLARE @isForeignKey int;
	SET @isForeignKey = (SELECT count(*) from 
							INFORMATION_SCHEMA.TABLE_CONSTRAINTS Tab, 
							INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE Col
						 WHERE 
							Col.Constraint_Name = Tab.Constraint_Name 
						 AND Col.Table_Name = Tab.Table_Name
						 AND Constraint_Type = 'FOREIGN KEY'
						 AND Tab.Table_name = @tableName 
						 AND Col.column_name = @columnName 
						 )
	IF @isForeignKey = 0 RETURN 'NO'
	RETURN 'YES';
END
GO

-- get the table that it's referencing
CREATE OR ALTER FUNCTION ColumnReferenceTable (@tableName varchar(max), @columnName varchar(max))
RETURNS VARCHAR(max) AS
BEGIN

	DECLARE @referenceTableName varchar(max);
	DECLARE @constraintName varchar(max) = (SELECT CONSTRAINT_NAME 
											FROM INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CU
											WHERE CU.TABLE_NAME = @tableName AND COLUMN_NAME = @columnName
											INTERSECT
											SELECT CONSTRAINT_NAME
											FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS Tab
											WHERE Tab.TABLE_NAME = @tableName AND CONSTRAINT_TYPE = 'FOREIGN KEY')
	SET @referenceTableName = 
		(	
			SELECT OBJECT_NAME(referenced_object_id)
			FROM sys.foreign_keys
			WHERE name = @constraintName
		);
	RETURN @referenceTableName
END
GO


-- "Function" for creating a completely random number >= 0 and <1
-- The problem is that you cannot call a non-deterministic function from inside a user-defined function.
-- I got around this limitation by creating a view, call that function inside the view and use that view inside your function, something like this......
CREATE VIEW getRandomValue AS
SELECT RAND() AS Value
go

-- Function for returning a number in interval [left, right]
CREATE FUNCTION randomInteger(@left INT, @right INT)
RETURNS INT AS
BEGIN
	DECLARE @randomValue INT;
	SET @randomValue = @left + FLOOR((SELECT Value FROM getRandomValue) * (@right + 1 - @left));
	RETURN @randomValue;
END
GO

-- Function to generate random String
CREATE FUNCTION randomString(@newid uniqueidentifier)
RETURNS VARCHAR(max) AS
BEGIN
	DECLARE @randomString varchar(max);
	SELECT @randomString = left(@newid, dbo.randomInteger(5, 35))
	RETURN @randomString;
END
GO






--------------------------------------- PROCEDURA DE INSERARE ---------------------------------------------------

CREATE OR ALTER PROCEDURE InsertIntoTable(@tablename varchar(50),  @numberOfInsertions int)
AS
BEGIN
	--- validations ---
	IF (NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = @tableName))
	BEGIN
			RAISERROR('Tabela nu exista in tabelul de date!!!', 10, 1) --text, severity, state
			RETURN
	END
	IF (@numberOfInsertions <= 0)
	BEGIN
			RAISERROR('Nr de insertii este mai mic sau egal cu 0!!!', 10, 1)
			RETURN
	END

	-- insert @numberOfInsertions rows into the table
	DECLARE @contor INT = 0
	WHILE(@contor < @numberOfInsertions)
	BEGIN
		-- start with every column
		DECLARE columnCursor CURSOR FOR 
		SELECT COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS  where table_name = @tableName

		DECLARE @columnName varchar(max);
		DECLARE @isPrimaryKey varchar(max); 
		DECLARE @isForeignKey varchar(max); 
		DECLARE @columnDataType varchar(max); 
		DECLARE @columnReferenceTable varchar(max); 
		DECLARE @columnList varchar(max) = ''; -- for insert command, is the list of columns from the table
		DECLARE @valueList varchar(max) = ''; -- for insert command, is the list of values for each column
		DECLARE @randomId INT; -- get random ID from the reference table
		declare @sql nvarchar(max); -- execute a string

		OPEN columnCursor; 
		-- Perform the first fetch.  
		FETCH FROM columnCursor into @columnName;
	
		-- Check @@FETCH_STATUS to see if there are any more rows to fetch.  
		WHILE @@FETCH_STATUS = 0  
		BEGIN  
				-- This is executed as long as the previous fetch succeeds.  
				SET @columnDataType = dbo.ColumnDataType(@tableName, @columnName)
				SET @isPrimaryKey = dbo.isPrimaryKey(@tableName, @columnName)
				SET @isForeignKey = dbo.isForeignKey(@tableName, @columnName)
				if @isForeignKey = 'YES'
					BEGIN
					SET @columnReferenceTable = dbo.ColumnReferenceTable(@tableName, @columnName)
					END

				----- Start generating values
				-- if the data type is int and it's a primary key => identity so i don't care
				-- if the data type is int and it's a normal column => generate random values
				-- if the data type is varchar => generate random strings
				-- if the column is foreign key => choose randomly from the foreign table. if the foreign table is empty->throw error.
				if NOT (@columnDataType = 'int' AND @isPrimaryKey = 'YES' AND @isForeignKey = 'NO')
					set @columnList = @columnList + @columnName + ', ';
				if @columnDataType = 'int' AND @isPrimaryKey = 'NO' AND @isForeignKey = 'NO'
					BEGIN
					SET @valueList = @valueList + convert(varchar(max), dbo.randomInteger(1, 100000)) + ', ';
					END

				if @columnDataType = 'varchar' AND @isForeignKey = 'NO'
					BEGIN
					SET @valueList = @valueList + '''' + dbo.randomString(NEWID()) + ''', '; -- so we can have '<string>'
					END
				if @isForeignKey = 'YES'
					BEGIN
					set @sql = 'SET @randomId = (SELECT TOP 1 Id FROM ' + @columnReferenceTable + ' ORDER BY NEWID())'
					exec sp_executesql @sql, N'@randomId INT OUTPUT', @randomId = @randomId OUTPUT
					SET @valueList = @valueList + convert(varchar(max), @randomId) + ', ';
					END
				FETCH FROM columnCursor into @columnName;
		END  

		set @columnList = (SELECT LEFT(@columnList,DATALENGTH(@columnList)-2)) -- get rid of ", "
		set @valueList = (SELECT LEFT(@valueList,DATALENGTH(@valueList)-2))
		
		-- insert the values into the table
		EXEC('INSERT ' + @tableName + '(' + @columnList + ') VALUES (' + @valueList + ')')

		-- go to default values for the next insertion
		Set @columnList = '';
		Set @valueList = '';
		CLOSE columnCursor
		DEALLOCATE columnCursor
		SET @contor = @contor + 1
	END
END







-------------------------- ADDING AND LINKING ---------------------------------
-- Tests – holds data about different test configurations; TestID INT, Name nvarchar(50);
CREATE OR ALTER PROCEDURE AddTest @name VARCHAR(50)
AS
BEGIN
    IF EXISTS(SELECT * FROM Tests T WHERE Name = @name)
        BEGIN
            PRINT 'Test already exists'
            RETURN
        END
    INSERT INTO Tests(Name) VALUES (@name)
END
GO

-- Tables – holds data about tables that might take part in a test; TableID INT, Name nvarchar(50);
CREATE OR ALTER PROCEDURE AddTable @tableName VARCHAR(50)
AS
BEGIN
	IF NOT EXISTS(SELECT * FROM sys.tables WHERE name = @tableName)
		BEGIN
			PRINT 'Table' + @tableName + ' does not exist'
            RETURN
        END
    IF EXISTS(SELECT * FROM Tables T WHERE T.Name = @tableName)
        BEGIN
            PRINT 'Table ' + @tableName + ' already added to test'
            RETURN
        END
    INSERT INTO Tables(Name) VALUES (@tableName)
END
GO

-- Views – holds data about a set of views from the database, used to assess the performance of certain SQL queries; ViewID INT, Name nvarchar(50);
CREATE OR ALTER PROCEDURE AddView @viewName VARCHAR(50) 
AS
BEGIN
    IF NOT EXISTS(SELECT * FROM INFORMATION_SCHEMA.VIEWS WHERE TABLE_NAME = @viewName)
    BEGIN
        PRINT 'View ' + @viewName + ' does not exist'
        RETURN
    end
    IF EXISTS(SELECT * FROM Views WHERE Name = @viewName)
    BEGIN
        PRINT 'View ' + @viewName + ' already added'
        RETURN
    end
    INSERT INTO Views(Name) VALUES (@viewName)
end
GO

-- TestTables - link table between Tests and Tables (which tables take part in which tests); TestID int, TableID int, NoOfRows int, Position int 
CREATE OR ALTER PROCEDURE LinkTablesAndTests @tableName VARCHAR(50), @testName VARCHAR(50), @noRows INT, @position INT
AS
    BEGIN
        IF @position < 0
        BEGIN
            PRINT 'Position must be >0'
            RETURN
        END
        IF @noRows < 0
        BEGIN
            PRINT 'Number of rows must be >0'
            RETURN
        END

        DECLARE @testID INT, @tableID INT
        SET @testID = (SELECT T.TestID FROM Tests T WHERE T.Name = @testName)
        SET @tableID = (SELECT T.TableID FROM Tables T WHERE T.Name = @tableName)
        INSERT INTO TestTables(TestID, TableID, NoOfRows, Position) VALUES (@testID, @tableID, @noRows, @position)
    END
GO

-- TestViews – link table between Tests and Views (which views take part in which tests); TestID int, ViewID int
CREATE OR ALTER PROCEDURE LinkViewsAndTests @viewName VARCHAR(50), @testName VARCHAR(50)
AS
    BEGIN
        DECLARE @testID INT, @viewID INT
        SET @testID = (SELECT TestID FROM Tests WHERE Name = @testName)
        SET @viewID = (SELECT ViewID FROM Views WHERE Name = @viewName)
        INSERT INTO TestViews(testid, viewid) VALUES (@testID, @viewID)
    end
GO








------------------ RUN TEST PROCEDURE -----------------------
CREATE OR ALTER PROCEDURE RunTest @name VARCHAR(50) AS
BEGIN
    DECLARE @testId INT, @testRunID INT
    SET @testId = (SELECT T.TestID FROM Tests T WHERE T.Name = @name) -- my test ID
	
	INSERT INTO TestRuns(Description) VALUES (@name) -- to have an id
    SET @testRunID = CONVERT(INT, (SELECT last_value FROM sys.identity_columns WHERE name = 'TestRunID'))
	-- columns: TestRunID int, Dscription nvarchar(2000), StartAt(datetime), ndAt(datetime)

	-- SCROLL: Specifies that all fetch options (FIRST, LAST, PRIOR, NEXT, RELATIVE, ABSOLUTE) are available.
	-- get the tables for this test
	DECLARE TablesCursor CURSOR SCROLL FOR
		SELECT T.TableID, T.Name, TT.NoOfRows 
		FROM TestTables TT 
		INNER JOIN Tables T on T.TableID = TT.TableID
		WHERE TT.TestID = @testId
		ORDER BY TT.Position

	-- get the views for this test
	DECLARE ViewsCursor CURSOR FOR
		SELECT V.ViewID, V.Name 
		FROM Views V 
		INNER JOIN TestViews TV on V.ViewID = TV.ViewID
		WHERE TV.TestID = 3

	DECLARE @allTestsStartTime DATETIME2, @allTestsEndTime DATETIME2,
            @currentTestStartTime DATETIME2, @currentTestEndTime DATETIME2
	
	SET @allTestsStartTime = SYSDATETIME();

	-------- start with tables
	DECLARE @tableName VARCHAR(50), @noRows INT, @tableID INT

	-- delete from tables
	DECLARE @deleteCommand nvarchar(200)

	OPEN TablesCursor
	FETCH FIRST FROM TablesCursor INTO @tableID, @tableName, @noRows

    WHILE @@FETCH_STATUS = 0
		begin
        SET @deleteCommand = 'delete from '+ @tableName   
        EXEC (@deleteCommand) -- de ce nu merge fara paranteze?
        FETCH NEXT FROM TablesCursor INTO @tableID, @tableName, @noRows
		end
    CLOSE TablesCursor

    -- DEALLOCATE TablesCursor
	-- populate tables
	OPEN TablesCursor
	FETCH LAST FROM TablesCursor INTO @tableID, @tableName, @noRows

	DECLARE @command VARCHAR(MAX)
	WHILE @@FETCH_STATUS = 0
		begin
        SET @currentTestStartTime = SYSDATETIME();
        SET @command = 'InsertIntoTable ' + char(39) + @tableName + char(39) + ', ' + CONVERT(VARCHAR(10), @noRows)
        print @command
		EXEC(@command)
		print @command
        SET @currentTestEndTime = SYSDATETIME();
		print @testRunID
		print @tableID
		print @currentTestStartTime
		print @currentTestEndTime
        INSERT INTO TestRunTables(TestRunID, TableID, StartAt, EndAt) VALUES (@testRunID, @tableID, @currentTestStartTime, @currentTestEndTime)
        FETCH PRIOR FROM TablesCursor INTO @tableID, @tableName, @noRows
		end
	CLOSE TablesCursor
	DEALLOCATE TablesCursor

	-------- start with views
	DECLARE @viewName VARCHAR(50), @viewID INT	
	OPEN ViewsCursor
    FETCH FROM ViewsCursor INTO @viewID, @viewName
    WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @currentTestStartTime = SYSDATETIME()
            DECLARE @statement VARCHAR(256)
            SET @statement = 'SELECT * FROM ' + @viewName
            EXEC (@statement)
            SET @currentTestEndTime = SYSDATETIME()
            INSERT INTO TestRunViews(TestRunID, ViewID, StartAt, EndAt) VALUES (@testRunID, @viewID, @currentTestStartTime, @currentTestEndTime)
            FETCH NEXT FROM ViewsCursor INTO @viewID, @viewName
        end
	CLOSE ViewsCursor
    DEALLOCATE ViewsCursor

	SET @allTestsEndTime = SYSDATETIME();
    
    UPDATE TestRuns
		SET StartAt = @allTestsStartTime, EndAt = @allTestsEndTime
		WHERE TestRunID = @testRunID
END
go

----------------- EXEC STUFF ---------------------
SELECT * FROM Instrumente
SELECT * FROM Laboranti
SELECT * FROM Calificare
SELECT * FROM Tables
--DELETE TestRunTables
--DELETE TestTables
EXEC AddTable @tableName='Instrumente'
EXEC AddTable @tableName='Laboranti'
EXEC AddTable @tableName='Calificare'
EXEC AddView @viewName='viewInstrumente'
EXEC AddView @viewName='viewExperienta'
EXEC AddView @viewName='viewUtile'
EXEC AddTest @name='Test1'
EXEC AddTest @name='Test2'
EXEC AddTest @name='Test3'

EXEC LinkTablesAndTests @tableName='Calificare', @testName='Test1', @noRows=2530, @position=1
EXEC LinkTablesAndTests @tableName='Laboranti', @testName='Test1', @noRows=3000, @position=2
EXEC LinkTablesAndTests @tableName='Instrumente', @testName='Test1', @noRows=2000, @position=3

EXEC LinkTablesAndTests @tableName='Calificare', @testName='Test2', @noRows=25, @position=1
EXEC LinkTablesAndTests @tableName='Laboranti', @testName='Test2', @noRows=30, @position=2
EXEC LinkTablesAndTests @tableName='Instrumente', @testName='Test2', @noRows=20, @position=3


EXEC LinkViewsAndTests @viewName='viewUtile', @testName='Test3'
EXEC LinkViewsAndTests @viewName='viewExperienta', @testName='Test3'
EXEC LinkViewsAndTests @viewName='viewInstrumente', @testName='Test3'
EXEC LinkTablesAndTests @tableName='Calificare', @testName='Test3', @noRows=45, @position=1
EXEC LinkTablesAndTests @tableName='Laboranti', @testName='Test3', @noRows=50, @position=2
EXEC LinkTablesAndTests @tableName='Instrumente', @testName='Test3', @noRows=10, @position=3

SELECT * FROM Views
SELECT * FROM TestViews

SELECT * FROM TestTables
SELECT * FROM TestRunTables
SELECT * FROM TestRuns
SELECT * FROM TestRunViews

EXEC RunTest 'Test3'