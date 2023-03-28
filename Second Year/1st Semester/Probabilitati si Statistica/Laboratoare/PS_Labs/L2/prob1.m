sim=25000;

count=0;
for i=1:sim
  v=randi(365,1,23);
  if length(unique(v)) < length(v) % alte moduri?
    count++;
  endif
endfor

prob=count/sim;
fprintf("Probabilitatea este %.2f\n", prob)
