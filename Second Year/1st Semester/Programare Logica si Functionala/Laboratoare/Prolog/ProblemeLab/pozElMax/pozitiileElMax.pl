% Model matematic:
% max(l1...ln) = {
%                  l1, daca n = 1
%                  l1, daca l1 > max(l2...ln)
%                  max(l2...ln), altfel
%                }
%
% pozitiiMax(l1..ln, Max, Poz)={
%    [], daca n = 0
%    Poz U pozitiiMax(l2...ln, Max, Poz+1), daca l1 = Max
%    pozitiiMax(l2...ln, Max, Poz+1), daca l1 != Max
%  }
%
% pozMaxim(l1...ln) ={
%    [], daca n = 0
%    pozitiiMax(l1...ln,Max(l1...ln),1), altfel
%  }
%
% Specificatii:
% max(l-lista, m-int)
% l: lista
% m: el max din lista l
%
% pozitiiMax(l - lista, m - int, p - int, r - lista)
% l: lista initiala
% m: maximul listei
% p: pozitia curenta
% r: lista rezultata
%
% Model de flux: (i,o), (i,i)
%                (i,i,i,o), (i,i,i,i)
max([H],H).
max([H|T],M):-max(T,M),
    H<M,!.
max([H|_],H).

pozitiiMax([],_,_,[]):-!.
pozitiiMax([H|T],H,P,[P|R]):-!,
    P1 is P+1,
    pozitiiMax(T,H,P1,R).
pozitiiMax([_|T],M,P,R):-
    P1 is P+1,
    pozitiiMax(T,M,P1,R).

pozMaxim([],[]):-!.
pozMaxim(L,R):-
    max(L,M),
    pozitiiMax(L,M,1,R).






