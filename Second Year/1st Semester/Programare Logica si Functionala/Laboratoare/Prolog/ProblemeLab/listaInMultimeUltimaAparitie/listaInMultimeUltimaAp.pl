%a. Sa se scrie un predicat care transforma o lista intr-o multime, in
% ordinea primei aparitii. Exemplu: [1,2,3,1,2] e transformat in [1,2,3].
%
% Modelul matematic:
% exista(l1...ln, e) =
% {
%     False, daca n = 0
%     True, daca l1 = e
%     exista(l2...ln, e), altfel
% }
%
% transformaLista(l1...ln) =
% {
%     [], daca n = 0
%     l1 U transformaLista(l2...ln), daca exista(l2..ln,l1) este False
%     transformaLista(l2...ln), daca exista(l2..ln,l1) este True
% }
%
% model de flux: (i,o), (i,i).
%
% Specificatii:
% exista(L: lista, e: element, R: boolean)
% L: lista in care verificam daca exista elementul e
% e: elementul pe care il cautam in lista L
% R: rezultatul, adica False daca e nu exista in lista L, True altfel
%
% transformaLista(L - lista, R - lista)
% L: lista pe care o vom transforma in multime
% R: multimea ce va contine elementele din L, in ordinea ultimei
% aparitii

exista([H|_], E):-
    H =:= E, !.
exista([_|T], E):-
    exista(T,E).

transformaLista([],[]):-!.
transformaLista([H|T],[H|R]):-
    not(exista(T,H)),!,
    transformaLista(T,R).
transformaLista([_|T],R):-
    transformaLista(T,R).













