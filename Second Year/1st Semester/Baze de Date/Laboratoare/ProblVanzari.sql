IF EXISTS(
	SELECT	* 
	FROM	sys.databases AS dbs
	WHERE	dbs.name = 'Examen'
	)
BEGIN
	DROP DATABASE Examen;
END
CREATE DATABASE Examen;
PRINT 'Database Examen was created';


USE Examen;

-- creez tabelul Clienti
IF EXISTS(
	SELECT	*
	FROM	SYS.tables AS tab
	WHERE	tab.name = 'Clienti'
)
BEGIN
	DROP TABLE Clienti;
END

CREATE TABLE Clienti
(
	IdClient		int				IDENTITY(1, 1)		PRIMARY KEY,
	Denumire		nvarchar(100)						NOT NULL,
	CodFiscal		numeric(10, 0)						NOT NULL
);
GO
PRINT 'Tabel Clienti was created';


-- creez tabelul Agenti
IF EXISTS(
	SELECT	*
	FROM	SYS.tables AS tab
	WHERE	tab.name = 'Agenti'
)
BEGIN
	DROP TABLE Agenti;
END
CREATE TABLE Agenti
(
	IdAgent			int				IDENTITY(1, 1)		PRIMARY KEY,
	Nume			nvarchar(50)						NOT NULL,
	Prenume			nvarchar(50)						NOT NULL
);
GO
PRINT 'Tabel Agenti was created';


-- creez tabelul Produse
IF EXISTS(
	SELECT	*
	FROM	SYS.tables AS tab
	WHERE	tab.name = 'Produse'
)
BEGIN
	DROP TABLE Produse;
END
CREATE TABLE Produse
(
	IdProdus		int				IDENTITY(1, 1)		PRIMARY KEY,
	Denumire		nvarchar(50)						NOT NULL,
	UM				nvarchar(50)						NOT NULL
);
GO
PRINT 'Tabel Produse was created';


-- creez tabelul Facturi
IF EXISTS(
	SELECT	*
	FROM	SYS.tables AS tab
	WHERE	tab.name = 'Facturi'
)
BEGIN
	DROP TABLE Facturi;
END
CREATE TABLE Facturi
(
	IdFactura		int				IDENTITY(1, 1)		PRIMARY KEY,
	NumarFactura	int									NOT NULL,
	DataEmiteri		date								NOT NULL,
	IdClient		int									NOT NULL,
	IdAgent			int									NOT NULL,

	FOREIGN KEY (IdClient)			REFERENCES Clienti(IdClient),
	FOREIGN KEY (IdAgent)			REFERENCES Agenti(IdAgent)
);
GO
PRINT 'Tabel Facturi was created';


-- creez tabelul ProduseFacturi
IF EXISTS(
	SELECT	*
	FROM	SYS.tables AS tab
	WHERE	tab.name = 'ProduseFacturi'
)
BEGIN
	DROP TABLE ProduseFacturi;
END
CREATE TABLE ProduseFacturi
(
	IdProdus		int									NOT NULL,
	IdFactura		int									NOT NULL,
	NrOrdine		int									NOT NULL,
	Pret			money								NOT NULL,
	Cantitate		int									NOT NULL,

	
	FOREIGN KEY		(IdProdus)		REFERENCES Produse(IdProdus),
	FOREIGN KEY		(IdFactura)		REFERENCES Facturi(IdFactura),

	PRIMARY KEY(IdProdus, IdFactura),
);
GO
PRINT 'Tabel ProduseFacturi was created';


ALTER PROCEDURE AdaugareProdus
	@IdFactura					int,
	@IdProdus					int,
	@NrOrdine					int,
	@Pret						money,
	@Cantitate					int
AS
BEGIN
	DECLARE	@valueToAdd						int;
	SET	@valueToAdd							= 0;
	SET NOCOUNT ON;
	-- validare date daca trebuie
	BEGIN TRANSACTION;
	BEGIN TRY	
		IF EXISTS(
			SELECT	*
			FROM	ProduseFacturi AS pf
			WHERE	(@IdProdus	= pf.IdProdus) AND
					(@IdFactura	= pf.IdFactura)
		)
		BEGIN
			PRINT 'Produsul exista pe factura, se va scadea';
			SET @valueToAdd = -@Cantitate;
		END
		ELSE
		BEGIN
			PRINT 'Produsul nu exista pe factura, se va adauga';
			SET @valueToAdd = @Cantitate;
		END
		INSERT INTO ProduseFacturi(IdFactura,IdProdus,NrOrdine,Pret,Cantitate)VALUES(@IdFactura,@IdProdus,@NrOrdine,@Pret,@valueToAdd);
		PRINT 'Success';
		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH		
		PRINT 'Transaction failed';
		ROLLBACK TRANSACTION;
	END CATCH
END
GO

------------ 3 -----------
SELECT	fac.IdFactura			AS 'IdFactura', 
		cl.Denumire				AS 'NumeClient',
		fac.NumarFactura		AS 'NumarFactura',
		fac.DataEmiteri			AS 'DataEmiterii',
		SUM(pf.Cantitate * pf.Pret)	AS PretTotal
FROM	Facturi AS fac
		INNER JOIN ProduseFacturi AS pf
			ON (fac.IdFactura = pf.IdFactura)
		INNER JOIN Produse AS prod
			ON (pf.IdProdus = prod.IdProdus)
		INNER JOIN Clienti AS cl
			ON (fac.IdClient = cl.IdClient)
WHERE	'Produs1' IN (
						SELECT	prod2.Denumire
						FROM	ProduseFacturi AS pf2
								INNER JOIN Produse AS prod2
									ON (pf2.IdProdus = prod2.IdProdus)
						WHERE	(pf2.IdFactura = pf.IdFactura)
						)
GROUP BY	fac.IdFactura,
			cl.Denumire,
			fac.NumarFactura,
			fac.DataEmiteri
HAVING SUM(pf.Cantitate * pf.Pret) > 300;



--------------4-----------------
USE Examen;
GO
ALTER FUNCTION ExFunction4(
)
RETURNS @retTable TABLE
(
	Luna				nvarchar(10),
	NumeAgent			nvarchar(100),
	PrenumeAgent		nvarchar(100),
	ValoareTotala		money
)
AS
BEGIN
	INSERT INTO		@retTable
	SELECT			MONTH(fac.DataEmiteri),
					ag.Nume,
					ag.Prenume,
					SUM(pf.Cantitate * pf.Cantitate)
	FROM			ProduseFacturi AS pf
					INNER JOIN Facturi AS fac
						ON (pf.IdProdus = fac.IdFactura)
					INNER JOIN Agenti AS ag
						ON (fac.IdAgent = ag.IdAgent)
	GROUP BY		MONTH(fac.DataEmiteri),
					ag.Nume,
					ag.Prenume,
					YEAR(fac.DataEmiteri)
	ORDER BY		MONTH(fac.DataEmiteri)	ASC,
					ag.Nume					ASC;
	RETURN;

END;
