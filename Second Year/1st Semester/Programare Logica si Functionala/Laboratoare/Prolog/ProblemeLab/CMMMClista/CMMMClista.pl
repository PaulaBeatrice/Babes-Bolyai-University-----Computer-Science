% Model matematic:
% cmmdc(a, b)= {
%                b, daca a = 0
%                a, daca b = 0
%                cmmdc(a%b,b), daca a >= b
%                cmmdc(a,b%a), daca b > a
%              }
% cmmmc(a, b) = a * b / cmmdc(a, b)
% cmmmcList(l1..ln) = {
%                        l1, daca n = 1
%                        cmmmc(l1, cmmmc(l2..ln)), altfel
%                      }
% Specificatii:
% cmmdc(a - int, b - int, r - int)
% a - primul numar
% b - al doilea numar
% r - cmmdc dintre a si b
%
% cmmmcList(l - lista, r - int)
% l - lista cu elemente
% r - cmmmc elementelor din lista l
%
% Modele de flux: (i,i,o), (i,i,i); (i,o), (i,i);
%
cmmdc(0,B,B):-!.
cmmdc(A,0,A):-!.
cmmdc(A,B,R):- A >= B,!,
    A1 is A mod B,
    cmmdc(A1,B,R).
cmmdc(A,B,R):-
    B1 is B mod A,
    cmmdc(A,B1,R).

cmmmc(A,B,R):- cmmdc(A,B,R1),R is A*B / R1.

cmmmc_list([],A,A).
cmmmc_list([H|T],Aux,R):-cmmmc(H,Aux,R2),cmmmc_list(T,R2,R).

cmmmc_pp([H|T],R):-cmmmc_list([H|T],H,R).












