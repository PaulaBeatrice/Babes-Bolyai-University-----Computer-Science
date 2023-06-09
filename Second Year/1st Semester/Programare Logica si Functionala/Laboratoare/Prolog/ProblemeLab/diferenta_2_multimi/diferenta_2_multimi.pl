% Model matematic:
% % exista(l1...ln, e) =
% {
%     False, daca n = 0
%     True, daca l1 = e
%     exista(l2...ln, e), altfel
% }
%
% diferentaMultimi(l1..ln, m) =
%       {
%           [], daca n = 0
%           diferentaMultimi(l2...ln, m), daca l1 se afla in lista m
%           l1 U diferentaMultimi(l2...ln, m), daca l1 nu se afla in lista m
%       }
%
% Specificatii:
% exista(l - lista, e - element, r - boolean)
% l - lista in care se va cauta elementul
% e - elementul care va fi cautat in lista l
% r - True, daca e este in lista l, False altfel
%
% diferentaMultimi(l - lista, m - lista, R - lista)
% l - prima lista
% m - a doua lista
% r - lista ce reprezinta diferenta dintre l si m (elementele ce apar in
% l dar nu apar in m)

% MODELE DE FLUX

exista([H|_], E):-
    H =:= E, !.
exista([_|T], E):-
    exista(T,E).

diferentaMultimi([], _, []).
diferentaMultimi([H|T], M, [H|R]):- not(exista(M, H)),!,
    diferentaMultimi(T,M,R).
diferentaMultimi([_|T],M,R):- diferentaMultimi(T,M,R).

