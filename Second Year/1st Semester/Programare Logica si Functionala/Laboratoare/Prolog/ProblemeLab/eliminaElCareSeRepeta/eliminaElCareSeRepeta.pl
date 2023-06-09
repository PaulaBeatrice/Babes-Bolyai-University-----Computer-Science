% Model matematic:
% nr_aparitii(l1...ln, el) = {
%                            0, daca n = 0
%                            nr_aparitii(l2...ln, el), daca el != l1
%                            1 + nr_aparitii(l2...ln, el), daca el = l1
%                            }
% eliminare(l1..ln, m) = {
%                     [], daca n = 0
%                     l1 U eliminare(l2...ln), daca nr_aparitii(l1) = 1
%                     eliminare(l2...ln), daca nr_aparitii(l1) > 1
%                   }
% Specificatii:
% nr_aparitii(l - lista, el - int, r - int)
% l: lista initiala
% el: un element
% r: nr de aparitii ale lui el in lista l
%
% eliminare(l - lista, m - lista, r - lista)
% l: lista initiala
% m: lista initiala
% r: lista dupa eliminarea elementelor care se repeta
%
% Modele de flux: (i,i,o), (i,i,i);  (i,i,o), (i,i,i).

nr_aparitii([],_,0).
nr_aparitii([H|T],E,R):- H=\=E,!,
    nr_aparitii(T,E,R).
nr_aparitii([H|T],H,R):- nr_aparitii(T,H,R1),
    R is R1+1.

eliminare([],_,[]).
eliminare([H|T], M, [H|R]):-
    nr_aparitii(M,H,Nr),
    Nr =:= 1,!,
    eliminare(T,M,R).
eliminare([_|T],M,R):-eliminare(T,M,R).

eliminaMain(L,R):-eliminare(L,L,R).

