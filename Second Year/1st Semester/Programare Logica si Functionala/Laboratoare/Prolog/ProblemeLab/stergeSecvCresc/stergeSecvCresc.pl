% el(l1...ln)={
%  [], daca n = 0
%  l1, daca n = 1
%  [], daca n = 2 si l1<l2
%  el(l2...ln), daca l1<l2<l3
%  el(l3...ln), daca l1 <l2>=l3
%  l1 U el(l2..ln), altfel


%
eliminaCresc(L:list, R:list)
% model de flux: (i,o) sau (i,i)
% L - lista din care eliminam secventele de elemente crescatoare
% R - lista rezultat
eliminaCresc([], []).
eliminaCresc([H], [H]).
eliminaCresc([H1,H2], []):- H1 < H2.
eliminaCresc([H1,H2,H3|T], R):-
H1 < H2,
H2 < H3,
eliminaCresc([H2,H3|T], R).
eliminaCresc([H1,H2,H3|T], R):-
H1 < H2,
H2 >= H3,
eliminaCresc([H3|T], R).
eliminaCresc([H1,H2|T], [H1|R]):-
H1 >= H2,
eliminaCresc([H2|T], R).
