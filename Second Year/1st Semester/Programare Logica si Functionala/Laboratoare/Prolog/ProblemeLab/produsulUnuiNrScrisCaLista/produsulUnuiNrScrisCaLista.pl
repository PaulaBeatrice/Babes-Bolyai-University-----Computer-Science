% Model matematic:
%
% prod(l1...ln,nr,tr)={
%   [0], daca nr = 0
%   [], daca n = 0 si tr = 0
%   [tr], daca n = 0 si tr != 0
%   (l1*nr+tr)%10 U prod(l2...ln,nr,(l1*nr+tr)/10), altfel
% }

adaug(E,[],[E]):-!.
adaug(E,[H|T],[H|L]):-adaug(E,T,L).

invers([],[]).
invers([H|T],LRez):-invers(T,L), adaug(H,L,LRez).

prod(_,0,_,[0]):-!.
prod([],_,0,[]):- !.
prod([],_,TR,[TR]):-!.
prod([H|T],C,TR,[H1|RR]):-
    H1 is (((H*C+TR) mod 10)),
    T1 is (H*C+TR) div 10,
    prod(T,C,T1,RR).

produs(N,C,R):-
    invers(N,NR),
    prod(NR,C,0,RR),
    invers(RR,R).
