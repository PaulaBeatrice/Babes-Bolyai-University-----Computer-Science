function fr = birthday(NS=2000) %fr = frecventa relativa
                                %NS = numar de simulari
  contor=0;
  for i = 1:NS
    b = randi(365,1,23);
    b = sort(b);
    for j = 1:length(b)-1
      if b(j) == b(j+1)
        contor++;
        break;
      endif
    endfor
  endfor
  fr = contor/NS;
end

% formula : 1 - A de 365 luate cate 23 / 365^23

