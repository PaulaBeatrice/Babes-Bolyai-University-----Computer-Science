% Model matematic:
% addPoz(l1..ln,e,p,poz)={
%   [], daca n = 0 && poz != 1
%   [e], daca n = 0 && poz = 1
%   e U l1 U addPoz(l2...ln,e,p+1,poz), daca p = poz
%   l1 U addPoz(l2...ln,e,p+1,poz), daca p != poz
% }
%
% addE(l1...ln,e,poz) = {
%   [], daca n = 0
%   addPoz(l1..ln, e,1,poz), altfel
% }
%
% Specificatii:
% addPoz(l - lista, e - int, p - int, poz - int, r - lista)
% l: lista initiala
% e: elementul ce se va adauga
% p: pozitia curenta
% poz: pozitia pe care se va adauga e
% r: lista rezultata in urma adaugarii elementului e pe pozitia poz
%
% addE(l - lista, e - int, poz - int, r - lista)
% l: lista initiala
% e: elementul care va fi adaugat
% poz: pozitia pe care se va adauga e in l
% r: lista rezultata
%
% Modele de flux: (i,i,i,i,o), (i,i,i,i,i); (i,i,i,o), (i,i,i,i)

addPoz([],E,_,1,[E]):-!.
addPoz([],_,_,_,[]).
addPoz([H|T],E,P,P,[E,H|R]):-!,
    P1 is P+1,
    addPoz(T,E,P1,P,R).
%addPoz(L,E,P,P,[E|L]):- !.
addPoz([H|T],E,P,Poz,[H|R]):-
    P1 is P+1,
    addPoz(T,E,P1,Poz,R).

addE(L,E,Poz,R):-addPoz(L,E,1,Poz,R).
