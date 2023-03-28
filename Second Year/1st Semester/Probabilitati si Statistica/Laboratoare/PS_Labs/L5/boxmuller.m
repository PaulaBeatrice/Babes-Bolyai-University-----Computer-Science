function [X, Y] = boxmuller(N)

  U = rand(2, N);

  R = sqrt(-2 * log(U(1, :))); % raza
  V = 2 * pi * U(2, :); % unghiul

  X = R .* cos(V);
  Y = R .* sin(V);

endfunction
