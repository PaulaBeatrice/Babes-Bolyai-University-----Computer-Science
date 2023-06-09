% Model matematicL
% elN(l1...ln, Poz, N)=
% {
%   l1, daca N = Poz
%   elN(l2...ln, Poz+1, N), daca Poz < N
% }
%
% Specificatii:
% elN(l - lista, Poz - int, N - int, R - int)
% l: lista initiala
% Poz: pozitia curent
% N: pozitia pe care o cautam
% R: elementul de pe pozitia N din lista l
%
% Model de flux: (i,i,i,o), (i,i,i,i)

elN([H|_],P,P,H):-!.
elN([_|T],P,N,R):-
    P1 is P+1,
    elN(T,P1,N,R).

elNMain(L,N,R):- elN(L,1,N,R).
