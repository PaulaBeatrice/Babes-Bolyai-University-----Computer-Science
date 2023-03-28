function X = rndvardisc( x, p, N )

  q = cumsum( p ); % [0 -- p1 -- p1 + p2 -- p1 + p2 + p3 -- ... -- 1]
  X = zeros(1, N);

  for i = 1 : N
    u = rand; % u in [0, 1]
    j = 1;

    while u > q(j)

      j++;

    endwhile

    % index = sum(q < u) + 1;
    % X(i) = x(index);

    X(i) = x(j);

  endfor

endfunction
