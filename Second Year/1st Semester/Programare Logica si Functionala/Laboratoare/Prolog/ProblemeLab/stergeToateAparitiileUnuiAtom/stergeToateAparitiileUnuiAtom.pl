% sterge(l1...ln,el)={
%                      [], daca n = 0
%                      l1 U sterge(l2...ln), daca el != l1
%                      sterge(l2...ln), daca el = l1
%                    }
% Specificatii:
% sterge(l - lista, el - int, r - lista)
% l - lista initiala
% el - atomul care se va sterge
% r - lista rezultata in urma stergerii elementului el
% Modele de flux: (i,i,o), (i,i,i)

sterge([],_,[]).
sterge([H|T],E,[H|R]):-
    H =\= E,!,
    sterge(T,E,R).
sterge([H|T],H,R):- sterge(T,H,R).
