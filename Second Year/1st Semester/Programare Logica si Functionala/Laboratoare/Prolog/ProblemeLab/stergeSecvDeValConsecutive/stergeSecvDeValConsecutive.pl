% Model matematic:
% stergeConsecutive(l1...ln)={
%    [], daca n = 0
%    [l1], daca n = 1
%    [], daca l1 = l2 - 1
%    [l1,l2], daca l1 != l2 - 1
%    stergeConsecutive(l2...ln), daca l1 = l2-1 si l2 = l3-1
%    stergeConsecutive(l3...ln), daca l1 = l2-1 si l2 != l3-1
%    l1 U stergeConsecutive(l2...ln), daca l1 != l2-1
% }
% Specificatii + Model de flux

stergeConsecutive([],[]).
stergeConsecutive([H],[H]):-!.
stergeConsecutive([H1,H2],[]):-H1 =:= H2-1,!.
stergeConsecutive([H1,H2],[H1,H2]):-!.
stergeConsecutive([H1,H2,H3|T],R):-
	H1 =:= H2-1,
	H2 =:= H3-1,!,
	stergeConsecutive([H2,H3|T],R).
stergeConsecutive([H1,H2,H3|T],R):-
	H1 =:= H2 - 1,
	H2 =\= H3 - 1,!,
	stergeConsecutive([H3|T],R).
stergeConsecutive([H1,H2,H3|T],[H1|R]):-
	H1 =\= H2 - 1,!,
	stergeConsecutive([H2,H3|T],R).
