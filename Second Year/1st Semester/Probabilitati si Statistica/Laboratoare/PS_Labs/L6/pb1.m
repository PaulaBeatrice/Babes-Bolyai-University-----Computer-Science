function pb1(N=1000, m, sigma)
  close all;
  x = normrnd(m,sigma,1,N);

  % i)
  figure
  hist(x,10);

  % ii)
  figure, hold on
  [app,centers]=hist(x,10); % appearances, centers
  hist(x, centers, 10 / (max(x) - min(x)));

  t=linspace(min(x),max(x),N);
  plot(t,normpdf(t,m,sigma),'-r');

  % iii)
  [mean(x),m]
  [std(x),sigma]
  u=mean((160<=x)&(x<=170));
  v=normcdf(170,m,sigma)-normcdf(160,m,sigma); % F(x)=P(X<x) - normcdf(t
  [u,v]
endfunction
