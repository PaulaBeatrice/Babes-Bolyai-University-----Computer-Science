% Model matematic:
% elimina(l1..ln, poz) = {
%                          [], daca n = 0
%                          l1 U elimina(l2..ln, poz - 1), daca poz != 0
%                          elimina(l2...ln, poz-1), daca poz = 0
%                        }
% Specificatii:
% elimina(l - lista, poz - int, r - lista)
% l: lista initiala
% poz: pozitia de pe care vom sterge
% r: lista rezultata in urma stergerii
% Modele de flux: (i,i,o), (i,i,i)

elimina([],_,[]).
elimina([H|T],P,[H|R]):-
    P =\= 0,!,
    P1 is P - 1,
    elimina(T,P1,R).
elimina([_|T],P,R):-
    P1 is P-1,
    elimina(T,P1,R).
