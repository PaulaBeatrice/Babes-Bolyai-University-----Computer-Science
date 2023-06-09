% Model matematic:
% sublista(l1...ln,m,n,pos)={
%  [], daca m >= n
%  [l1], daca pos = n
%  l1 U subslista(l2...ln,m,n,pos+1), daca m <= pos < n
%  sublista(l2...ln,m,n,pos+1), daca pos < m
% }
%
% Specificatii:
% sublista(l - lista, m - int, n - int, pos - int, r - lista)
% l: lista initiala
% m: pozitia 1
% n: pozitia 2
% pos: pozitia curenta
% r: lista rezultata
%
% Modele de flux: (i,i,i,i,o), (i,i,i,i,i).

sublista(_,M,N,_,[]):-
    M >= N, !.
sublista([H|_],_,N,N,[H]):-!.
sublista([H|T],M,N,P,[H|R]):-
    M =< P, P<N, !,
    P1 is P+1,
    sublista(T,M,N,P1,R).
sublista([_|T],M,N,P,R):-
    P1 is P+1,
    sublista(T,M,N,P1,R).

sublistaMain(L,M,N,R):- sublista(L,M,N,1,R).
