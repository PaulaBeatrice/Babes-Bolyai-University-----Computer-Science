% cmmdc(a, b)= {
%                b, daca a = 0
%                a, daca b = 0
%                cmmdc(a%b,b), daca a >= b
%                cmmdc(a,b%a), daca b > a
%              }
% cmmdcList(l1...ln)=
% {
%    l1, daca n = 1
%    cmmdc(l1,)
%
% Specificatii:
% cmmdc(a - int, b - int, r - int)
% a - primul numar
% b - al doilea numar
% r - cmmdc dintre a si b
%
% cmmdcList(l - lista, r - int)
% l: lista initiala
% r: cmmdc
%
% Modele de flux: (i,i,o), (i,i,i); (i,o), (i,i)

cmmdc(0,B,B):-!.
cmmdc(A,0,A):-!.
cmmdc(A,B,R):-
    A >= B,!,
    A1 is A mod B,
    cmmdc(A1,B,R).
cmmdc(A,B,R):-
    B1 is B mod A,
    cmmdc(A,B1,R).

cmmdcL([H],H):-!.
cmmdcL([H|T],R):-
    cmmdcL(T,R1),
    cmmdc(H,R1,R).
