CREATE DATABASE CabinetOftalmologic
GO
USE CabinetOftalmologic
GO

CREATE TABLE Pacienti
(
	id INT PRIMARY KEY IDENTITY(1,1),
	nume varchar(30) NOT NULL,
	prenume varchar(30) NOT NULL
);

CREATE TABLE Roluri
(
	id INT PRIMARY KEY IDENTITY(1,1),
	rol varchar(30) NOT NULL,
	salar INT NOT NULL
);

CREATE TABLE Angajati 
(
	id INT PRIMARY KEY IDENTITY(1,1),
	nume varchar(30) NOT NULL,
	prenume varchar(30) NOT NULL,
	rol INT FOREIGN KEY REFERENCES Roluri(id)
);

CREATE TABLE Concedii
(
	id INT PRIMARY KEY IDENTITY(1,1),
	angajat INT FOREIGN KEY REFERENCES Angajati(id),
	data_inceput DATETIME,
	data_sfarsit DATETIME
);

CREATE TABLE Programari
(
	id INT PRIMARY KEY IDENTITY(1,1),
	pacient INT FOREIGN KEY REFERENCES Pacienti(id),
	medic INT FOREIGN KEY REFERENCES Angajati(id),
	data DATETIME
);

CREATE TABLE Producatori
(
	id INT PRIMARY KEY IDENTITY(1,1),
	denumire varchar(30) NOT NULL,
	oras varchar(30) NOT NULL
);

CREATE TABLE Lentile
(
	cod INT PRIMARY KEY IDENTITY(1,1),
	producator INT FOREIGN KEY REFERENCES Producatori(id)
);

CREATE TABLE Rame
(
	cod INT PRIMARY KEY IDENTITY(1,1),
	producator INT FOREIGN KEY REFERENCES Producatori(id)
);

CREATE TABLE Ochelari
(
	id INT PRIMARY KEY IDENTITY(1,1),
	rama INT FOREIGN KEY REFERENCES Rame(cod),
	lentile INT FOREIGN KEY REFERENCES Lentile(cod),
	dioptrie_ochi_stang FLOAT,
	dioptrie_ochi_drept FLOAT,
	pret INT
);

CREATE TABLE Comenzi
(
	id INT PRIMARY KEY IDENTITY(1,1),
	pacient INT FOREIGN KEY REFERENCES Pacienti(id),
	data_livrarii DATETIME
);

CREATE TABLE OchelariComenzi
(
	ochelari INT FOREIGN KEY REFERENCES Ochelari(id),
	comanda INT FOREIGN KEY REFERENCES Comenzi(id),
	CONSTRAINT PK_OchelariComenzi PRIMARY KEY (ochelari, comanda)
);