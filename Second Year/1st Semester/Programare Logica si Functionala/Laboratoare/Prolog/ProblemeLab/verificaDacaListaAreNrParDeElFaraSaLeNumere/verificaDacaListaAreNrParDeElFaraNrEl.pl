% Model matematic:
% par(l1...ln)=
% {
%   True, daca n = 0
%   par(l3..ln), altfel
% }
%
% Specificatii:
% par(l - lista)
% l: lista initiala
%
% Modele de flux: (i)

par([]).
par([_,_|T]):-
    par(T).
