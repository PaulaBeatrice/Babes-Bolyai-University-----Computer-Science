function aranjamente(v)
  k=input("k=");
  w=nchoosek(v, k);
  [linii,~]=size(w);

  for ind=1:linii
    disp(perms(w(ind, :)));
  endfor
endfunction
