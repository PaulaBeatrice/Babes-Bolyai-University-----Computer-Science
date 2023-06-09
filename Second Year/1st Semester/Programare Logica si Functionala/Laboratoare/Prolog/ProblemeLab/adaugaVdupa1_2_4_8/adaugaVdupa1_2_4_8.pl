% Modelul matematic:
% adaugaV(l1..ln, v, pos, put_2)=
%     {
%       [], if n = 0
%       l1 U V U adaugaV(l2...ln, v, pos+1, put_2*2), daca pos = put_2
%       l1 U adaugaV(l2...ln, v, pos+1, put_2), altfel
%     }
% adaugaV_L(l, v, r) = adaugaV(l,v,1,1,r)
% Specificatii:
% adaugaV(l - lista, v - int, pos - int, put_2 - int, r - lista)
% l - lista initiala
% v - valoarea ce se va adauga in l
% pos - pozitia curenta din lista l
% put_2 - puterea lui 2 curenta(si cea pe care se va insera v)
% r - lista rezultata in urma modificarilor
%
% Model de flux: (i,i,i,i,o), (i,i,i,i,i).
%                (i,i,o), (i,i,i).

adaugaV([],_,_,_,[]).
adaugaV([H|T], V,P,P,[H,V|R]):-
    !,
    Pos1 is P+1,
    Put_2 is P*2,
    adaugaV(T,V,Pos1,Put_2,R).
adaugaV([H|T], V, Pos, Put_2, [H|R]):-
    Pos1 is Pos+1,
    adaugaV(T,V,Pos1,Put_2, R).

adaugaV_L(L,V,R):- adaugaV(L,V,1,1,R).
