% Model matematic:
% sterge(l1...ln)={
%   [], daca n = 0
%   [l1], daca n = 1
%   [l1,l2], daca n = 2 si l1 <= l2
%   [], daca n = 2 si l1 > l2
%   sterge(l2...ln), daca n >= 3 si l1 > l2 > l3
%   sterge(l3...ln), daca n >= 3 si l1 > l2 si l2 <= l3
%   l1 U sterge(l2...ln), daca l1 <= l2
% }

sterge([],[]).
sterge([H],[H]).
sterge([H1,H2],[H1,H2]):-H1=<H2.
sterge([H1,H2],[]):-H1>H2.
sterge([H1,H2,H3|T],R):- H1 > H2, H2 > H3,
    sterge([H2,H3|T],R).
sterge([H1,H2,H3|T],R):- H1 > H2, H2 =< H3,
    sterge([H3|T],R).
sterge([H1,H2,H3|T],[H1|R]):-H1 =<H2,
    sterge([H2,H3|T],R).
