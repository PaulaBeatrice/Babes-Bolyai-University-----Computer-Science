% Model matematic
% stergePrimeleKEl(l1...ln, el, k)=
% {
%    [], daca n = 0
%    l1...ln, daca k = 0
%    stergePrimeleKEl(L2...ln, el, k-1), daca l1 = el
%    l1 U stergePrimeleKEL(l2...ln, el, k), daca l1 != el
% }
%
% sterge3(l1..ln,el)=
% {
%    [], daca n = 0
%    stergePrimeleKEL(l1...ln, el,3)
% }
%
% Specificatii:
% stergePrimeleKEl(l - lista, el - int, k - int, r - lista)
% l - lista initiala
% el - elementul care se va sterge
% k - nr de stergi
% r - lista rezulatata
%
% sterge3(l - lista, el-int, r - lista)
% l - lista initiala
% el - elementul care se va sterge
% r - lista rezultata
%
% Modele de flux: (i,i,i,o), (i,i,i,i); (i,i,o), (i,i,i)

stergePrimeleKEl([],_,_,[]):-!.
stergePrimeleKEl(L,_,0,L):-!.
stergePrimeleKEl([E|T],E,K,R):-!,
    K1 is K-1,
    stergePrimeleKEl(T,E,K1,R).
stergePrimeleKEl([H|T],E,K,[H|R]):-
    stergePrimeleKEl(T,E,K,R).

sterge3([],_,[]).
sterge3(L,E,R):-
    stergePrimeleKEl(L,E,3,R).
