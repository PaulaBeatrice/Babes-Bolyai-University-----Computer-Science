% Model matematic:
% divizor(N, Nr)={
%   [], daca N=Nr sau N=1
%   divizor(N,Nr+1), daca N%Nr!=0
%   Nr U divizor(N,Nr+1), daca N%Nr=0
% }
%
% divizori(Nr) = divizor(N2,2)
%
% concLists(l1...ln,p1...pm)={
%   p1...pm, daca n = 0
%   l1 U concLists(l2...ln,p1...pm)
% }
%
% addDivizori(l1...ln)={
%   [], daca n = 0
%   l1 U divizori(l1) U addDivizori(l2...ln), altfel
%  }
%
%  Specificatii + Model de flux

divizor(1,_,[]):-!.
divizor(N,N,[]):-!.
divizor(N,D,[D|R]):-
    N mod D=:=0,!,
    D2 is D+1,
    divizor(N,D2,R).
divizor(N,D,R):-
    D2 is D+1,
    divizor(N,D2,R).

divizori(N,Div):-divizor(N,2,Div).

concLists([], K, K).
concLists([H|T], K, [H|R]) :-
    concLists(T, K, R).

addDivizori([],[]):-!.
addDivizori([H|T],[H|Res]):-
    divizori(H,Div),
    addDivizori(T,R),
    concLists(Div,R,Res).
