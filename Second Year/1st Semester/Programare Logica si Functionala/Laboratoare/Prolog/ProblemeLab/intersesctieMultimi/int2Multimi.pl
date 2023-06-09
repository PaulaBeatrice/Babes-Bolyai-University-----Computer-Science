% Modelul matematic:
% exista(l1...ln, e) =
% {
%     False, daca n = 0
%     True, daca l1 = e
%     exista(l2...ln, e), altfel
% }
%
% intersectie(l1...ln, m)=
% {
%    [], daca n = 0
%    l1 U intersesctie(l2..ln,m), daca exista(m,l1) = True
%    intersectie(l2...ln,m), daca exista(m,l1) = False
%
% Specificatii:
% exista(l -lista, e - int)
% l: lista initiala
% e: elementul care se va cauta in lista
%
% intersesctie(l -lista, m-lista, r-lista)
% l: lista initiala
% m: a doua lista initiala
% r: intersectia celor 2 multimi
%
% Modele de flux: (i,i,o), (i,i,i); (i,i,o), (i,i,i)

exista([H|_], E):-
    H =:= E, !.
exista([_|T], E):-
    exista(T,E).

intersectie([],_,[]).
intersectie([H|T],M,[H|R]):-
    exista(M,H),!,
    intersectie(T,M,R).
intersectie([_|T],M,R):-
    intersectie(T,M,R).
