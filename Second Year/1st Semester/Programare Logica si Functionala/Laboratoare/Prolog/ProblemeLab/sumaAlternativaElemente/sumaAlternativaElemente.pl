% Model matematic:
% suma(l1..ln, f) = {
%                     0, daca n = 0
%                     l1, daca n = 1
%                     l1 - l2 + suma(l2..ln,1), altfel
%                   }
% Specificatii:
% suma(l - lista, r - int)
% l: lista initiala
% r: rezultatul sumei
%
% Model de flux: (i,o), (i,i)

suma([],0):-!.
suma([H],H):-!.
suma([H1,H2|T],R):-
    suma(T,R1),
    R is R1+H1-H2.
