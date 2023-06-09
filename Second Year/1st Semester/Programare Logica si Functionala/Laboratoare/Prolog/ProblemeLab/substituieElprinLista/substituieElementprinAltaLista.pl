% Model matematic:
% substituie(l1...ln, e, r)={
%                              [], daca n = 0
%                              r U substituie(l2...ln), daca l1 = e
%                              l1 U substituie(l2...ln), daca l1 != e
%                            }
% Specificatii:
% substituie(l - lista, e - int, r - lista)
% l - lista initiala
% e - un element
% r - lista cu care se va inlocui elementul e in lista l
%
% Model de flux: (i,i,i,o), (i,i,i,i);
substituie([],_,_,[]).
substituie([H|T], E,R,[H|Rez]):- H =\= E,!,
    substituie(T,E,R,Rez).
substituie([H|T], H, R, [R|Rez]):- substituie(T,H,R,Rez).
