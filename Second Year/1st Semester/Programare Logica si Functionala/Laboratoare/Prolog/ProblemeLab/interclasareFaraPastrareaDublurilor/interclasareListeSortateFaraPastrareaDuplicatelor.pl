% Model matematic:
% interclasare(l1..ln,p1..pm) = {
%  [], daca n = 0 si m = 0
%  l1...ln, daca m = 0
%  p1...pm, daca n = 0
%  l1 U interclasare(l2...ln, p2...pm), daca l1 = p1
%  l1 U interclasare(l2...ln, p1...pm), daca l1 < p1
%  p1 U interclasare(l1...ln, p2...pm), daca l1 > p1
%  }
%
%  Specificatii:
%  interclasare(l - lista, p - lista, r - lista)
%  l: prima lista
%  p: a doua lista
%  r: lisa rezultata in urma interclasarii listelor l si p
%
%  Model de flux: (i,i,o), (i,i,i)

interclasare([],[],[]):-!.
interclasare(L,[],L):-!.
interclasare([],P,P):-!.
interclasare([L1|T],[P1|V],[L1|R]):-
    L1 =:= P1,!,
    interclasare(T,V,R).
interclasare([L1|T],[P1|V],[L1|R]):-
    L1 < P1,!,
    interclasare(T,[P1|V],R).
interclasare([L1|T],[P1|V],[P1|R]):-
    interclasare([L1|T],V,R).
