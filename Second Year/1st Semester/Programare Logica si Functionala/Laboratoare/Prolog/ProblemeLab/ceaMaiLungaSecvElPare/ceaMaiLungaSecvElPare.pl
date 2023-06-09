% Model matematic:
% adaugla final
%
% size
%
% secvPare(l1...ln, p1...pm, t1..tq)={
%   p1...pm, daca n = 0 si len(p) >= len(t)
%   t1...tq, daca n = 0 si len(p) < len(t)
%   secvPare(l2...ln, p1...pm, t1...tql1), daca l1 este par
%   secvPare(l2...ln, p1...pm, []), daca l1 este impar si len(p) >
%   len(t)
%   secvPare(l2...ln, [], t1...tn), daca l1 este impar si len(p) <
%   len(t)
%
% p - secv maxima
% c - secv curenta

adauglafinal([],E,[E]).
adauglafinal([H|T],E,[H|R]):-
	adauglafinal(T,E,R).

size([],0).
size([_|T],R):-
	size(T,R1),
	R is R1 + 1.

secvPare([],L,C,L):-
	size(L,N),
	size(C,M),
	N >= M,!.
secvPare([],_,C,C):-!.
secvPare([H|T],L,C,R):-
	0 =:= H mod 2,!,
	adauglafinal(C,H,R1),
	secvPare(T,L,R1,R).
secvPare([_|T],L,C,R):-
	size(L,N),
	size(C,M),
	M >= N,!,
	secvPare(T,C,[],R).
secvPare([_|T],L,_,R):-
	secvPare(T,L,[],R).

secvPareMain(L,R):-
	secvPare(L,[],[],R).


