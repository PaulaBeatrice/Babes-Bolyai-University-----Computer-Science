% Model matematic:
% adaugaEl(l1...ln,el,poz,p)={
%  [], daca n = 0
%  l1 U adaugaEl(l1...ln,el,poz+1,p), daca poz != p
%  l1 U el U adaugaEl(l1...ln,el,poz+1,p*2+1), daca poz = p
% }
%
% adauga(l1...ln,el)= adaugaEl(l1...ln,el,1,1)
%
% Specificatii:
% adaugaEl(l - lista, el - int, poz - int, p - int, r - lista)
% l: lista initiala
% el: elementul ce va fi adaugat
% poz: pozitia curenta
% p: pozitia pe care va fi adaugat el
% r: lista finala
%
% Model de flux: (i,i,i,i,o), (i,i,i,i,i)

adaugaEl([],_,_,_,[]):-!.
adaugaEl([H|T],E,P,P,[H,E|R]):-!,
    Poz is P+1,
    P2 is P*2+1,
    adaugaEl(T,E,Poz,P2,R).

adaugaEl([H|T],E,Poz,P,[H|R]):-
    Poz =\= P,!,
    Poz1 is Poz+1,
    adaugaEl(T,E,Poz1,P,R).

adauga(L,E,R):-adaugaEl(L,E,1,1,R).
