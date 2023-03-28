% >>pkg load statistics
urn='rrrrrbbbgg';
% ['r','r',...]

N=5000;
PA=0; PAB=0; PCond=0;

for i=1:N
  extragere=randsample(urn,3); % replacement=true
  if any(extragere=='r') % ext=rbb, >> ext=='r' ans=100  any(ext=='r') -> 1 cel putin una nenula
    PA++; % (i)
  endif

  if all(extragere=='r') % toate nenule, vector condite
    PAB++; % (ii)
  endif

  if any(extragere=='r')
    PA++;
    if all(extragere=='r')
      PCond++; % (iv)
    endif
  endif
endfor

disp("(i)");
PA/N
disp("(ii)");
PAB/N
disp("(iii)");
PAB/PA
disp("(iv)");
PCond/PA % (PCond/N)/(PA/N)
