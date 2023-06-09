% Model matematic
% perecheEl(l1...ln, E)={
%                        [], daca n = 0
%                        [E,l1] U perecheEl(l2...ln), altfel
%                      }
% pereche(l1...ln)={
%                       [], daca n = 0
%                       [l1,l2], daca n = 1
%                       perecheEl(l1) U pereche(l2...ln), altfel
%                  }
% Specificatii:
% perecheEl(l - lista, e - int, R - lista)
% l: lista initiala
% e: un element din lista
% R: lista finala, care va contine perechi formate din e impreuna cu
% fiecare dintre celelalte elemente din lista l
%
% pereche(l - lista, R - lista)
% l: lista initiala
% R: lista finala, ce va contine perechile dintre fiecare 2 elemente ale
% listei l

perecheEl([],_,[]).
perecheEl([H|T], E,[[E,H]|R]):- perecheEl(T,E,R).

pereche([],[]).
pereche([A,B],[A,B]):-!.
pereche([H|T],[Rez|R]):-
    perecheEl(T, H, Rez),
    pereche(T,R).
