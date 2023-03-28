% Exercitiul 2

function p2(N = 1000)

  x1 = rndexp(12, N);
  x2 = exprnd(12, 1, N);

  [mean(x1) mean(x2)]
  [std(x1) std(x2)]

endfunction
