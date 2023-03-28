CREATE DATABASE Magazin
GO
USE Magazin


CREATE TABLE Locatii(
	id_loc INT PRIMARY KEY IDENTITY(1,1),
	localitate VARCHAR(100),
	strada VARCHAR(100),
	nr INT,
	cod_postal VARCHAR(100)
)

CREATE TABLE Magazine(
	id_mag INT PRIMARY KEY IDENTITY(1,1),
	denumire VARCHAR(100),
	id_locatie INT FOREIGN KEY REFERENCES Locatii(id_loc)
)

CREATE TABLE Clienti(
	id_cl INT PRIMARY KEY IDENTITY(1,1),
	nume VARCHAR(100),
	prenume VARCHAR(100),
	gen VARCHAR(100),
	data_nasteroo DATETIME
)

CREATE TABLE ProduseFavorite(
	id_pr INT PRIMARY KEY IDENTITY(1,1),
	denumire VARCHAR(100),
	pret INT,
	reducere INT,
	id_client INT FOREIGN KEY REFERENCES Clienti(id_cl)
)

CREATE TABLE MagazineClienti(
	id_magazin INT FOREIGN KEY REFERENCES Magazine(id_mag),
	id_client INT FOREIGN KEY REFERENCES Clienti(id_cl),
	CONSTRAINT PK_MagazineClienti PRIMARY KEY(id_magazin, id_client),
	data_cumpararii DATETIME,
	pret_achitat INT
)

INSERT INTO Clienti(nume,prenume,gen, data_nasteroo) VALUES ('ANA','BACIU','F',2023-05-06), ('DAN','SUCIU','M',2023-05-06), ('MIHAI','POP','M',2023-05-06)
INSERT INTO Locatii(localitate,strada,nr, cod_postal) VALUES('Cluj','Soarelui',2,'345654'),('Bucuresti','A2',2,'2345678'),('Cluj','Zorilor',4,'2344')
INSERT INTO Magazine(denumire, id_locatie) VALUES('ABC',2),('De ce',1),('Magazin',1)
INSERT INTO ProduseFavorite(denumire,pret,reducere,id_client) VALUES('A',100,23,2),('B',100,23,3),('C',100,23,1),('C',100,23,1),('C',100,23,1),('C',100,23,1)
INSERT INTO MagazineClienti(id_magazin,id_client,data_cumpararii,pret_achitat) VALUES (1,1,2023-01-01, 200)

--- 2) Procedura stocata ce primeste un client, magazin, o data de cumparare si un pret achitat si adauga clientul magazinului
-- Daca deja exista se actualizeaza pretul achitat si clientul magazinului.
CREATE OR ALTER PROCEDURE UpAddClient
@client VARCHAR(100),
@magazin VARCHAR(100),
@data_cumparare DATETIME,
@pret_achitat INT
AS 
BEGIN
	DECLARE @idMag INT;
	DECLARE @idCl INT;
	SELECT TOP 1 @idMag=id_mag FROM Magazine WHERE denumire=@magazin;
	SELECT TOP 1 @idCl=id_cl FROM Clienti WHERE nume=@client;

	IF(@idMag IS NULL OR @idCl IS NULL)
		THROW 50003, 'Nu exista magazinul sau clientul!', 1;

	IF EXISTS(SELECT * FROM MagazineClienti WHERE id_magazin = @idMag AND id_client = @idCl)
		BEGIN
			UPDATE MagazineClienti SET data_cumpararii=@data_cumparare , pret_achitat=@pret_achitat WHERE id_magazin =@idMag AND id_client=@idCl
		END
	ELSE
		BEGIN 
			INSERT INTO MagazineClienti(id_magazin,id_client,data_cumpararii,pret_achitat) VALUES(@idMag,@idCl,@data_cumparare,@pret_achitat)
		END
END

GO
EXEC UpAddClient 'ANA', 'De ce', '2023-05-06', 150
EXEC UpAddClient 'ANA', 'ABC', '2023-05-06', 150

SELECT * FROM MagazineClienti

INSERT INTO Clienti(nume,prenume,gen, data_nasteroo) VALUES ('GRAS','S','F',2023-05-06)
--- 3) View care afiseaza numele clientilor ce au cel mult 3 produse favorite
CREATE OR ALTER VIEW clientiView
AS 
	SELECT C.nume FROM ProduseFavorite RIGHT JOIN Clienti C ON id_client = id_cl GROUP BY C.nume HAVING COUNT(*) <= 3
GO

SELECT * FROM clientiView

