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
%     l1 U transformaLista(l2...ln), daca exista(l2...ln, l1) este False
%     transformaLista(l2...ln), daca exista(l2...ln) este True
% }
%
% model de flux: (i, o, i)
%
% Exemple de testare:
%    transformareLista([1,4,2,4,2], R) => R =[1,4,2]
%    transformareLista([1,2,3], R) => R = [1,2,3]
%    transformareLista([1,1,1], R) => R = [1]
%    transformareLista([], R) => R = []

exista([H|_], E):-
    H =:= E, !.
exista([_|T], E):-
    exista(T,E).

transformaLista([], R):- R=[].
transformaLista([H|T], R):-
    exista(T, H),
    transformaLista(T, R).
transformaLista([H|T], [H|R]):- not(exista(T, H)),
    transformaLista(T, R).






