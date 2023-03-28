function pb2b(n=100000000)
  %g = @(x) exp( - x .^ 2), a = -2, b = 2, M = 1
  g = @(x) abs(sin(exp(x))), a = -1, b = 3, M = 1
  %g = @(x)  x .^2 ./ (1 + x .^ 2) .* (x <= 0) + sqrt(2 * x - x .^ 2) .* (x > 0), a = -1, b = 2, M = 1
  tic
  MC1(g,a,b,M,n)
  MC2(g,a,b,n)
  integral(g,a,b)
  toc
endfunction;
