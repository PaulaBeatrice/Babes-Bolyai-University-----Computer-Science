function pb2a(n=1000)
  %g = @(x) exp( - x .^ 2), a = -2, b = 2, M = 1
  % g = @(x) abs(sin(exp(x))), a = -1, b = 3, M = 1
   g = @(x)  x .^2 ./ (1 + x .^ 2) .* (x <= 0) + sqrt(2 * x - x .^ 2) .* (x > 0), a = -1, b = 2, M = 1
  clf; hold on;
  x=unifrnd(a,b,1,n);
  y=unifrnd(0,M,1,n);
  % v(v>4)
  % u=v(v>4)=5:10
  plot(x(y<=g(x)),y(y<=g(x)),'*r')
  plot(x(y>g(x)),y(y>g(x)),'*b')
  t=linspace(a,b,n);
  plot(t,g(t),'-k','LineWidth',3);
endfunction
