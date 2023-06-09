SELECT nume_briosa , pret FROM Briose WHERE pret = 10
 
CREATE INDEX ix_pret_nume ON Briose (pret ASC , nume_briosa ASC)


select * from tari

create index index_nume_tara on tari(nume_tara asc)

select * from tari

create index index_nume_tara3 on tari(nume_tara desc)


select * from Cofetarii
SELECT *
FROM Cofetarii
WHERE nume_cofetarie = 'Lemnul Verde';

CREATE INDEX idx_nume_cofetarie ON Cofetarii(nume_cofetarie);

