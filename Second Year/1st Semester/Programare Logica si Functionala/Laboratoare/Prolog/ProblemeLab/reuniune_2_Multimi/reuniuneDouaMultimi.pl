% Model matematic:
% % exista(l1...ln, e) =
% {
%     False, daca n = 0
%     True, daca l1 = e
%     exista(l2...ln, e), altfel
% }
%
% reuniuneMultimi(l1..ln, m1..mt) =
%       {
%           m1..mt, daca n = 0
%           reuniuneMultimi(l2..ln,m1...mt), daca l1 se afla in lista m
%           l1 U reuniuneMultimi(l2...ln, m), daca l1 nu se afla in lista m
%       }
%
% Specificatii:
% exista(l - lista, e - element, r - boolean)
% l - lista in care se va cauta elementul
% e - elementul care va fi cautat in lista l
% r - True, daca e este in lista l, False altfel
%
% reuniuneMultimi(l - lista, m - lista, R - lista)
% l - prima lista
% m - a doua lista
% r - lista ce reprezinta reuniunea dintre l si m
% MODELE DE FLUX: (i,o), (i,i), (i,i,o), (i,i,i).

exista([H|_], E):-
    H =:= E, !.
exista([_|T], E):-
    exista(T,E).

reuniuneMultimi([],M,M).
reuniuneMultimi([H|T],M,R):-
    exista(M, H),!,
    reuniuneMultimi(T,M,R).
reuniuneMultimi([H|T],M,[H|R]):-
    reuniuneMultimi(T,M,R).
