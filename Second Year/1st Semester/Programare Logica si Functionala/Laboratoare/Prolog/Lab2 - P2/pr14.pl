% 14. a)Definiti un predicat care determina predecesorul unui numar
% reprezentat cifra cu cifra intr-o lista.
% De ex: [1 9 3 6 0 0] => [1 9 3 5 9 9]
% b)Se da o lista eterogena, formata din numere intregi si liste
% de cifre. Pentru fiecare sublista sa se determine predecesorul
% numarului reprezentat cifra cucifra de lista respectiva.
% De ex:[1, [2, 3], 4, 5, [6, 7, 9], 10, 11, [1, 2, 0], 6] =>[1, [2, 2],
% 4, 5, [6, 7, 8], 10, 11, [1, 1, 9] 6]
% % predecesorLista([[1,0], 9, 8, [1], [3,0,0], 8, 6, [1,4,3], R).
% => R = [[9], 9, 8, [0], [299], 8, 6, [142]] ;
%
% a) Model matematic:
% predecesor(l1...ln, v)={
%                         [], daca n = 0
%                         [9], daca v = 1, n = 1, l1 = 0
%                         [l1-1], daca v = 0, n = 1, l1 != 0
%                         9 U predecesor(l2...ln, v), daca v = 1, l1 = 0
%                         (l1-v) U predecesor(l2...ln, v), daca v = 0
%                        }
% predecesor(L-lista, V-intreg, R-lista)
% Model de flux: (i,i,o), (i,i,i)
% L - lista data
% V - valoarea imprumutata la scadere
% R - lista rezultata, ce va reprezenta predecesorul nr format din
% elementele listei L

predecesor([], _, []) :- !.
predecesor([0], 1, [9]) :- !.
predecesor([H], 0, [HR]):- HR is H - 1, !.
predecesor([0|T], 1, [9|R]):-
    predecesor(T, 1, R), !.
predecesor([H|T], 0, [HR|R]):-
    predecesor(T, V, R),
    HR is H - V.

% b) Model matematic:
%  predecesorListe(l1...ln)= {
%                             [], n = 0
%                             predecesor(l1) U predecesorListe(l2...ln),
%                             daca l1 este lista
%                             l1 U predecesorListe(l2...ln), daca l1 nu
%                             este lista
%                            }
%  predecesorLista(L: lista, R:lista)
%  Model de flux: (i,o), (i,i)
%  L: lista ale carei elemente care sunt liste se vor modifica in lista
%  formata din predecesorul numarului format cu elementele initiale
%  R: lista rezultata in urma transformarii

predecesorLista([],[]).
predecesorLista([H|T], [R1|R]) :-
	is_list(H),
	predecesor(H,0, R1),
	predecesorLista(T,R).

predecesorLista([H|T], [H|R]) :-
	not(is_list(H)),
	predecesorLista(T,R).

