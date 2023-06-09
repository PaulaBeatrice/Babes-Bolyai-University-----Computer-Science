% Model matematic:
% nr_aparitii(l1...ln, el) = {
%                            0, daca n = 0
%                            nr_aparitii(l2...ln, el), daca el != l1
%                            1 + nr_aparitii(l2...ln, el), daca el = l1
%                            }
% isMultime(l1..ln) = {
%                       True, daca n = 0
%                       False, daca nr_aparitii(l1...ln,l1)>1
%                       isMultime(l2..ln), altfel
%                        }
% Specificatii:
% nr_aparitii(l - lista, e - int, r - int)
% l: lista initiala
% e: element din lista
% r: nr de aparitii a lui e in l
%
% isMultime(l - lista,m - lista)
% l: lista initiala
% m: lista initiala

nr_aparitii([],_,0).
nr_aparitii([H|T],E,R):- H=\=E,!,
    nr_aparitii(T,E,R).
nr_aparitii([H|T],H,R):-
    nr_aparitii(T,H,R1),
    R is R1+1.

%isMultime([],_):-!.
isMultime([]):-!.
isMultime([H|T]):-
    nr_aparitii([H|T],H,Nr),
    Nr =:= 1,!,
    isMultime(T).
