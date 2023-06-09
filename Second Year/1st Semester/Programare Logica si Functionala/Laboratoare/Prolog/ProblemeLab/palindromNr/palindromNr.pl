% facem inversul si compari daca e egal
%
%
% inv(nr,nri)={
%   nri, daca nr = 0
%   inv([nr/10],nr1*10+nr%10), altfel
%   }

%inv_numar(Nr: int, NrI: int, Rez: int)
%Nr - numarul care trebuie inversat
%NrI - variabila colectoare in care retinem rezultatul partial
%Rez - rezultatul
%model de flux (i, i, o), (i, i, i)
inv_numar(0, NrI, NrI).
inv_numar(Nr, NrI, Rez):-
Nr > 0,
Cifra is mod(Nr,10),
NrINew is NrI * 10 + Cifra,
NrNew is Nr div 10,
inv_numar(NrNew, NrINew, Rez).
