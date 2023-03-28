% 2 a

% binornd(1,p) -> 0 sau 1
% x din 0,1 => 2x-1 din -1,1

function pos=f(k=10,p=0.5)
  pos=zeros(1,k+1);

  for i=1:k
    pos(i+1) = pos(i) + (2 * binornd(1,p) - 1);
  endfor
endfunction

% 2 b

function frq=g(m=1000,p=0.4,k=10)
  last=[];
  for i=1:m
    last=[last f(k,p)(end)];
  endfor
  frq=hist(last,-k:k);
  bar(-k:k,frq,'hist');

  mx = max(frq);
  possibleValues=-k:k;
  MAXIM=possibleValues(frq == mx)
endfunction

