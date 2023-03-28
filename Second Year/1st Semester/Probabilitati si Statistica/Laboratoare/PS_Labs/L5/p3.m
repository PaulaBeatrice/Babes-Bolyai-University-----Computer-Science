% Exercitiul 3

function p3(N = 500)

  close all; clf;

  t = linspace(0, 2 * pi, 360);
  polar(t, 4 * ones(1, 360), 'w');
  hold on; % inainte avem coordonate polare, vrem sa le pastram cand trecem la carteziene

  [X, Y] = boxmuller(N);
  plot(X, Y, 'r*');
  polar(t, 0.5 * ones(1, 360), 'k');

  sum(X .^ 2 + Y .^ 2 < 0.25) / N % (x - cx) .^ 2 + (y - cy) .^ 2 < r .^ 2, cx=0, cy=0
  % pentru frecventa relativa, putem sa folosim  si mean(X .^ 2 + Y .^ 2 < 0.25)

  1 - exp(-1 / 8)

  % X = R .* cos(V);
  % Y = R .* sin(V);
  % P(X .^ 2 + Y .^ 2 < 0.25) = P(-2 * log(U(1, :)) < 0.25) =
  % P(log(U(1, :)) > -1 / 8) = P(U(1, :) > exp( -1 / 8)) =
  % 1 - P(U(1, :) <= exp( -1 / 8)) = 1 - exp( -1 / 8)

  figure;
  polar(t, 4 * ones(1, 360), 'w');
  hold on;
  Z = normrnd(0, 1, 2, N);
  plot(Z(1, :), Z(2, :), 'c*');
  polar(t, 0.5 * ones(1, 360), 'k');
  mean(Z(1, :) .^ 2 + Z(2, :) .^ 2 < 0.25)

endfunction
