% Model matematic:
% inserare(l1...ln, p1...pm)={
%   p1...pm, daca n = 0
%   l1 U inserare(l2...ln,p1...pm), altfel
% }
%
% inlocuire(l1...ln, el, p1...pm)={
%   [], daca n = 0
%   inlocuire(inserare(p1...pm, l2...ln), el, p1...pm), daca l1 = el
%   l1 U inlocuire(l2...ln, el, p1..pm), altfel
% }
%
% Specificatii:
% inserare(l - lista, m - lista, r - lista)
% l: lista in care inserez valorile listei m
% m: lista pe care o inserez
% r: lista finala
%
% inlocuire(l - lista, e - int, p - lista, r - lista)
% l: lista initiala
% e: elementul care va fi inlocuit
% p: lista cu care va fi inlocuit elementul e
% r: lista rezultata
%
% Modele de flux: (i,i,o), (i,i,i)
%                 (i,i,i,o), (i,i,i,i)

inserare([], P, P).
inserare([H|T], P, [H|R]):-
    inserare(T, P, R).

inlocuire([], _, _, []):- !.
inlocuire([H|T], H, P, R):- !,
    inserare(P, T, Rez),
    inlocuire(Rez, H, P, R).
inlocuire([H|T], E, P, [H|R]):-
    inlocuire(T, E, P, R).
