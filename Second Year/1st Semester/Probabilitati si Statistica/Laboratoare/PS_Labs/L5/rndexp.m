function X = rndexp(lambda, N)

  X = -log(1 - rand(1, N)) .* lambda;  % conventia cu 1/lambda din octave, .* inutil ca e scalar lambda

endfunction
