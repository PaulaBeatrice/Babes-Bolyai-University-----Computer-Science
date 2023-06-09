% Model matematic:
% el(l1..ln, k, poz)={
%   [], daca n = 0
%   l1 U el(l2...ln,k,poz+1), daca poz % k != 0
%   el(l2..ln,k,poz+1), daca poz % k = 0
% }

el([],_,_,[]).
el([H|T],K,P,[H|R]):-
    P mod K =\= 0,
    P1 is P+1,
    el(T,K,P1,R).
el([_|T],K,P,R):-
    P mod K =:= 0,
    P1 is P+1,
    el(T,K,P1,R).
