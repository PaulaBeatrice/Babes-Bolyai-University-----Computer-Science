% Model matematic:
%
% succesor(l1...ln,rest)={
%   [], daca n = 0
%   [0], daca n = 1, rest = 1, l1=9
%   [l1+1], daca n = 1, rest = 1, l1 != 9
%   0 U succesor(l2...ln,1), daca rest = 1 si l1 = 9
%   [l1+rest] U succesor(l2...ln, ), daca rest = 0
%   }
succesor([],_,[]).
succesor([9],1,[0]):-!.
succesor([N],0,[Nr]):-Nr is N+1,!.
succesor([9|T],1,[0|R]):-
                  succesor(T,1,R),!.
succesor([H|T],0,[HR|R]):-
                  succesor(T,Rest,R),
                  HR is H+Rest,
                  HR<10,!.
succesorMain([9|T],R):-!,
    succesor([0,9|T],0,R).
succesorMain(L,R):-
    succesor(L,0,R).


%VARIANTA 2 - BUNA!!!!!!!!!!!!!!!!!!!!!!!!
%
%Model matematic:
%
%adaug(el,l1...ln)={
%  [el], daca n = 0
%  l1 U adaug(l2...ln), altfel
% }
%
% invers(l1...ln) ={
%  [], daca n = 0
%  adaug(l1,l2...ln), altfel
% }
%
% sum(l1...ln,t)={
%   t, daca n = 0
%   [], daca n = 0 si t = 0
%   (l1+t) % 10 U sum(l2..ln,(l1+t)/10), altfel
% }
%
%Specificatii:
%adaugare la sfarsitul unei liste
%adaug(E:integer, L:list, LRez:list)
%(i,i,o),(i,i,i)
%
%inversa unei liste
%invers(L:list, LRez:list)
%(i,o),(i,i)
%
%adaugare cu transport
%sum(L:list, Tr:integer, LRez:list)
%(i,o),(i,i)
%
%succesor(L:list, LS:list)
%(i,o),(i,i)

adaug(E,[],[E]).
adaug(E,[H|T],[H|L]):-adaug(E,T,L).

invers([],[]).
invers([H|T],LRez):-invers(T,L), adaug(H,L,LRez).

sum([],Tr,[Tr]):-Tr>0,!.
sum([],0,[]):-!.
sum([H|T],Tr,[R|LRez]) :-
                  R is (H+Tr) mod 10,
                  Tr1 is (H+Tr) div 10,
                  sum(T,Tr1,LRez).

succesorE(L,LS):- invers(L,LI),
                  sum(LI,1,LA),
                  invers(LA,LS).
