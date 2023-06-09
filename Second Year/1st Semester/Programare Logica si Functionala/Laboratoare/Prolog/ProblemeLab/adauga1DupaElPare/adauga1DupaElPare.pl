% Model matematic:
%   adauga(l1..ln) = {
%                      [], daca n = 0
%                      l1 U adauga(l2..ln), daca l1 este impar
%                      l1 U 1 U adauga(l2..ln), daca l1 este par
%                    }
% Specificatii:
% adauga(l - lista, r - lista)
% l - lista initiala
% r - lista formata din l prin adaugarea elementului 1 dupa fiecare
% element par
%
% Modele de flux: (i,o), (i,i).

adauga([],[]).
adauga([H|T], [H|R]):- H mod 2 =:= 1, !,
    adauga(T,R).
adauga([H|T], [H,1|R]):- adauga(T,R).
