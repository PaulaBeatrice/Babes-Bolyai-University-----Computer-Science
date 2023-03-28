k=5;
s=0;
p=0.5;
for i=1:k
  step = binornd(1,p);
  if step == 1
    s = s+1;
    fprintf('Succes: %d\n', s);
  else s = s-1;
    fprintf('Esec: %d\n', s);
  endif
endfor
fprintf('Poz final: %d\n', s);
