function pos=f(k=10, p=0.5)
  pos=zeros(1,k+1);

  for i=1:k
    pos(i+1) = pos(i) + (2 * binornd(1,p) - 1);
  endfor
endfunction
