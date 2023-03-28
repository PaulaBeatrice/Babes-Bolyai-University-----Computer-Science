function frq=g(m=1000,p=0.4,k=10)
  last=[];
  for i=1:m
    last=[last f(k,p)(end)];
  endfor
  frq=hist(last,-k:k);
  bar(-k:k,frq,'hist');

  mx = max(frq);
  possibleValues=-k:k;
  MAXIM=possibleValues(frq == mx)
endfunction
