% Model matematic:
%
% substituie(l1...ln, e1, r1)={
%  [], daca n = 0
%  l1 U substituie(l2..ln,e1,r1), daca l1 != el
%  r1 U substituie(l2..ln,e1,r1), daca l1 = el
% }
%
% Specificatii:
% substituie(l - lista, e1 - int, r1 - int, r - lista)
% l: lista initiala
% e1: valoarea care va fi inlocuita
% r1: valoarea cu care se va inlocui
% r: lista rezultata in urma inlocuirilor
%
% Modele de flux: (i,i,i,o), (i,i,i,i)

substituie([],_,_,[]).
substituie([H|T],H,R1,[R1|R]):-!,
    substituie(T,H,R1,R).
substituie([H|T],E,R1,[H|R]):-
    substituie(T,E,R1,R).
