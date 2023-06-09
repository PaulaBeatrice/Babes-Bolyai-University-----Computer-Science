select * from Producatori
select * from Biscuiti


Select pret From Biscuiti Where pret < 5

Create Index ix_pret ON Biscuiti(pret ASC)
DROP INdex ix_pret On Biscuiti



INSERT INTO Biscuiti (nume_b,nr_calorii,pret,cod_p) VALUES
('biscuiti4 Milka',45,5,1);
INSERT INTO Biscuiti (nume_b,nr_calorii,pret,cod_p) VALUES
('biscuiti5 Milka',150,7,1);
INSERT INTO Biscuiti (nume_b,nr_calorii,pret,cod_p) VALUES
('biscuiti6 Milka',80,12,1);
INSERT INTO Biscuiti (nume_b,nr_calorii,pret,cod_p) VALUES
('biscuiti10 Belvita',120,5,2);
INSERT INTO Biscuiti (nume_b,nr_calorii,pret,cod_p) VALUES
('biscuiti80 Belvita',55,7,2);
INSERT INTO Biscuiti (nume_b,nr_calorii,pret,cod_p) VALUES
('biscuiti97 Belvita',74,9,2);
INSERT INTO Biscuiti (nume_b,nr_calorii,pret,cod_p) VALUES
('biscuiti77 Petit Beurre',300,5,3);
INSERT INTO Biscuiti (nume_b,nr_calorii,pret,cod_p) VALUES
('biscuiti84 Petit Beurre',20,8,3);
INSERT INTO Biscuiti (nume_b,nr_calorii,pret,cod_p) VALUES
('biscuiti90 Petit Beurre',65,2,3);


CREATE INDEX ind_nume_calorii ON Biscuiti (nume_b ASC , nr_calorii ASC);

SELECT nume_b, nr_calorii FROM Biscuiti WHERE nr_calorii > 100 order by nume_b;

SELECT nume_b, nr_calorii FROM Biscuiti WHERE nr_calorii = 100 order by nume_b;

SELECT nume_b, nr_calorii FROM Biscuiti WHERE nr_calorii = 100;


CREATE INDEX idx_Biscuiti_nr_calorii ON Biscuiti (nr_calorii);
SELECT * FROM Biscuiti WHERE nr_calorii >= 100;
